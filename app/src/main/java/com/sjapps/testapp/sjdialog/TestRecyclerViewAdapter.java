package com.sjapps.testapp.sjdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.testapp.R;

import java.util.ArrayList;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    ArrayList<TestObj> testObjs;
    private int bg;

    public TestRecyclerViewAdapter(ArrayList<TestObj> testObjs) {
        this.testObjs = testObjs;

    }
    public TestRecyclerViewAdapter(ArrayList<TestObj> testObjs,int bg) {
        this.testObjs = testObjs;
        this.bg = bg;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public TextView getVal1Txt() {
            return itemView.findViewById(R.id.value1Txt);
        }

        public TextView getVal2Txt() {
            return itemView.findViewById(R.id.value1Txt);
        }

        public View getView(){
            return itemView.findViewById(R.id.layoutItem);
        }
    }

    @NonNull
    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.getView().setBackgroundResource(bg);


        holder.getVal1Txt().setText(testObjs.get(position).val1);
        holder.getVal2Txt().setText(testObjs.get(position).val2);


        holder.getView().setOnClickListener(view -> Toast.makeText(holder.getView().getContext(), testObjs.get(position).toString(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return testObjs.size();
    }
}
