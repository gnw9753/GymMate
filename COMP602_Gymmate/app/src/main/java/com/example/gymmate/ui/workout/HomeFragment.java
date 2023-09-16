package com.example.gymmate.ui.workout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gymmate.R;
import com.example.gymmate.data.WorkoutItem;
import com.example.gymmate.databinding.FragmentHomeBinding;
import com.example.gymmate.ui.view.OnRecyclerViewItemClickListener;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    HomeViewModel homeViewModel;

    RecyclerView listView;


    List<WorkoutItem> workoutItemList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initRoot(root);
        return root;
    }

    private void initRoot(View view)
    {

        workoutItemList = homeViewModel.getItems();
        listView=view.findViewById(R.id.my_list);
        getListAdapter();
    }

    private void getListAdapter()
    {
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        WorkoutListAdapter adapter=new WorkoutListAdapter(workoutItemList);
        //adapter.test();

        adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Intent intent=new Intent(getActivity(),DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",adapter.getPos(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(RecyclerView parent, View view, int position) {

            }
        });
        listView.setAdapter(adapter);

    }

    public void update()
    {
        WorkoutListAdapter adapter=new WorkoutListAdapter(workoutItemList);
        //adapter.test();

        adapter.setOnRecyclerViewItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Intent intent=new Intent(getActivity(),DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",adapter.getPos(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(RecyclerView parent, View view, int position) {

            }
        });
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}