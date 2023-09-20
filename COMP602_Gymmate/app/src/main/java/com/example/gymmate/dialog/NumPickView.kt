package com.example.gymmate.dialog

import java.lang.Math.abs
import java.lang.Math.min
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.example.gymmate.R
import java.util.ArrayList
import java.util.List

class NumPickView : ScrollView {
    var selectedTextColor = "#FF0000" //被选中的文字的颜色
    var unSelectedTextColor = "#FFFFFF" //未被选中的文字的颜色
    var dividerColor = "#eeeeee" //分割线的颜色
    var itemPadding = 10 //item上下的padding，单位dp
    var itemTextSize = 16 //item的字体大小，单位sp

    interface OnWheelViewListener {
        fun onSelected(selectedIndex: Int, item: String?)
    }

    private var context: Context? = null
    private var views: LinearLayout? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context)
    }

    var items: List<String>? = null
    private fun getItems(): List<String>? {
        return items
    }

    fun setItems(list: List<String?>?) {
        if (null == items) {
            items = ArrayList<String>()
        }
        items.clear()
        items.addAll(list)

        // 前面和后面补全
        for (i in 0 until offset) {
            items.add(0, "")
            items.add("")
        }
        initData()
    }

    var offset = OFF_SET_DEFAULT // 偏移量（需要在最前面和最后面补全）
    var displayItemCount = 0 // 每页显示的数量
    var selectedIndex = 1
    private fun init(context: Context) {
        this.context = context
        //Log.d(TAG, "parent: " + this.getParent());
        this.setVerticalScrollBarEnabled(false)
        views = LinearLayout(context)
        views.setOrientation(LinearLayout.VERTICAL)
        this.addView(views)
        scrollerTask = object : Runnable() {
            fun run() {
                val newY: Int = getScrollY()
                if (initialY - newY == 0) { // stopped
                    val remainder = initialY % itemHeight
                    val divided = initialY / itemHeight
                    if (remainder == 0) {
                        selectedIndex = divided + offset
                        onSeletedCallBack()
                    } else {
                        if (remainder > itemHeight / 2) {
                            this@NumPickView.post(object : Runnable() {
                                @Override
                                fun run() {
                                    this@NumPickView.smoothScrollTo(
                                        0,
                                        initialY - remainder + itemHeight
                                    )
                                    selectedIndex = divided + offset + 1
                                    onSeletedCallBack()
                                }
                            })
                        } else {
                            this@NumPickView.post(object : Runnable() {
                                @Override
                                fun run() {
                                    this@NumPickView.smoothScrollTo(0, initialY - remainder)
                                    selectedIndex = divided + offset
                                    onSeletedCallBack()
                                }
                            })
                        }
                    }
                } else {
                    initialY = getScrollY()
                    this@NumPickView.postDelayed(scrollerTask, newCheck)
                }
            }
        }
    }

    var initialY = 0
    var scrollerTask: Runnable? = null
    var newCheck = 50
    fun startScrollerTask() {
        initialY = getScrollY()
        this.postDelayed(scrollerTask, newCheck)
    }

    private fun initData() {
        displayItemCount = offset * 2 + 1
        for (item in items!!) {
            views.addView(createView(item))
        }
        refreshItemView(0)
    }

    var itemHeight = 0
    private fun createView(item: String): TextView {
        val tv = TextView(context)
        tv.setLayoutParams(
            LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        tv.setSingleLine(true)
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize)
        tv.setText(item)
        tv.setGravity(Gravity.CENTER)
        val padding = dip2px(itemPadding.toFloat())
        tv.setPadding(padding, padding, padding, padding)
        if (0 == itemHeight) {
            itemHeight = getViewMeasuredHeight(tv)
            //Log.d(TAG, "itemHeight: " + itemHeight);
            views.setLayoutParams(
                LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    itemHeight * displayItemCount
                )
            )
            val lp: LinearLayout.LayoutParams = this.getLayoutParams() as LinearLayout.LayoutParams
            this.setLayoutParams(LayoutParams(lp.width, itemHeight * displayItemCount))
        }
        return tv
    }

    @Override
    protected fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        refreshItemView(t)
        scrollDirection = if (t > oldt) { //向下滚动
            SCROLL_DIRECTION_DOWN
        } else { //向上滚动
            SCROLL_DIRECTION_UP
        }
    }

    private fun refreshItemView(y: Int) {
        var position = y / itemHeight + offset
        val remainder = y % itemHeight
        val divided = y / itemHeight
        if (remainder == 0) {
            position = divided + offset
        } else {
            if (remainder > itemHeight / 2) {
                position = divided + offset + 1
            }
        }
        val childSize: Int = views.getChildCount()
        for (i in 0 until childSize) {
            val itemView: TextView = views.getChildAt(i) as TextView ?: return
            if (position == i) {
                itemView.setTextColor(Color.parseColor(selectedTextColor)) //设置被选中的item的字体颜色
            } else {
                itemView.setTextColor(Color.parseColor(unSelectedTextColor))
            }
        }
    }

    /**
     * 获取选中区域的边界
     */
    var selectedAreaBorder: IntArray?
    private fun obtainSelectedAreaBorder(): IntArray {
        if (null == selectedAreaBorder) {
            selectedAreaBorder = IntArray(2)
            selectedAreaBorder!![0] = itemHeight * offset
            selectedAreaBorder!![1] = itemHeight * (offset + 1)
        }
        return selectedAreaBorder
    }

    private var scrollDirection = -1
    var paint: Paint? = null
    var viewWidth = 0
    @Override
    protected fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //Log.d(TAG, "w: " + w + ", h: " + h + ", oldw: " + oldw + ", oldh: " + oldh);
        viewWidth = w
        setBackgroundDrawable(null)
    }

    /**
     * 选中回调
     */
    private fun onSeletedCallBack() {
        if (null != onWheelViewListener) {
            try {
                onWheelViewListener!!.onSelected(selectedIndex, items!![selectedIndex])
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setSeletion(position: Int) {
        selectedIndex = position + offset
        this.post(object : Runnable() {
            @Override
            fun run() {
                this@NumPickView.smoothScrollTo(0, position * itemHeight)
            }
        })
    }

    val seletedItem: String
        get() = items!![selectedIndex]
    val seletedIndex: Int
        get() = selectedIndex - offset

    @Override
    fun fling(velocityY: Int) {
        super.fling(velocityY / 3)
    }

    @Override
    fun onTouchEvent(ev: MotionEvent): Boolean {
        if (ev.getAction() === MotionEvent.ACTION_UP) {
            startScrollerTask()
        }
        return super.onTouchEvent(ev)
    }

    var onWheelViewListener: OnWheelViewListener? = null

    private fun dip2px(dpValue: Float): Int {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun getViewMeasuredHeight(view: View): Int {
        val width: Int = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val expandSpec: Int =
            View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        view.measure(width, expandSpec)
        return view.getMeasuredHeight()
    }

    companion object {
        val TAG: String = NumPickView::class.java.getSimpleName()
        const val OFF_SET_DEFAULT = 1
        private const val SCROLL_DIRECTION_UP = 0
        private const val SCROLL_DIRECTION_DOWN = 1
    }
}