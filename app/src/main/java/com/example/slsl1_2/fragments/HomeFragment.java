package com.example.slsl1_2.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.slsl1_2.MainActivity;
import com.example.slsl1_2.R;
import com.example.slsl1_2.Room.App;
import com.example.slsl1_2.adapters.HomeAdapter;
import com.example.slsl1_2.model.TaskModel;
import com.example.slsl1_2.databinding.FragmentHomeBinding;
import com.example.slsl1_2.interfaces.OnItemClickListener;
import com.example.slsl1_2.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;




public class HomeFragment extends Fragment implements OnItemClickListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public HomeAdapter adapter;
    public int pos;
    Bundle bundle = new Bundle();
    public static boolean orStatus = false;
    List<TaskModel> list;
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
        addTextListener();
        return root;
    }

    private void getDataForm() {
        if (!orStatus) {
            binding.rvTask.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        } else {
            binding.rvTask.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        binding.rvTask.setAdapter(adapter);
        App.getDataBase().getTaskDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                list = taskModels;
                adapter.addListOfModel(taskModels);
            }
        });
    }

    private void initRecycler() {
        binding.rvTask.setAdapter(adapter);
        getParentFragmentManager().setFragmentResultListener("send", getViewLifecycleOwner(), (requestKey, result) -> {
            TaskModel model = (TaskModel) result.getSerializable("model");
            TaskModel editedModel = (TaskModel) result.getSerializable("editedModel");
            if (model != null) {
                adapter.addModel(model);
            } else {
                adapter.editModel(editedModel, pos);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        adapter = new HomeAdapter(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_btn_change) {
            if (!orStatus) {
                item.setIcon(R.drawable.ic_baseline_widgets_24);
                binding.rvTask.setLayoutManager(linearLayoutManager);
                orStatus = true;
            } else {
                item.setIcon(R.drawable.ic_outline_list_24);
                binding.rvTask.setLayoutManager(staggeredGridLayoutManager);
                orStatus = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private void addTextListener() {
        binding.searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }

    private void filter(String text) {

        List<TaskModel> newList = new ArrayList<>();
        for (TaskModel item : adapter.list) {
            if (item.getTitle().contains(text)) {
                newList.add(item);
            }
        }
        if (binding.searchEt.getText().toString().isEmpty()) {
            adapter.listEmpty();
        } else {
            adapter.filterList(newList);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, TaskModel model) {
        pos = position;
        bundle.putSerializable("mod", model);
        bundle.putSerializable("position", position);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_nav_home_to_formFragment, bundle);

    }

    private void myDialog(TaskModel model) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("Подвердить", (dialog, which) -> {
            bundle.getSerializable("position");
            App.getDataBase().getTaskDao().delete(model);
            dialog.dismiss();
        });
        builder.setNegativeButton("Отмена", (dialog, which) -> {
            dialog.dismiss();

        });
        AlertDialog aDialog = builder.create();
        aDialog.show();
    }


    @Override
    public void onItemLongClick(int position, TaskModel model) {
        pos = position;
        bundle.putSerializable("mod", model);
        bundle.putSerializable("position", position);
        myDialog(model);
    }
}