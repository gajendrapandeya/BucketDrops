package com.codermonkeys.bucketdrops;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.codermonkeys.bucketdrops.beans.Drop;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmConfiguration.Builder;

public class DialogAdd extends DialogFragment {

    private ImageButton mBtnClickListner;
    private EditText mInputWhat;
    private DatePicker mInputWhen;
    private Button mBtnAdd;

    public DialogAdd() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mInputWhat = (EditText) view.findViewById(R.id.et_drop);
        mInputWhen = (DatePicker) view.findViewById(R.id.bpv_date);
        mBtnClickListner = (ImageButton) view.findViewById(R.id.btn_close);
        mBtnAdd = (Button) view.findViewById(R.id.btn_add_it);

        mBtnClickListner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                addAction();
            }
        });
    }

    private void addAction() {



        String what = mInputWhat.getText().toString();
        long now = System.currentTimeMillis();

        //Realm.init(getActivity());

       // RealmConfiguration configuration = new RealmConfiguration.Builder().build();
       // Realm.setDefaultConfiguration(configuration);

        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();

        Drop drop = new Drop(what, now, 0, false);

        realm.beginTransaction();
        realm.copyFromRealm(drop);
        realm.commitTransaction();
        realm.close();


    }


}
