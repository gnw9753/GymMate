package com.example.gymmate.funs;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymmate.databinding.FragFunsBinding

class FunsFragment : Fragment() {

    private var _binding: FragFunsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(FunsViewModel::class.java)

        _binding = FragFunsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val lineChart1=binding.lineChart1;
        val lineChart2=binding.lineChart2;
        val helper:FunsViewHelper=FunsViewHelper();
        helper.init(lineChart1);
        helper.init(lineChart2);

        val btnSetUser=binding.btnUser;
        val btnSetAlarm=binding.btnClarm;
        val btnWorkout=binding.btnWorkout;
        val btnDownload=binding.btnDownload;

        btnSetUser.setOnClickListener{
            helper.setUser(btnSetUser.context)
        }
        btnSetAlarm.setOnClickListener{
            helper.setAlarm(btnSetAlarm.context)
        }

        btnWorkout.setOnClickListener{
            helper.setWorkout(btnSetAlarm.context)
        }

        btnDownload.setOnClickListener{
            helper.setDownload(btnSetAlarm.context)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}