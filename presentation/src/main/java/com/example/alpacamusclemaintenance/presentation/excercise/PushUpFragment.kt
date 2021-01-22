package com.example.alpacamusclemaintenance.presentation.excercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alpacamusclemaintenance.presentation.databinding.FragmentPushUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PushUpFragment : Fragment() {

    private lateinit var binding: FragmentPushUpBinding
    private val viewModel: PushUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPushUpBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.count.observe(viewLifecycleOwner) { value ->
            binding.count = value
        }

        binding.onAddClicked = View.OnClickListener {
            viewModel.add(1)
        }
        binding.onSaveClicked = View.OnClickListener {
            viewModel.save()
        }
    }
}
