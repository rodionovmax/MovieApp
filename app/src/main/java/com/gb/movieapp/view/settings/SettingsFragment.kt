package com.gb.movieapp.view.settings

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.movieapp.databinding.FragmentSettingsBinding
import com.gb.movieapp.isShowMoviesWithRating8
import com.gb.movieapp.setValueShowMovieWithRating8
import kotlinx.android.synthetic.main.fragment_settings.*

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
            settingsSwitchBtn.isChecked = requireActivity().isShowMoviesWithRating8()
            settingsSwitchBtn.setOnClickListener {
                if (settingsSwitchBtn.isChecked) {
                    requireActivity().setValueShowMovieWithRating8(true)
                } else {
                    requireActivity().setValueShowMovieWithRating8(false)
                }
            }
        }

    }


}