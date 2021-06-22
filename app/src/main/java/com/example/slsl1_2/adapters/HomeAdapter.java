package com.example.slsl1_2.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slsl1_2.Room.App;
import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.interfaces.OnItemClickListener;
import com.example.slsl1_2.fragments.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    public List<TaskModel> list = new ArrayList<>();
    public List<TaskModel> listSource = new ArrayList<>();
    View view;
    private OnItemClickListener listener;
    private TaskModel model;


    public HomeAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addModel(TaskModel model) {
        list.add(model);
        listSource = list;
        notifyDataSetChanged();
    }
    public void editModel(TaskModel model, int position) {
        App.dataBase.getTaskDao().update(model);
        notifyItemChanged(position);
    }
    public void addListOfModel(List<TaskModel> listM){
        list.clear();
        this.list.addAll(listM);
        notifyDataSetChanged();

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(HomeFragment.orStatus){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid,parent,false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<TaskModel> filteredList) {
        list = filteredList;
        notifyDataSetChanged();
    }

    public void listEmpty() {
        list = listSource;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_item);
        }
        @SuppressLint("ResourceAsColor")
        public void onBind(TaskModel model) {
            itemView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition(),model);
            });
            title.setText(model.getTitle());
           if (model.getBackground() != null) {
                switch (model.getBackground()) {
                    case "black":
                        title.setTextColor(Color.parseColor("#FFF300"));
                        itemView.setBackgroundResource(R.drawable.btn_black);
                        break;
                    case "yellow":
                        title.setTextColor(Color.parseColor("#EC0505"));
                        itemView.setBackgroundResource(R.drawable.btn_yellow);
                        break;
                    case "red":
                        title.setTextColor(Color.parseColor("#000000"));
                        itemView.setBackgroundResource(R.drawable.btn_red);
                        break;
                }
            }
        }
    }
}
