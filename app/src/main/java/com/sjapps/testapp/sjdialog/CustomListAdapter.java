package com.sjapps.testapp.sjdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.testapp.R;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    String[] strings;

    public CustomListAdapter(String[] strings){
       this.strings = strings;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(strings[position]);

        int pos = position;

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(strings[pos]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.t1);
        }

        public View getView(){
            return itemView;
        }
    }
}
