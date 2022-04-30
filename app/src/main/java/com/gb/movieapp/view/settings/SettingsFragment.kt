package com.gb.movieapp.view.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.movieapp.databinding.FragmentSettingsBinding

private const val RATING_8_AND_HIGHER = "RATING_8"

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // If switch is checked save true in shared preferences
        // to display only movies with rating 8 and higher
        with(binding) {
            settingsSwitchBtn.setOnClickListener {
                if (settingsSwitchBtn.isChecked) {
                    showMoviesWithRatingEight(true)
                } else {
                    showMoviesWithRatingEight(false)
                }
            }
        }

    }

    // Write boolean to shared preferences
    private fun showMoviesWithRatingEight(isRatingEight: Boolean) {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(RATING_8_AND_HIGHER, isRatingEight)
                apply()
            }
        }
    }


}