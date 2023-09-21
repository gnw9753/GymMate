package com.example.gymmate.homepage

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.gymmate.R
import com.example.gymmate.data.ExerciseModel

class WorkoutAdapter(context: Context, val resourceId: Int, data: List<ExerciseModel>) : ArrayAdapter<ExerciseModel>(context, resourceId, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val txtDate: TextView = view.findViewById(R.id.txt_date)
        val txtitems: TextView = view.findViewById(R.id.txt_items)
        val item = getItem(position)
        if (item!=null){
            txtitems.text=item.name;
            txtDate.text = item.day
        }
        return view
    }
}