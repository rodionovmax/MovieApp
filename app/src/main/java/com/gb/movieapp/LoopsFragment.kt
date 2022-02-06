package com.gb.movieapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class LoopsFragment : Fragment() {

    private lateinit var listener: IFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loops, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as IFragments
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<Button>(R.id.loops_back_btn)
        val textOutput1 = view.findViewById<TextView>(R.id.loops_textview1)
        val textOutput2 = view.findViewById<TextView>(R.id.loops_textview2)
        val textOutput3 = view.findViewById<TextView>(R.id.loops_textview3)
        val textOutput4 = view.findViewById<TextView>(R.id.loops_textview4)

        // Foreach loop
        val carsList : List<Car> = Repo.getCarsList()
        val brandsList: ArrayList<String> = arrayListOf()
        for (car in carsList) {
            brandsList.add(car.brand)
        }
        textOutput1.text = brandsList.toString()

        // Closed range loop
        val rangeList: ArrayList<String> = arrayListOf()
        for (i in 1..10) {
            rangeList.add("$i ")
        }
        textOutput2.text = rangeList.toString()

        // Reversed range with step
        val reversedRangeList: ArrayList<String> = arrayListOf()
        for (i in 10 downTo 1 step 2) {
            reversedRangeList.add("$i ")
        }
        textOutput3.text = reversedRangeList.toString()

        // Range loop with until
        val car = Repo.getCar()
        var result = ""
        for (i in 0 until carsList.size) {
            if (carsList[i] == car) {
                result = "found"
                break
            } else {
                result = "not found"
            }
        }
        textOutput4.text = result

        // Handler for Back button
        btnBack.setOnClickListener {
            listener.openMainFragment()
        }
    }

    fun firstLoop() {
        val carsList : List<Car> = Repo.getCarsList()
        for (car in carsList) {

        }
    }


}