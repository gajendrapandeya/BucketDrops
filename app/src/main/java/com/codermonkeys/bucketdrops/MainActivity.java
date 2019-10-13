package com.codermonkeys.bucketdrops;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.bucketdrops.Adapter.AdapterDrops;
import com.codermonkeys.bucketdrops.beans.Drop;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Toolbar mToolbar;
    private Button mButtonAdd;
    Realm realm;
    private ArrayList<Drop> dropArrayList = new ArrayList<>();
    private AdapterDrops adapterDrops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_drops);
        initRecyclerView();
        setSupportActionBar(mToolbar);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogAdd();
            }
        });


    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        adapterDrops = new AdapterDrops(this, dropArrayList);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapterDrops);
        setData();

    }

    private void setData() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<Drop> realmList = realm.where(Drop.class).findAll();
        List<Drop> sales = new ArrayList<>();
        sales = realm.copyFromRealm(realmList);
        realm.commitTransaction();
        if (sales != null && sales.size() > 0) {
            dropArrayList.clear();
            dropArrayList.addAll(sales);
            adapterDrops.notifyDataSetChanged();
        }
    }

    private void showDialogAdd() {

        DialogAdd dialog = new DialogAdd(new OnSuccessListener() {
            @Override
            public void onSuccess() {
                setData();
            }
        });
        dialog.show(getSupportFragmentManager(), "Add");

    }
}
