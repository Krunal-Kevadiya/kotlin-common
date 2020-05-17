package com.kotlinlibrary.swiperefreshlayout;

import android.content.Context;
import android.view.animation.Animation;
import androidx.appcompat.widget.AppCompatImageView;

class AnimationImageView extends AppCompatImageView {

    private Animation.AnimationListener mListener;

    public AnimationImageView(Context context) {
        super(context);
    }

    public void setAnimationListener(Animation.AnimationListener listener) {
        mListener = listener;
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
        if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
        if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
    }
}