package com.example.gymmate.ui.calories;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymmate.R;
import com.example.gymmate.data.FoodItem;
import com.example.gymmate.data.MyStorage;
import com.example.gymmate.databinding.FragmentDashboardBinding;
import com.example.gymmate.dialog.DialogManager;
import com.example.gymmate.dialog.DialogView;
import com.example.gymmate.dialog.NumPickView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaloriesFragment extends Fragment {

    private FragmentDashboardBinding binding;

    ListView foodList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CaloriesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(CaloriesViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        PieChart pieChart = binding.pie;
        TextView textView=binding.text;

        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"protein"));
        strings.add(new PieEntry(70f,"carbs"));
        PieDataSet dataSet = new PieDataSet(strings,"");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setUsePercentValues(true);

        pieChart.setData(pieData);
        pieChart.invalidate();



        StringBuffer sb=new StringBuffer();
        sb.append("Calories need");
        sb.append("\n");
        sb.append("400 protein");
        sb.append("\n");
        textView.setText(sb.toString());

        Button btn_add_food=binding.btnAddFood;
        Button btn_add_weight=binding.btnAddWeight;
        btn_add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DialogView dialogView= DialogManager.getInstance().initView(getContext(), R.layout.layout_add_food, Gravity.BOTTOM);
               dialogView.setCanceledOnTouchOutside(true);
                Spinner spinner1=dialogView.findViewById(R.id.spinner1);
                List<String> list = new ArrayList<String>();
                list.add("Egg");
                list.add("Milk");
                list.add("Beef");
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);

                Spinner spinner2=dialogView.findViewById(R.id.spinner2);
                List<String> list1 = new ArrayList<String>();
                list1.add("Small(10g)");
                list1.add("Medium(100g)");
                list1.add("Large(500g)");
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,list1);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter1);

                Button btnConfirm=dialogView.findViewById(R.id.btn_confirm);

                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String food=spinner1.getSelectedItem().toString();
                        String size=spinner2.getSelectedItem().toString();
                        FoodItem foodItem=new FoodItem();
                        foodItem.size=size;
                        foodItem.food=food;
                        MyStorage.foodItems.add(foodItem);
                        foodList.setAdapter(
                                new SimpleAdapter(getContext(),getData(),R.layout.simple_food_item,new String[]{"food","size"},
                                        new int[]{R.id.text_food,R.id.text_size}));
                        DialogManager.getInstance().hide(dialogView);
                    }
                });

               DialogManager.getInstance().show(dialogView);
            }
        });

        btn_add_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogView dialogView= DialogManager.getInstance().initView(getContext(), R.layout.picker_layout, Gravity.BOTTOM);
                dialogView.setCanceledOnTouchOutside(true);
                NumPickView pickView=dialogView.findViewById(R.id.numPickView);
                List<String> list= Arrays.asList("66kg","67kg","68kg","69kg","70kg","71kg","72kg");
                pickView.setItems(list);
                pickView.setSeletion(2);
                pickView.setOnWheelViewListener(new NumPickView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        //tv.setText("您选择的英雄是："+selectedIndex+"-"+item);
                    }
                });
                DialogManager.getInstance().show(dialogView);

                Button btnConfirm=dialogView.findViewById(R.id.btn_confirm);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogManager.getInstance().hide(dialogView);
                    }
                });
            }
        });

        foodList=binding.listFood;
        if(MyStorage.foodItems==null)
        {
            MyStorage.foodItems=new ArrayList<>();
            FoodItem foodItem=new FoodItem();
            foodItem.food="Egg";
            foodItem.size="10g";
            MyStorage.foodItems.add(foodItem);
        }
        foodList.setAdapter(
                new SimpleAdapter(getContext(),getData(),R.layout.simple_food_item,new String[]{"food","size"},
                new int[]{R.id.text_food,R.id.text_size}));

        return root;
    }

    public  List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list=new ArrayList<>();
        for(FoodItem item:MyStorage.foodItems)
        {
            Map<String, Object> obj=new HashMap<>();
            obj.put("food",item.food);
            obj.put("size",item.size);
            list.add(obj);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}