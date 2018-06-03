package com.example.alpacamusclemaintenance.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


/**
 * A simple [Fragment] subclass.
 */
class ThirdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_third, container, false)
        setupChart(rootView)

        return rootView
    }

    private fun setupChart(rootView: View) {
        val entries = ArrayList<BarEntry>().apply {
            add(BarEntry(1f, floatArrayOf(8f, 5f, 3f)))
            add(BarEntry(2f, floatArrayOf(7f, 3f, 5f)))
            add(BarEntry(3f, floatArrayOf(6f, 6f, 8f)))
            add(BarEntry(4f, floatArrayOf(8f, 5f, 3f)))
            add(BarEntry(5f, floatArrayOf(10f, 4f, 8f)))
            add(BarEntry(6f, floatArrayOf(5f, 6f, 3f)))
            add(BarEntry(7f, floatArrayOf(8f, 4f, 7f)))
        }

        val dataSet = BarDataSet(entries, "foo").apply {
            valueFormatter = IValueFormatter { value, entry, dataSetIndex, viewPortHandler -> value.toInt().toString() }
            colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
        }

        val chart = rootView.findViewById<BarChart>(R.id.chart).apply {
            data = BarData(dataSet)
            legend.isEnabled = false
            setScaleEnabled(false)
            animateY(1200, Easing.EasingOption.Linear)
        }
        chart.axisRight.run {
            setDrawGridLines(false)
            setDrawLabels(false)
        }
        chart.xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM
            valueFormatter = IndexAxisValueFormatter(arrayOf("", "5/25", "5/26", "5/27", "5/28", "5/29", "5/30", "6/1"))
            setDrawGridLines(false)
        }
    }
}

