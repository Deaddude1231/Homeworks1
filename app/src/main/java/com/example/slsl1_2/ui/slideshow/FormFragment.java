package com.example.slsl1_2.ui.slideshow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.slsl1_2.databinding.FragmentFormBinding;
import com.example.slsl1_2.databinding.FragmentHomeBinding;
import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.ui.OnItemClickListener;
import com.example.slsl1_2.ui.home.HomeFragment;


public class FormFragment extends Fragment {
    TextView title,desc;
    Button save;
    public TaskModel model;
    private boolean isEdit = false;
    private FragmentFormBinding binding;
    int pos;
    private OnItemClickListener click;
    public void setClick(OnItemClickListener click) {
        this.click =click;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form,container,false);
        initView(view);
        initClickListener(view);
        getData();
        return view;
    }

    private void getData() {
        if(getArguments() != null){
            model = (TaskModel) getArguments().getSerializable("mod");
            title.setText(model.getTitle());
            pos = getArguments().getInt("position");
            isEdit = true;
        }
    }

    private void initClickListener(View view) {
        save.setOnClickListener(v -> {
            if(isEdit){
                String tit = title.getText().toString();
                model = new TaskModel(tit);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel",model);
                bundle.putInt("position",pos);
                getParentFragmentManager().setFragmentResult("editData",bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            } else {
                String tit = title.getText().toString();
                model = new TaskModel(tit);
                Bundle bundle = new Bundle();
                bundle.putSerializable("keyModel", model);
                getParentFragmentManager().setFragmentResult("eKey", bundle);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            }
        });
    }
        private void initView(View view) {
        title = view.findViewById(R.id.title_et);
        save = view.findViewById(R.id.btn_save);
    }
}