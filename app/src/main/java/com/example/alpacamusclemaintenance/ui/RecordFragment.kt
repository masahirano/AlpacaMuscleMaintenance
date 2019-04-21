package com.example.alpacamusclemaintenance.ui

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.alpacamusclemaintenance.R
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.util.InjectorUtils
import com.example.alpacamusclemaintenance.viewmodel.PushUpViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_record.view.*
import org.apache.commons.lang3.time.DateFormatUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import com.example.alpacamusclemaintenance.databinding.FragmentRecordBinding


/**
 * A simple [Fragment] subclass.
 */
class RecordFragment : Fragment(), OnChartValueSelectedListener {
    private lateinit var viewModel: PushUpViewModel
    private var currentDate: LocalDate = LocalDate.now()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRecordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_record, container, false)
//        val rootView = inflater.inflate(R.layout.fragment_record, container, false)

        val pushUpViewModelFactory = InjectorUtils.providePushUpViewModelFactory(context!!)
        viewModel = ViewModelProviders.of(this, pushUpViewModelFactory).get(PushUpViewModel::class.java)
        subscribeUi(binding.root)

        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        binding.date = formatter.format(currentDate)
//        binding.textView2

//        binding.finishButton.setOnClickListener {
//            if (binding.count > 0) {
//                GlobalScope.launch {
//                    val database = AppDatabase.getInstance(context!!)
//                    database.pushUpDao().insert(PushUp(0, binding.count))
//                    binding.count = 0
//                }
//            }
//        }



        return binding.root
    }

    private fun subscribeUi(rootView: View) {
        viewModel.getPushUps().observe(this, Observer { pushUps ->
            pushUps?.let {
                setupChart(rootView, it)
            }
        })
    }

    private fun setupChart(rootView: View, pushUps: List<PushUp>) {
        val dataList = pushUps.reversed().groupingBy { DateFormatUtils.format(it.doneAt, "MM/dd") }
                .fold(0) { total, pushUp -> total + pushUp.count }



        val beginningOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth())
        val endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())
        val foo: ArrayMap<String, Int> = ArrayMap()
        val formatter = DateTimeFormatter.ofPattern("MM/dd")
        foo[formatter.format(beginningOfMonth)] = dataList.getOrDefault(formatter.format(beginningOfMonth), 0)
        val days = beginningOfMonth.until(endOfMonth).days
        repeat(days) { index ->
            val date = formatter.format(beginningOfMonth.plusDays((index + 1).toLong()))
            foo[date] = dataList.getOrDefault(date, 0)
        }



//        val entries = dataList.values.mapIndexed { index, totalCount -> BarEntry(index.toFloat(), totalCount.toFloat()) }
        val entries = foo.values.mapIndexed { index, totalCount -> BarEntry(index.toFloat(), totalCount.toFloat()) }
        val dataSet = BarDataSet(entries, "push_ups").apply {
//            valueFormatter = IValueFormatter { value, entry, dataSetIndex, viewPortHandler ->
//                if (value.toInt() == 0) null else value.toInt().toString()
//            }
            setDrawValues(false)
            colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
        }
        val chart = rootView.chart.apply {
            data = BarData(dataSet)
            legend.isEnabled = false
            description.isEnabled = false
            setScaleEnabled(false)
            animateY(600, Easing.EasingOption.Linear)
        }

        chart.axisLeft.run {
            setDrawGridLines(false)
            setDrawLabels(false)
        }
        chart.xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM
            labelCount = pushUps.size
//            valueFormatter = IndexAxisValueFormatter(dataList.keys)
            valueFormatter = IndexAxisValueFormatter(foo.keys)
            setDrawGridLines(false)
        }
        chart.setOnChartValueSelectedListener(this)
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {
//        val dialog = EditRecordDialogFragment()
//        dialog.show(fragmentManager, "edit_record")
        Log.d("INFO", "foo")
    }

    override fun onNothingSelected() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.d("INFO", "not implemented")
    }
}

