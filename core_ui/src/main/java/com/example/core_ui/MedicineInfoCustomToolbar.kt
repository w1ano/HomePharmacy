package com.example.core_ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.core_ui.databinding.MedicineInfoCustomToolbarBinding

class MedicineInfoCustomToolbar
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : Toolbar(context, attrs, defStyleAttr) {

    private var _binding : MedicineInfoCustomToolbarBinding
    private var isMedicineCreated: Boolean = false

    init {
        val view = View.inflate(context, R.layout.medicine_info_custom_toolbar, this)
        _binding = MedicineInfoCustomToolbarBinding.bind(view)
        applyAttributes(attrs)
    }

    private fun applyAttributes(attrs: AttributeSet?) {
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.MedicineInfoCustomToolbar, 0, 0)
            with(attributes) {
                try {
                    isMedicineCreated = getBoolean(R.styleable.MedicineInfoCustomToolbar_isMedicineCreated, false)
                    if (isMedicineCreated) {
                        _binding.medicineInfoBackButton.visibility = View.VISIBLE
                        _binding.medicineInfoBackTextButton.visibility = View.GONE
                        _binding.medicineInfoRightButton.text = resources.getString(R.string.change_text)
                    }else {
                        _binding.medicineInfoBackButton.visibility = View.GONE
                        _binding.medicineInfoBackTextButton.visibility = View.VISIBLE
                        _binding.medicineInfoRightButton.text = resources.getString(R.string.add_text)
                    }
                } finally {
                    recycle()
                }
            }
        }
    }

    fun setOnClickListenerBackButton(click: (() -> Unit)? = null) {
        if (isMedicineCreated) {
            _binding.medicineInfoBackButton.setOnClickListener {
                click?.invoke()
            }
        }else {
            _binding.medicineInfoBackTextButton.setOnClickListener {
                click?.invoke()
            }
        }
    }

    fun setOnClickListenerRightButton(click: (() -> Unit)? = null) {
        _binding.medicineInfoRightButton.setOnClickListener {
            click?.invoke()
        }
    }
}