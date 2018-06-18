package com.example.alpacamusclemaintenance.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.db.AppDatabase
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.repository.PushUpRepository
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_record.view.*


/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment() {

    private lateinit var viewModel: PushUpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_record, container, false)

        // TODO: Use ViewModelFactory to instantiate ViewModel
//        viewModel = ViewModelProviders.of(this).get(PushUpViewModel::class.java)
        val repository = PushUpRepository.getInstance(AppDatabase.getInstance(context!!).pushUpDao())
        viewModel = PushUpViewModel(repository)
        subscribeUi(rootView)

        return rootView
    }

    private fun subscribeUi(rootView: View) {
        viewModel.getPushUps().observe(this, Observer { pushUps ->
            pushUps?.let {
                setupChart(rootView, it)
            }
        })
    }

    private fun setupChart(rootView: View, pushUps: List<PushUp>) {
        val entries = pushUps.mapIndexed { index, pushUp ->
            BarEntry(index.toFloat(), pushUp.count.toFloat())
        }
        val dataSet = BarDataSet(entries, "push_ups").apply {
            valueFormatter = IValueFormatter { value, entry, dataSetIndex, viewPortHandler -> value.toInt().toString() }
            colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
        }
        val chart = rootView.chart.apply {
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

