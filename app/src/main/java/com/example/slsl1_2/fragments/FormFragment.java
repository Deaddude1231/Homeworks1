package com.example.slsl1_2.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.slsl1_2.Room.App;
import com.example.slsl1_2.databinding.FragmentFormBinding;
import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.interfaces.OnItemClickListener;


public class FormFragment extends Fragment {
    EditText etTitle;
    String time;
    Button save;
    String background;
    public TaskModel model;
    private boolean isEdit = false;
    private FragmentFormBinding binding;
    int pos;
    TaskModel mod;
    private AppCompatButton btnB, btnY, btnR;
    private OnItemClickListener click;

    public void setClick(OnItemClickListener click) {
        this.click = click;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        initView(view);
        initClickListener(view);
        getData();
        initButtons();
        return view;
    }

    private void initButtons() {
        btnB.setOnClickListener(v -> {
            background = "black";
        });
        btnR.setOnClickListener(v -> {
            background = "red";
        });
        btnY.setOnClickListener(v -> {
            background = "yellow";
        });
    }

    private void getData() {
        if (getArguments() != null) {
            mod = (TaskModel) getArguments().getSerializable("mod");
            if (mod != null) {
                etTitle.setText(model.getTitle());
            }
        }
    }

    private void initClickListener(View view) {
        save.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            Bundle bundle = new Bundle();
            model = new TaskModel(title, background, null);
            if (mod == null) {
                App.getDataBase().getTaskDao().insert(model);
                bundle.putSerializable("model", model);
            }
            else {
                mod.setTitle(title);
                App.getDataBase().getTaskDao().update(mod);
                bundle.putSerializable("editedModel", model);
            }
            getParentFragmentManager().setFragmentResult("send", bundle);
            close();

        });
    }

    public void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigateUp();
    }

    private void initView(View view) {
        etTitle = view.findViewById(R.id.title_et);
        save = view.findViewById(R.id.btn_save);
        btnY = view.findViewById(R.id.btn_yellow);
        btnR = view.findViewById(R.id.btn_red);
        btnB = view.findViewById(R.id.btn_black);
    }
}