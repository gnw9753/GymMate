package com.example.gymmate.ui.workout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.gymmate.R;
import com.example.gymmate.data.WorkoutItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends Activity {

    List<Map<String, Object>> list;
    WorkoutItem workoutItem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        workoutItem=(WorkoutItem)getIntent().getSerializableExtra("data");
        setContentView(R.layout.layout_detail);
        ListView listView=(ListView) findViewById(R.id.listview);
        TextView textView=(TextView) findViewById(R.id.title);
        textView.setText(workoutItem.getDate());
        getData(workoutItem.getContents());
        listView.setAdapter(new SimpleAdapter(this,list,R.layout.simple_item,new String[]{"name"},  new int[]{R.id.text1}));
    }

    private void getData(List<String> str) {
        list = new ArrayList<Map<String, Object>>();
        for(String s:str)
        {
            Map map = new HashMap<String, Object>();
            map.put("name", s);
            list.add(map);
        }
    }
}
