package com.gb.movieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class CarFragment : Fragment() {

    private var listener : IFragments ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as IFragments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<Button>(R.id.car_back_btn)

        btnBack.setOnClickListener {
            listener?.openMainFragment()
        }
    }
}




