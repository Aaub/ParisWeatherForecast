package com.aaub.parisweatherforecast.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatTextView
import com.aaub.parisweatherforecast.R

class SeparatorTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @ColorInt
    private var colorLine: Int = 0

    @Dimension
    private var dividerTextPadding: Float = 0.toFloat()

    init {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SeparatorTextView,
            defStyleAttr,
            0
        )
        try {
            colorLine = a.getColor(R.styleable.SeparatorTextView_dividerColor, Color.BLACK)
            dividerTextPadding =
                a.getDimension(R.styleable.SeparatorTextView_dividerTextPadding, 16f)
        } finally {
            a.recycle()
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = colorLine
        val top = (height + paddingTop - paddingBottom) / 2
        val left = paddingLeft
        val right = width - paddingRight
        val bottom = top + 2
        val horizontalCenter = (width + paddingLeft - paddingRight) / 2

        val textWidth = paint.measureText(text.toString()).toInt()
        val halfTextWidth = textWidth / 2

        val gravity = gravity

        when {
            gravity and Gravity.END == Gravity.END -> canvas.drawRect(
                left.toFloat() + textWidth.toFloat() + dividerTextPadding,
                top.toFloat(),
                right.toFloat(),
                bottom.toFloat(),
                paint
            )
            gravity and Gravity.START == Gravity.START -> canvas.drawRect(
                left.toFloat(),
                top.toFloat(),
                right.toFloat() - textWidth.toFloat() - dividerTextPadding,
                bottom.toFloat(),
                paint
            )
            else -> {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    horizontalCenter.toFloat() - halfTextWidth.toFloat() - dividerTextPadding,
                    bottom.toFloat(),
                    paint
                )
                canvas.drawRect(
                    horizontalCenter.toFloat() + halfTextWidth.toFloat() + dividerTextPadding,
                    top.toFloat(),
                    right.toFloat(),
                    bottom.toFloat(),
                    paint
                )
            }
        }
    }
}
