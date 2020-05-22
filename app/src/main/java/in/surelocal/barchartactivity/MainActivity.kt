package `in`.surelocal.barchartactivity

import android.graphics.Insets.add
import android.graphics.Path
import android.os.Bundle
import android.service.autofill.FillContext
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.red
import androidx.core.view.OneShotPreDrawListener.add
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class MainActivity : AppCompatActivity() {

    private lateinit var chart :BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chartActivity()
//        setData()
    }



    private fun chartActivity() {
        chart=findViewById(R.id.chart1)
      //  chart.setOnChartValueSelectedListener(this)
        chart.setDrawBarShadow(false)
        chart.description.isEnabled=false
        chart.setMaxVisibleValueCount(60)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)

        val xAxisFormatter=DayAxisValueFormatter(chart)
        val xAxis = chart.xAxis
        xAxis.setPosition(XAxisPosition.BOTTOM)
        xAxis.typeface= tfLight
        xAxis.setDrawGridLines(false)
        xAxis.granularity=1f
        xAxis.labelCount=7
        xAxis.setValueFormatter(xAxisFormatter)

        val custom: ValueFormatter = MyValueFormatter("$")

        val leftAxis = chart.axisLeft
        leftAxis.typeface = tfLight
        leftAxis.setLabelCount(8, false)
        leftAxis.valueFormatter = custom
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.spaceTop = 15f
        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)

         val rightAxis=chart.axisRight

        rightAxis.setDrawGridLines(false)
        rightAxis.typeface = tfLight
        rightAxis.setLabelCount(8, false)
        rightAxis.valueFormatter = custom
        rightAxis.spaceTop = 15f
        rightAxis.axisMinimum = 0f

        val l=chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f


        val mv = MarkerView(this, 10)
        mv.setChartView(chart) // For bounds control
        chart.marker = mv
    }


    private fun setData(count int ,range float ) {
        val start = 1f
        val values = ArrayList<BarEntry>()



            var i = start.toInt()
             while (i < start) {
                val `val` = (Math.random() *(range + 1)) as Float
                if (Math.random() * 100 < 25) {
                    values.add(BarEntry(i, `val`, resources.getDrawable(R.drawable.star)))
                } else {
                    values.add(BarEntry(i, `val`))
                }
                i++

        }
        var set1: BarDataSet


        if (chart.data != null &&
                chart.data.dataSetCount > 0) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "The year 2017")
            set1.setDrawIcons(false)
            val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark)
            val endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple)
            val endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark)
            val endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark)
            val endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark)
//            val gradientFills: MutableList<Fill> = java.util.ArrayList<Fill>()
            val gradientFills=ArrayList<Path.FillType>()


            gradientFills.add(Fill(startColor1,endColor1))
            gradientFills.add(Fill(startColor2, endColor2))
            gradientFills.add(Fill(startColor3, endColor3))
            gradientFills.add(Fill(startColor4, endColor4))
            gradientFills.add(Fill(startColor5, endColor5))
            set1.setFills(gradientFills)
            val dataSets = java.util.ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.setValueTextSize(10f)
            data.setValueTypeface(tfLight)
            data.barWidth = 0.9f
            chart.data = data
        }
    }


    }

