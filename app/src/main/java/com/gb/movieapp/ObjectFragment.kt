package com.gb.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ObjectFragment : Fragment() {

    private lateinit var listener: IFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_object, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as IFragments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById(R.id.object_back_btn) as Button
        val textOutput1 = view.findViewById<TextView>(R.id.object_textview1)
        val textOutput2 = view.findViewById<TextView>(R.id.object_textview2)

        // Print Object into a textview
        textOutput1.text = Repo.getCarsList().toString()
        textOutput2.text = Repo.getCar().toString()

        // Handler for Back button
        btnBack.setOnClickListener {
            listener.openMainFragment()
        }
    }

}