package com.example.slsl1_2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slsl1_2.Model.TaskModel;
import com.example.slsl1_2.R;
import com.example.slsl1_2.databinding.FragmentHomeBinding;
import com.example.slsl1_2.ui.slideshow.FormFragment;

import static com.example.slsl1_2.ui.home.HomeAdapter.list;



public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public static HomeAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initRecycler();
        return root;
    }
    private void initRecycler() {
        adapter = new HomeAdapter();
        binding.rvTask.setAdapter(adapter);

    }
    public static void Receiver(TaskModel model, int requestCode){
        if(requestCode == 100 && !model.getTitle().isEmpty() && !model.getDescription().isEmpty()){
        adapter.addModel(model);
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}