package com.example.slsl1_2.ui.slideshow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.slsl1_2.Model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.ui.home.HomeAdapter;
import com.example.slsl1_2.ui.home.HomeFragment;


public class FormFragment extends Fragment {
    TextView title,desc;
    Button save;
    public static TaskModel model;
    public static int requestCode;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container,false);
        initView(view);
        initClickListener(view);

        return view;
    }
    private void initClickListener(View view) {
        save.setOnClickListener(v -> {
            String tit = title.getText().toString();
            String des = desc.getText().toString();
            if (!tit.isEmpty() && !des.isEmpty()) {
                requestCode = 100;
            }
            model = new TaskModel(des,tit);
            HomeFragment.Receiver(model,requestCode);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController .navigateUp();
        });
    }
        private void initView(View view) {
        title = view.findViewById(R.id.title_et);
        desc = view.findViewById(R.id.desc_et);
        save = view.findViewById(R.id.btn_save);
    }
}