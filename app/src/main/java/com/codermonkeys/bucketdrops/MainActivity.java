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

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Toolbar mToolbar;
    private Button mButtonAdd;
    Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRealm = Realm.getDefaultInstance();
      RealmResults<Drop> results = mRealm.where(Drop.class).findAllAsync();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_drops);



        mRecyclerView.setAdapter(new AdapterDrops(this, results));
        setSupportActionBar(mToolbar);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialogAdd();
            }
        });


    }

    private void showDialogAdd() {

        DialogAdd dialog = new DialogAdd();
        dialog.show(getSupportFragmentManager(), "Add");

    }
}
