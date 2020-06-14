package com.example.alpacamusclemaintenance.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.alpacamusclemaintenance.databinding.FragmentRecordBinding
import com.example.alpacamusclemaintenance.db.entity.PushUp
import com.example.alpacamusclemaintenance.viewmodel.RecordViewModel
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_record.view.*
import org.apache.commons.lang3.time.DateFormatUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class RecordFragment : Fragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var binding: FragmentRecordBinding
  private lateinit var viewModel: RecordViewModel
  private val disposable = CompositeDisposable()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
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

    viewModel =
      ViewModelProvider(viewModelStore, viewModelFactory)
        .get(RecordViewModel::class.java)

    // Set current date
    val formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy")
    binding.currentDate = LocalDateTime.now().format(formatter)

    // Set chart
    viewModel
      .pushUpsObservable
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe { pushUps ->
        setupChart(binding.root, pushUps)
      }
      .addTo(disposable)
  }

  override fun onDestroy() {
    super.onDestroy()
    disposable.clear()
  }

  private fun setupChart(
    rootView: View,
    pushUps: List<PushUp>
  ) {
    val dataList: Map<String, Int> =
      pushUps
        .sortedBy { it.doneAt }
        .groupingBy { DateFormatUtils.format(it.doneAt, "MM/dd") }
        .fold(0) { total, pushUp -> total + pushUp.count }
    val entries: List<BarEntry> =
      dataList
        .values
        .mapIndexed { index, totalCount -> BarEntry(index.toFloat(), totalCount.toFloat()) }
    val dataSet: BarDataSet =
      BarDataSet(entries, "push_ups")
        .apply {
          valueFormatter = IValueFormatter { value, _, _, _ -> value.toInt().toString() }
          colors = ColorTemplate.MATERIAL_COLORS.slice(0 until stackSize)
        }
    val chart: BarChart =
      rootView
        .chart
        .apply {
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

