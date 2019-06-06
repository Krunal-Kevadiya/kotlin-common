package com.kotlinlibrary.buttonview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.kotlinlibrary.R

class ProgressButton: AppCompatButton {
    var progressProps: ProgressProps = ProgressProps()

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0)
        with(progressProps) {
            pbGradientAngle = typedArray.getFloat(R.styleable.ProgressButton_pb_gradient_angle, 0f)
            typedArray.getResourceId(R.styleable.ProgressButton_pb_gradient_colors, 0).run {
                if(this != 0) {
                    pbGradientColor = context.resources.getIntArray(this)
                }
            }
            typedArray.getString(R.styleable.ProgressButton_pb_gradient_positions).run {
                if(!this.isNullOrEmpty()) {
                    pbGradientPositions = ResourceHelper.getFloatArray(context, this).toFloatArray()
                }
            }
            pbNormalColor = typedArray.getColor(R.styleable.ProgressButton_pb_normal_color, Color.TRANSPARENT)
            pbCornerTopLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_topLeftRadius, 0)
            pbCornerTopRightRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_topRightRadius, 0)
            pbCornerBottomLeftRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_bottomLeftRadius, 0)
            pbCornerBottomRightRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_corner_bottomRightRadius, 0)
            pbStateSuccessBackgroundColor = typedArray.getColor(R.styleable.ProgressButton_pb_state_success_backgroundColor, Color.TRANSPARENT)
            pbStateSuccessDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_state_success_drawable, 0)
            pbStateSuccessDrawableTint = typedArray.getColor(R.styleable.ProgressButton_pb_state_success_drawableTint, Color.TRANSPARENT)
            pbStateFailureBackgroundColor = typedArray.getColor(R.styleable.ProgressButton_pb_state_failure_backgroundColor, Color.TRANSPARENT)
            pbStateFailureDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_state_failure_drawable, 0)
            pbStateFailureDrawableTint = typedArray.getColor(R.styleable.ProgressButton_pb_state_failure_drawableTint, Color.TRANSPARENT)
            pbImageDrawable = typedArray.getResourceId(R.styleable.ProgressButton_pb_image_drawable, 0)
            pbImageDrawableTint = typedArray.getColor(R.styleable.ProgressButton_pb_image_drawableTint, Color.TRANSPARENT)
            pbAnimDuration = typedArray.getInt(R.styleable.ProgressButton_pb_anim_duration, 300)
            pbAnimCornerRadius = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_anim_corner_radius, 0)
            pbAnimAlphaMorphing = typedArray.getBoolean(R.styleable.ProgressButton_pb_anim_alpha_morphing, false)
            pbProgressStyle = ProgressStyle.values()[typedArray.getInt(R.styleable.ProgressButton_pb_progress_style, 0)]
            pbProgressColor = typedArray.getColor(R.styleable.ProgressButton_pb_progress_color, Color.TRANSPARENT)
            pbButtonStyle = ButtonStyle.values()[typedArray.getInt(R.styleable.ProgressButton_pb_button_style, 0)]
            pbStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.ProgressButton_pb_stroke_width, 0)
            pbStrokeColor = typedArray.getColor(R.styleable.ProgressButton_pb_stroke_color, Color.TRANSPARENT)
            pbStateWithStroke = typedArray.getBoolean(R.styleable.ProgressButton_pb_state_with_stroke, false)
            pbStateWithRevert = typedArray.getBoolean(R.styleable.ProgressButton_pb_state_with_revert, false)
            pbStateRevertDuration = typedArray.getInt(R.styleable.ProgressButton_pb_state_revert_duration, 300)
            pbButtonShape = ButtonShape.values()[typedArray.getInt(R.styleable.ProgressButton_pb_button_shape, 0)]
            pbProgressDotsCount = typedArray.getInt(R.styleable.ProgressButton_pb_progress_dots_count, 0)
        }
        typedArray.recycle()
    }
}