package com.example.alpacamusclemaintenance.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.alpacamusclemaintenance.R

/**
 * A simple [Fragment] subclass.
 */
class PushUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_push_up, container, false)
        val button = rootView.findViewById<Button>(R.id.button)
        val textView = rootView.findViewById<TextView>(R.id.textView)

        var count = 0
        button.setOnClickListener {
            textView.text = (++count).toString()
        }

        return rootView
    }
}
