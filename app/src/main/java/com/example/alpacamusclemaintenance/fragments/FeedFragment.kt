package com.example.alpacamusclemaintenance.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.utilities.VolleyRequestQueueManager

/**
 * A simple [Fragment] subclass.
 *
 */
class FeedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        val url = "https://qiita.com/api/v2/items?query=tag:筋トレ"
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    System.out.println("successssssssssssss")
                },
                Response.ErrorListener { error ->
                    System.out.println("errorrrrrrrrrrrrrrr")
                }
        )

        // Access the RequestQueue through your singleton class.
        VolleyRequestQueueManager.getInstance(context!!).addToRequestQueue(jsonArrayRequest)

        return rootView
    }
}
