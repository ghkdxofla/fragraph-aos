package com.depromeet.fragraph.feature.report.view.chart

import android.graphics.Canvas
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler


class ReportChartViewXAxisRenderer(
    viewPortHandler: ViewPortHandler?,
    xAxis: XAxis?,
    trans: Transformer?
) : XAxisRenderer(viewPortHandler, xAxis, trans) {

    override fun drawLabel(
        c: Canvas?,
        formattedLabel: String,
        x: Float,
        y: Float,
        anchor: MPPointF?, // 0.5, 0.0 으로 떨어짐
        angleDegrees: Float
    ) {
        val line = formattedLabel.split("\n".toRegex()).toTypedArray()
        Utils.drawXAxisValue(c, line[0], x, y, mAxisLabelPaint, anchor, angleDegrees)
        Utils.drawXAxisValue(
            c,
            line[1],
            x,
            y + mAxisLabelPaint.textSize,
            mAxisLabelPaint,
            anchor, // MPPointF(0.5f, 0f),
            angleDegrees
        )
    }
}