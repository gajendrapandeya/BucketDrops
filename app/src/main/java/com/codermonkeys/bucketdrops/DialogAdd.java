package com.codermonkeys.bucketdrops;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private OnSuccessListener listener;

    public DialogAdd(OnSuccessListener listener) {
        this.listener = listener;
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

        if (!TextUtils.isEmpty(mInputWhat.getText().toString())) {
            String what = mInputWhat.getText().toString();
            long now = System.currentTimeMillis();

            final Drop drop = new Drop();
            drop.setAdded(now);
            drop.setCompleted(false);
            drop.setWhat(what);
            drop.setWhen(0);
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() { // must be in transaction for this to work
                @Override
                public void execute(Realm realm) {
                    // increment index
                    Number currentIdNum = realm.where(Drop.class).max("id");

                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    drop.setId(nextId);
                    //...
                    realm.insertOrUpdate(drop); // using insert API
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    dismissDialog();
                }
            });
        } else
            Toast.makeText(getContext(), "Textfield should not empty", Toast.LENGTH_SHORT).show();

    }

    private void dismissDialog() {
        this.listener.onSuccess();
        dismiss();
    }


}
