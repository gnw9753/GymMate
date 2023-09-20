package com.example.gymmate.homepage;

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gymmate.R
import com.example.gymmate.data.ExerciseModel
import com.example.gymmate.databinding.FragHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var items = ArrayList<ExerciseModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView = binding.listView;
        val adapter = WorkoutAdapter(listView.context, R.layout.layout_workout, items)
        listView.adapter = adapter
        initData(listView.context)


        return root
    }


    private fun initData(context: Context) {
        items.clear();
       val helper=ExerciseHelper();
        items.addAll(helper.getData(context));

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}