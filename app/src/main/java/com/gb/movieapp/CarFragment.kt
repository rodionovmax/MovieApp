package com.gb.movieapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class CarFragment : Fragment() {

    private var listener : IFragments ? = null
    private val carList: ArrayList<Car> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        carList.add(Car("Toyota", "Camry", 2007))
        carList.add(Car("BMW", "535", 2015))
        carList.add(Car("VW", "Tuareg", 2012))

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
        val textOutput = view.findViewById<TextView>(R.id.textview_car)

        // Display Car data class in TextView
        val carListStrings : ArrayList<String> = arrayListOf()
        var carText : String

        for (car in carList) {
            carText = car.convertToString()
            carListStrings.add(carText)
        }

        val separator = "\n"
        val stringToDisplay = carListStrings.joinToString(separator)

        textOutput.text = stringToDisplay

        // Handler for Back button
        btnBack.setOnClickListener {
            listener?.openMainFragment()
        }

    }
}




