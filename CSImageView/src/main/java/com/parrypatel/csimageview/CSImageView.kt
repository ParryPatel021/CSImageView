package com.parrypatel.csimageview

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.core.content.ContextCompat
import com.google.android.material.imageview.ShapeableImageView
import kotlin.math.roundToInt

class CSImageView : ShapeableImageView, Checkable {

    private var checked = false
    private var broadcasting = false
    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs,
        defStyle)

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

    fun setOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeListener?) {
        this.onCheckedChangeListener = onCheckedChangeListener
    }

    interface OnCheckedChangeListener {
        fun onCheckedChanged(csImageView: CSImageView?, isChecked: Boolean)
    }

    init {
        minimumHeight = resources.getDimension(R.dimen.dim_50).roundToInt()
        minimumWidth = resources.getDimension(R.dimen.dim_50).roundToInt()
        background = ContextCompat.getDrawable(context, R.color.selector_editor_options)
        setOnClickListener {
            toggle()
        }
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }

    override fun setChecked(checked: Boolean) {
        if (this.checked != checked) {
            this.checked = checked
            refreshDrawableState()

            // Avoid infinite recursions if setChecked() is called from a listener
            if (broadcasting) {
                return
            }
            broadcasting = true
            onCheckedChangeListener?.onCheckedChanged(this, checked)
            broadcasting = false
        }
    }

    override fun isChecked(): Boolean {
        return checked
    }

    override fun toggle() {
        isChecked = !isChecked
    }


}