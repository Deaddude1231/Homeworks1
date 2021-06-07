package com.example.slsl1_2.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.slsl1_2.R;
import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.databinding.FragmentHomeBinding;
import com.example.slsl1_2.ui.OnItemClickListener;

import org.jetbrains.annotations.NotNull;


public class HomeFragment extends Fragment implements OnItemClickListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public static HomeAdapter adapter;
    public static boolean orStatus = false;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initRecycler();
        getDataForm();
        return root;
    }

    private void getDataForm() {
        if (!orStatus) {
            binding.rvTask.setLayoutManager(staggeredGridLayoutManager);
        } else {
            binding.rvTask.setLayoutManager(linearLayoutManager);
        }
        binding.rvTask.setAdapter(adapter);
        getParentFragmentManager().setFragmentResultListener("eKey", getViewLifecycleOwner(), (requestKey, result) -> {
            TaskModel model = (TaskModel) result.getSerializable("keyModel");
            if (model != null) {
                adapter.addModel(model,HomeFragment.this);
            }
        });
        getParentFragmentManager().setFragmentResultListener("editData", getViewLifecycleOwner(), ((requestKey, result) -> {
            TaskModel model = (TaskModel) result.getSerializable("keyModel");
            if(model != null){
                adapter.editModel(model, result.getInt("position"));
            }


        }));

    }

    private void initRecycler() {
        adapter = new HomeAdapter();
        binding.rvTask.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_btn_change) {
            if (orStatus) {
                item.setIcon(R.drawable.ic_baseline_widgets_24);
                binding.rvTask.setLayoutManager(linearLayoutManager);
                orStatus = false;
            } else {
                item.setIcon(R.drawable.ic_outline_list_24);
                binding.rvTask.setLayoutManager(staggeredGridLayoutManager);
                orStatus = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, TaskModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mod",model);
        bundle.putSerializable("position",position);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.action_nav_home_to_formFragment);
        navController.navigate(R.id.action_nav_home_to_formFragment,bundle);

    }

    @Override
    public void onItemLongClick(int position) {

    }
}