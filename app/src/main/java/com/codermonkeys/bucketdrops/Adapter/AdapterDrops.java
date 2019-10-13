package com.codermonkeys.bucketdrops.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.bucketdrops.R;
import com.codermonkeys.bucketdrops.beans.Drop;

import java.util.ArrayList;

import io.realm.RealmResults;

public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {
    
    private LayoutInflater mInflater;
    
    private ArrayList<Drop> mResults;

    public AdapterDrops(Context context, ArrayList<Drop> results) {

        
        mInflater = LayoutInflater.from(context);
        mResults = results;
    }

    private static ArrayList<String> getDummyItems() {

        ArrayList<String > dummyValues = new ArrayList<>();
        for(int i =0 ; i < 40; i++) {

            dummyValues.add("Item"+ i);
        }
        return dummyValues;
    }

    @NonNull
    @Override
    public DropHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view =  mInflater.inflate(R.layout.row_drop, parent, false); 
        DropHolder holder = new DropHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DropHolder holder, int position) {


        Drop drop = mResults.get(position);
        holder.mTextWhat.setText(drop.getWhat());
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public static class DropHolder extends RecyclerView.ViewHolder {

        TextView mTextWhat;
        public DropHolder(@NonNull View itemView) {
            super(itemView);
            mTextWhat = itemView.findViewById(R.id.tv_what);
        }
    }
}
