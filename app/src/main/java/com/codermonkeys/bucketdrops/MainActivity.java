package com.codermonkeys.bucketdrops;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Toolbar mToolbar;
    private Button mButtonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_drops);
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
