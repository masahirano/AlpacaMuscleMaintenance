package com.example.alpacamusclemaintenance.presentation.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alpacamusclemaintenance.domain.pushup.PushUp
import com.example.alpacamusclemaintenance.presentation.R
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
import org.apache.commons.lang3.time.DateFormatUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class RecordFragment : Fragment() {

//    private val viewModel: RecordViewModel by activityViewModels()
//    private val formatter: DateTimeFormatter by lazy {
//        DateTimeFormatter.ofPattern(getString(R.string.current_date_format))
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MaterialTheme {
                RecordView()
            }
        }
    }

//    private fun setupChart(
//        chart: BarChart,
//        pushUpEntities: List<PushUp>
//    ): BarChart {
//        val dataList: Map<String, Int> = pushUpEntities
//            .sortedBy { it.doneAt }
//            .groupingBy { DateFormatUtils.format(it.doneAt, "MM/dd") }
//            .fold(0) { total, pushUp -> total + pushUp.count }
//        val entries: List<BarEntry> = dataList.values.mapIndexed { index, totalCount ->
//            BarEntry(index.toFloat(), totalCount.toFloat())
//        }
//        val dataSet: BarDataSet = BarDataSet(entries, "push_ups").apply {
//            valueFormatter = IValueFormatter { value, _, _, _ -> value.toInt().toString() }
//            colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
//        }
//
//        return chart.apply {
//            data = BarData(dataSet)
//            legend.isEnabled = false
//            setScaleEnabled(false)
//            animateY(1_200, Easing.EasingOption.Linear)
//
//            axisRight.apply {
//                setDrawGridLines(false)
//                setDrawLabels(false)
//            }
//
//            xAxis.apply {
//                position = XAxis.XAxisPosition.BOTTOM
//                labelCount = dataList.size
//                valueFormatter = IndexAxisValueFormatter(dataList.keys)
//                setDrawGridLines(false)
//            }
//        }
//    }
}

@Composable
fun RecordView(viewModel: RecordViewModel = hiltViewModel()) {
    val pushUpEntities by viewModel.pushUpsEntities.observeAsState(emptyList())
    val formatter: DateTimeFormatter = DateTimeFormatter
        .ofPattern(stringResource(R.string.current_date_format))

    val setupChart: (BarChart) -> BarChart = { chart ->
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

        chart.apply {
            data = BarData(dataSet)
            legend.isEnabled = false
            setScaleEnabled(false)
            animateY(1_200, Easing.EasingOption.Linear)

            axisRight.apply {
                setDrawGridLines(false)
                setDrawLabels(false)
            }

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                labelCount = dataList.size
                valueFormatter = IndexAxisValueFormatter(dataList.keys)
                setDrawGridLines(false)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = LocalDateTime.now().format(formatter))

        Spacer(modifier = Modifier.height(32.dp))
        AndroidView(
            factory = { context ->
                BarChart(context)
            },
            modifier = Modifier.fillMaxSize(),
            update = { barChart ->
                setupChart(barChart)
            }
        )
    }
}
