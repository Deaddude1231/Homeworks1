package com.example.slsl1_2.ui;

import com.example.slsl1_2.model.TaskModel;

public interface OnItemClickListener {
    void onItemClick(int position, TaskModel model);
    void onItemLongClick(int position);
}
