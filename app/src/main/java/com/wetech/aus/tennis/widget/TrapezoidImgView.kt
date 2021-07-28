package com.wetech.aus.tennis.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/7/28 10:34
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class TrapezoidImgView constructor(context: Context) : AppCompatImageView(context) {
    private val mTrapezoid: ShapeDrawable by lazy {
        val path = Path()
        path.moveTo(0.0f, 0.0f)
        path.lineTo(100.0f, 0.0f)
        path.lineTo(200.0f, 100.0f)
        path.lineTo(0.0f, 100.0f)
        path.lineTo(0.0f, 0.0f)
        ShapeDrawable(PathShape(path, 200f, 100f))

    }

    override fun onDraw(canvas: Canvas) {
        mTrapezoid.paint.style = Paint.Style.FILL_AND_STROKE
        mTrapezoid.paint.strokeWidth = 1.0f
        mTrapezoid.paint.color = Color.GREEN
        mTrapezoid.draw(canvas)
    }
}