package com.example.alpacamusclemaintenance.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import com.example.alpacamusclemaintenance.presentation.databinding.FragmentRecordBinding
import com.example.alpacamusclemaintenance.presentation.viewmodel.RecordViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.android.synthetic.main.fragment_record.view.*
import org.apache.commons.lang3.time.DateFormatUtils

@AndroidEntryPoint
class RecordFragment : Fragment() {

    private lateinit var binding: FragmentRecordBinding
    private val viewModel: RecordViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecordBinding.inflate(
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

        // Set current date
        val formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy")
        binding.currentDate = LocalDateTime.now().format(formatter)

        // Set chart
        viewModel.pushUpsObservableEntity.observe(viewLifecycleOwner) { pushUps ->
            setupChart(binding.root, pushUps)
        }
    }

    private fun setupChart(
        rootView: View,
        pushUpEntities: List<PushUp>
    ) {
        val dataList: Map<String, Int> = pushUpEntities
            .sortedBy { it.doneAt }
            .groupingBy { DateFormatUtils.format(it.doneAt, "MM/dd") }
            .fold(0) { total, pushUp -> total + pushUp.count }
        val entries: List<BarEntry> = dataList.values.mapIndexed { index, totalCount ->
            BarEntry(index.toFloat(), totalCount.toFloat())
        }
        val dataSet: BarDataSet = BarDataSet(entries, "push_ups").apply {
            valueFormatter = IValueFormatter { value, _, _, _ -> value.toInt().toString() }
            colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
        }
        val chart: BarChart = rootView.chart.apply {
            data = BarData(dataSet)
            legend.isEnabled = false
            setScaleEnabled(false)
            animateY(1200, Easing.EasingOption.Linear)
        }

        chart.axisRight.apply {
            setDrawGridLines(false)
            setDrawLabels(false)
        }
        chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            labelCount = dataList.size
            valueFormatter = IndexAxisValueFormatter(dataList.keys)
            setDrawGridLines(false)
        }
    }
}
