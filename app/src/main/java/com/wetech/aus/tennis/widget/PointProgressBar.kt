package com.wetech.aus.tennis.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.ImageUtils
import com.wetech.aus.tennis.app.R

/**
 * @Author: pokerfaceCmy
 * @Date: 2021/5/20 16:09
 * @Desc: TODO
 * @GitHub：https://github.com/pokerfaceCmy
 */
class PointProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {
    private var mWidth = 0

    private val mBitmap: Bitmap by lazy {
        ImageUtils.drawable2Bitmap(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.ic_progress_point,
                null
            )
        )
    }

    private val mPaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = measuredWidth - paddingLeft - paddingRight
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            val radio = progress * 1.0f / max
            val progressPos = mWidth * radio

            canvas.save()
            canvas.translate(
                this@PointProgressBar.paddingLeft.toFloat(),
                (this@PointProgressBar.height / 2).toFloat()
            )
            val x: Float = progressPos - mBitmap.width / 2
            val y: Float = ((-mBitmap.height) / 2).toFloat()
            canvas.drawBitmap(mBitmap, x, y, mPaint)
            canvas.restore()
        }

    }

}