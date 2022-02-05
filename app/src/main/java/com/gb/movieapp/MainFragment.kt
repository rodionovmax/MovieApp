package com.gb.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MainFragment : Fragment() {

    private var listener : IFragments ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = activity as IFragments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = view.findViewById<Button>(R.id.cta_button)
        val txtView = view.findViewById<TextView>(R.id.text_view)
        val btnReset = view.findViewById<Button>(R.id.reset_button)
        val btnCar = view.findViewById<Button>(R.id.btn_data_class)
        val btnFruits = view.findViewById<Button>(R.id.btn_object)
        val btnLoops = view.findViewById<Button>(R.id.btn_loops)

        btnStart.setOnClickListener {
            txtView.text = "Let's get the party started!"
        }

        btnReset.setOnClickListener {
            txtView.text = ""
        }

        btnCar.setOnClickListener {
            listener?.openCarFragment()
        }

        btnFruits.setOnClickListener {
            listener?.openObjectFragment()
        }

        btnLoops.setOnClickListener {
            listener?.openLoopsFragment()
        }

    }



}