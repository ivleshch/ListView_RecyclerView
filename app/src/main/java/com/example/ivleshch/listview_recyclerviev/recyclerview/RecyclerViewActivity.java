package com.example.ivleshch.listview_recyclerviev.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.StudentInformationRealm;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class RecyclerViewActivity extends Fragment {

    private RecyclerView rvUsers;
    private Realm realm;

    String nameDelete;
    String linkToGitDelete;
    String linkToGoogleDelete;
    String gitLoginDelete;
    String idGoogleDelete;
    String searchNameDelete;


    private final RealmChangeListener<RealmResults<StudentInformationRealm>> changeListener = new RealmChangeListener<RealmResults<StudentInformationRealm>>() {
        @Override
        public void onChange(RealmResults<StudentInformationRealm> elements) {
            updateUI(elements);
        }
    };


    private void updateUI(RealmResults<StudentInformationRealm> elements) {
        if (rvUsers.getAdapter() == null) {
            rvUsers.setAdapter(new RecyclerViewAdapter(getActivity(), elements));
        } else {
            RecyclerViewAdapter adapter = (RecyclerViewAdapter) rvUsers.getAdapter();
            adapter.notifyDataSetChanged();
        }
    }

    private void updateUIRefreshAll(RealmResults<StudentInformationRealm> elements) {
        rvUsers.setAdapter(new RecyclerViewAdapter(getActivity(), elements));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        final SearchView search = (SearchView) view.findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                realm = Realm.getDefaultInstance();
                RealmResults<StudentInformationRealm> users = realm.where(StudentInformationRealm.class)
                        .beginGroup()
                        .contains("searchName",newText)
                        .or()
                        .contains("name",newText)
                        .endGroup()
                        .findAllSorted("searchName",Sort.ASCENDING);
                users.addChangeListener(changeListener);
                updateUIRefreshAll(users);
                return false;
            }
        });


        rvUsers = (RecyclerView) view.findViewById(R.id.RecyclerView);

        realm = Realm.getDefaultInstance();

        RealmResults<StudentInformationRealm> users = realm.where(StudentInformationRealm.class)
                .findAllSortedAsync("searchName",Sort.ASCENDING);
        users.addChangeListener(changeListener);

        rvUsers.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        ItemTouchHelper.SimpleCallback mIth = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                String NameDelete = ((RecyclerViewAdapter.UserViewHolder) viewHolder).Name.getText().toString();
                RealmResults<StudentInformationRealm> rawUsersList = realm.where(StudentInformationRealm.class).equalTo("name", NameDelete).findAll();
                if (rawUsersList.size() != 0) {
                    nameDelete = rawUsersList.first().getName();
                    linkToGitDelete = rawUsersList.first().getLinkToGit();
                    linkToGoogleDelete = rawUsersList.first().getLinkToGoogle();
                    gitLoginDelete = rawUsersList.first().getGitLogin();
                    idGoogleDelete = rawUsersList.first().getIdGoogle();
                    searchNameDelete = rawUsersList.first().getSearchName();

                    ;
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    rawUsersList.deleteFirstFromRealm();
                    //deleteAllFromRealm();
                    realm.commitTransaction();
                    realm.close();
                }

                Snackbar snackbar = Snackbar.make(rvUsers, nameDelete + " deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();

                        //
                        int min = 0;
                        RealmResults<StudentInformationRealm> rawUsers = realm.where(StudentInformationRealm.class).findAllSorted("id", Sort.DESCENDING);
                        if (rawUsers.size() > 0) {
                            min = rawUsers.first().getId() + 1;
                        }

                        StudentInformationRealm user = new StudentInformationRealm();
                        user.setId(min);
                        user.setName(nameDelete);
                        user.setLinkToGit(linkToGitDelete);
                        user.setLinkToGoogle(linkToGoogleDelete);
                        user.setGitLogin(gitLoginDelete);
                        user.setIdGoogle(idGoogleDelete);
                        user.setSearchName(searchNameDelete);

                        realm.insertOrUpdate(user);
                        realm.commitTransaction();
                        realm.close();
                    }
//
                });
                snackbar.show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mIth);
        itemTouchHelper.attachToRecyclerView(rvUsers);
    }
}
