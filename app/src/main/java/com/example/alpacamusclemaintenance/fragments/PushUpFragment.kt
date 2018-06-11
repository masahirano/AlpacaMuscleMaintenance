package com.example.alpacamusclemaintenance.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import kotlinx.android.synthetic.main.fragment_push_up.view.*

/**
 * A simple [Fragment] subclass.
 */
class PushUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_push_up, container, false)

        var count = 0
        rootView.run {
            button.setOnClickListener { textView.text = (++count).toString() }
        }

        return rootView
    }
}
