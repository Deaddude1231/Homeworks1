package com.example.slsl1_2.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.ui.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> implements View.OnClickListener {
    public static ArrayList<TaskModel> list = new ArrayList<>();
    View view;
    private OnItemClickListener listener;

    public void addModel(TaskModel model, OnItemClickListener listener) {
        this.listener = listener;
        list.add(model);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // NavController navController = Navigation.findNavController(,R.id.action_nav_home_to_aboutItemFragment);
                //navController.navigateUp();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void editModel(TaskModel model, int position) {
        list.get(position).setTitle(model.getTitle());
        notifyItemChanged(position);
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_item);
        }

        public void onBind(TaskModel taskModel) {
            title.setText(taskModel.getTitle());
        }
    }
}
