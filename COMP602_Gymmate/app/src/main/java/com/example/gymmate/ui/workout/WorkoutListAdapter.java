package com.example.gymmate.ui.workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymmate.R;
import com.example.gymmate.data.WorkoutItem;
import com.example.gymmate.ui.view.OnRecyclerViewItemClickListener;

import java.util.List;

public class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder> implements  View.OnClickListener{

    private List<WorkoutItem> listData;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private RecyclerView rvParent;

    @Override
    public void onClick(View view) {
        int position = rvParent.getChildAdapterPosition(view);
        if (onRecyclerViewItemClickListener != null)
            onRecyclerViewItemClickListener.onItemClick(rvParent, view, position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View fileView;
        ImageView image;
        TextView homeDate,homeContents;

        public ViewHolder(View itemView) {
            super(itemView);
            fileView = itemView;
            homeDate = itemView.findViewById(R.id.homeDate);
            homeContents = itemView.findViewById(R.id.homeContents);
        }
    }


    // 获取到数据
    public WorkoutListAdapter(List<WorkoutItem> list) {
        this.listData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        rvParent = (RecyclerView) parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item,parent,false);
        view.setOnClickListener(this);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // 数据绑定
        final WorkoutItem s = listData.get(position);
        holder.homeDate.setText(s.getDate());
        StringBuffer sb=new StringBuffer();
        for(String str:s.getContents())
        {
            sb.append(str);
            sb.append("\n");
        }
        holder.homeContents.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public WorkoutItem getPos(int position)
    {
        return listData.get(position);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void test()
    {
        listData.get(0).setDate("tettetetet");
    }
}
