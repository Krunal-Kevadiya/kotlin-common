package com.kotlinlibrary.swiperefreshlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;

public class WaveSwipeRefreshLayout extends ViewGroup implements ViewTreeObserver.OnPreDrawListener {

    private enum VERTICAL_DRAG_THRESHOLD {
        FIRST(0.1f), SECOND(0.16f + FIRST.val), THIRD(0.5f + FIRST.val);

        final float val;

        VERTICAL_DRAG_THRESHOLD(float val) {
            this.val = val;
        }
    }

    private enum STATE {
        REFRESHING, PENDING
    }

    private enum EVENT_PHASE {
        WAITING, BEGINNING, APPEARING, EXPANDING, DROPPING;
    }

    private static final float DECELERATE_INTERPOLATION_FACTOR = 2f;

    private static final int INVALID_POINTER = -1;

    private static final float DRAGGING_WEIGHT = 0.5f;

    private static final float MAX_PROGRESS_ROTATION_RATE = 0.8f;

    private static final int SCALE_DOWN_DURATION = 200;

    private static final int ANIMATE_TO_TRIGGER_DURATION = 200;

    private static final int DEFAULT_CIRCLE_TARGET = 64;

    private View mTarget;

    private OnRefreshListener mListener;

    private STATE mState = STATE.PENDING;

    private EVENT_PHASE mEventPhase = EVENT_PHASE.WAITING;

    private final DecelerateInterpolator mDecelerateInterpolator;

    private ProgressAnimationImageView mCircleView;

    private WaveView mWaveView;

    private boolean mNotify;

    private boolean mIsManualRefresh = false;

    private float mFirstTouchDownPointY;

    private boolean mIsBeingDropped;

    private int mActivePointerId = INVALID_POINTER;

    private int mTopOffset;

    public WaveSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public WaveSwipeRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveSwipeRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getViewTreeObserver().addOnPreDrawListener(this);

        setWillNotDraw(false);
        mDecelerateInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);

        ViewCompat.setChildrenDrawingOrderEnabled(this, true);

        createProgressView();
        createWaveView();
    }

    private void createProgressView() {
        addView(mCircleView = new ProgressAnimationImageView(getContext()));
    }

    private void createWaveView() {
        mWaveView = new WaveView(getContext());
        addView(mWaveView, 0);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        try {
            ensureTarget();
            mTarget.measure(
                    makeMeasureSpecExactly(getMeasuredWidth() - (getPaddingLeft() + getPaddingRight())),
                    makeMeasureSpecExactly(getMeasuredHeight() - (getPaddingTop() + getPaddingBottom())));
            mWaveView.measure(widthMeasureSpec, heightMeasureSpec);

            mCircleView.measure();
        } catch (IllegalArgumentException e){

        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (getChildCount() == 0) {
            return;
        }

        ensureTarget();

        final int thisWidth = getMeasuredWidth();
        final int thisHeight = getMeasuredHeight();

        final int childRight = thisWidth - getPaddingRight();
        final int childBottom = thisHeight - getPaddingBottom();
        mTarget.layout(getPaddingLeft(), getPaddingTop(), childRight, childBottom);

        layoutWaveView();
    }

    private void layoutWaveView() {
        if (mWaveView == null) {
            return;
        }
        final int thisWidth = getMeasuredWidth();
        final int thisHeight = getMeasuredHeight();

        final int circleWidth = mCircleView.getMeasuredWidth();
        final int circleHeight = mCircleView.getMeasuredHeight();
        mCircleView.layout(
                (thisWidth - circleWidth) / 2,
                -circleHeight + mTopOffset,
                (thisWidth + circleWidth) / 2,
                mTopOffset);
        final int childRight = thisWidth - getPaddingRight();
        final int childBottom = thisHeight - getPaddingBottom();
        mWaveView.layout(getPaddingLeft(), mTopOffset + getPaddingTop(), childRight, childBottom);
    }

    public void setTopOffsetOfWave(int topOffset) {
        if (topOffset < 0) {
            return;
        }
        mTopOffset = topOffset;
        layoutWaveView();
    }

    @Override
    public boolean onPreDraw() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        mWaveView.bringToFront();
        mCircleView.bringToFront();
        if (mIsManualRefresh) {
            mIsManualRefresh = false;
            mWaveView.manualRefresh();
            reInitCircleView();
            mCircleView.setBackgroundColor(Color.TRANSPARENT);
            mCircleView.setTranslationY(
                    mWaveView.getCurrentCircleCenterY() + mCircleView.getHeight() / 2);
            animateOffsetToCorrectPosition();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull MotionEvent event) {
        ensureTarget();

        if (!isEnabled() || canChildScrollUp() || isRefreshing()) {
            return false;
        }

        final int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
                mFirstTouchDownPointY = getMotionEventY(event, mActivePointerId);
                break;

            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }

                final float currentY = getMotionEventY(event, mActivePointerId);

                if (currentY == -1) {
                    return false;
                }

                if (mFirstTouchDownPointY == -1) {
                    mFirstTouchDownPointY = currentY;
                }

                final float yDiff = currentY - mFirstTouchDownPointY;

                if (yDiff > ViewConfiguration.get(getContext()).getScaledTouchSlop() && !isRefreshing()) {
                    mCircleView.makeProgressTransparent();
                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mActivePointerId = INVALID_POINTER;
                break;
        }

        return false;
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean b) {
        //orz
    }

    private void reInitCircleView() {
        if (mCircleView.getVisibility() != View.VISIBLE) {
            mCircleView.setVisibility(View.VISIBLE);
        }

        mCircleView.scaleWithKeepingAspectRatio(1f);
        mCircleView.makeProgressTransparent();
    }

    private boolean onMoveTouchEvent(@NonNull MotionEvent event, int pointerIndex) {
        if (mIsBeingDropped) {
            return false;
        }

        final float y = MotionEventCompat.getY(event, pointerIndex);
        final float diffY = y - mFirstTouchDownPointY;
        final float overScrollTop = diffY * DRAGGING_WEIGHT;

        if (overScrollTop < 0) {
            mCircleView.showArrow(false);
            return false;
        }

        final DisplayMetrics metrics = getResources().getDisplayMetrics();

        float originalDragPercent = overScrollTop / (DEFAULT_CIRCLE_TARGET * metrics.density);
        float dragPercent = Math.min(1f, originalDragPercent);
        float adjustedPercent = (float) Math.max(dragPercent - .4, 0) * 5 / 3;

        // 0f...2f
        float tensionSlingshotPercent =
                (originalDragPercent > 3f) ? 2f : (originalDragPercent > 1f) ? originalDragPercent - 1f : 0;
        float tensionPercent = (4f - tensionSlingshotPercent) * tensionSlingshotPercent / 8f;

        mCircleView.showArrow(true);
        reInitCircleView();

        if (originalDragPercent < 1f) {
            float strokeStart = adjustedPercent * .8f;
            mCircleView.setProgressStartEndTrim(0f, Math.min(MAX_PROGRESS_ROTATION_RATE, strokeStart));
            mCircleView.setArrowScale(Math.min(1f, adjustedPercent));
        }

        float rotation = (-0.25f + .4f * adjustedPercent + tensionPercent * 2) * .5f;
        mCircleView.setProgressRotation(rotation);
        mCircleView.setTranslationY(mWaveView.getCurrentCircleCenterY());

        float seed = diffY / Math.min(getMeasuredWidth(), getMeasuredHeight());
        float firstBounds = seed * (5f - 2 * seed) / 3.5f;
        float secondBounds = firstBounds - VERTICAL_DRAG_THRESHOLD.FIRST.val;
        float finalBounds = (firstBounds - VERTICAL_DRAG_THRESHOLD.SECOND.val) / 5;

        if (firstBounds < VERTICAL_DRAG_THRESHOLD.FIRST.val) {
            onBeginPhase(firstBounds);
        } else if (firstBounds < VERTICAL_DRAG_THRESHOLD.SECOND.val) {
            onAppearPhase(firstBounds, secondBounds);
        } else if (firstBounds < VERTICAL_DRAG_THRESHOLD.THIRD.val) {
            onExpandPhase(firstBounds, secondBounds, finalBounds);
        } else {
            onDropPhase();
        }

        return !mIsBeingDropped;
    }

    private void onBeginPhase(float move1) {
        mWaveView.beginPhase(move1);

        setEventPhase(EVENT_PHASE.BEGINNING);
    }

    private void onAppearPhase(float move1, float move2) {
        mWaveView.appearPhase(move1, move2);

        setEventPhase(EVENT_PHASE.APPEARING);
    }

    private void onExpandPhase(float move1, float move2, float move3) {
        mWaveView.expandPhase(move1, move2, move3);

        setEventPhase(EVENT_PHASE.EXPANDING);
    }

    private void onDropPhase() {
        mWaveView.animationDropCircle();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 0);
        animator.setDuration(500);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCircleView.setTranslationY(
                        mWaveView.getCurrentCircleCenterY() + mCircleView.getHeight() / 2.f);
            }
        });
        animator.start();
        setRefreshing(true, true);
        mIsBeingDropped = true;
        setEventPhase(EVENT_PHASE.DROPPING);
        setEnabled(false);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {

        if (!isEnabled() || canChildScrollUp()) {
            return false;
        }
        mIsBeingDropped = mWaveView.isDisappearCircleAnimatorRunning();

        final int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                final int pointerIndex = MotionEventCompat.findPointerIndex(event, mActivePointerId);
                return pointerIndex >= 0 && onMoveTouchEvent(event, pointerIndex);

            case MotionEvent.ACTION_UP:
                if (mIsBeingDropped) {
                    mIsBeingDropped = false;
                    return false;
                }

                final float diffY = event.getY() - mFirstTouchDownPointY;
                final float waveHeightThreshold =
                        diffY * (5f - 2 * diffY / Math.min(getMeasuredWidth(), getMeasuredHeight())) / 1000f;
                mWaveView.startWaveAnimation(waveHeightThreshold);

            case MotionEvent.ACTION_CANCEL:
                if (mActivePointerId == INVALID_POINTER) {
                    return false;
                }

                if (!isRefreshing()) {
                    mCircleView.setProgressStartEndTrim(0f, 0f);
                    mCircleView.showArrow(false);
                    mCircleView.setVisibility(GONE);
                }
                mActivePointerId = INVALID_POINTER;
                return false;
        }
        return true;
    }

    private float getMotionEventY(@NonNull MotionEvent ev, int activePointerId) {
        final int index = MotionEventCompat.findPointerIndex(ev, activePointerId);
        if (index < 0) {
            return -1;
        }
        return MotionEventCompat.getY(ev, index);
    }

    private void animateOffsetToCorrectPosition() {
        mAnimateToCorrectPosition.reset();
        mAnimateToCorrectPosition.setDuration(ANIMATE_TO_TRIGGER_DURATION);
        mAnimateToCorrectPosition.setInterpolator(mDecelerateInterpolator);
        mCircleView.setAnimationListener(mRefreshListener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(mAnimateToCorrectPosition);
    }

    private final Animation mAnimateToCorrectPosition = new Animation() {
        @Override
        public void applyTransformation(float interpolatedTime, @NonNull Transformation t) {
        }
    };

    public void setMaxDropHeight(int dropHeight) {
        mWaveView.setMaxDropHeight(dropHeight);
    }

    private Animation.AnimationListener mRefreshListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isRefreshing()) {
                mCircleView.makeProgressTransparent();
                mCircleView.startProgress();
                if (mNotify) {
                    if (mListener != null) {
                        mListener.onRefresh();
                    }
                }
            } else {
                mCircleView.stopProgress();
                mCircleView.setVisibility(View.GONE);
                mCircleView.makeProgressTransparent();
                mWaveView.startDisappearCircleAnimation();
            }
        }
    };

    private void ensureTarget() {
        if (mTarget == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (!child.equals(mCircleView) && !child.equals(mWaveView)) {
                    mTarget = child;
                    break;
                }
            }
        }

        if (mTarget == null) {
            throw new IllegalStateException("This view must have at least one AbsListView");
        }
    }

    private void setRefreshing(boolean refreshing, final boolean notify) {
        if (isRefreshing() != refreshing) {
            mNotify = notify;
            ensureTarget();
            setState(refreshing);
            if (isRefreshing()) {
                animateOffsetToCorrectPosition();
            } else {
                startScaleDownAnimation(mRefreshListener);
            }
        }
    }

    private void setEventPhase(EVENT_PHASE eventPhase) {
        mEventPhase = eventPhase;
    }

    private void setState(STATE state) {
        mState = state;
        setEnabled(true);
        if (!isRefreshing()) {
            setEventPhase(EVENT_PHASE.WAITING);
        }
    }

    private void setState(boolean doRefresh) {
        setState((doRefresh) ? STATE.REFRESHING : STATE.PENDING);
    }

    private void startScaleDownAnimation(Animation.AnimationListener listener) {
        Animation scaleDownAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                mCircleView.scaleWithKeepingAspectRatio(1 - interpolatedTime);
            }
        };

        scaleDownAnimation.setDuration(SCALE_DOWN_DURATION);
        mCircleView.setAnimationListener(listener);
        mCircleView.clearAnimation();
        mCircleView.startAnimation(scaleDownAnimation);
    }

    public void setColorSchemeResources(@IdRes int... colorResIds) {
        mCircleView.setProgressColorSchemeColorsFromResource(colorResIds);
    }

    public void setColorSchemeColors(int... colors) {
        ensureTarget();
        mCircleView.setProgressColorSchemeColors(colors);
    }

    public boolean isRefreshing() {
        return mState == STATE.REFRESHING;
    }

    private boolean isBeginning() {
        return mEventPhase == EVENT_PHASE.BEGINNING;
    }

    private boolean isExpanding() {
        return mEventPhase == EVENT_PHASE.EXPANDING;
    }

    private boolean isDropping() {
        return mEventPhase == EVENT_PHASE.DROPPING;
    }

    private boolean isAppearing() {
        return mEventPhase == EVENT_PHASE.APPEARING;
    }

    private boolean isWaiting() {
        return mEventPhase == EVENT_PHASE.WAITING;
    }

    public void setRefreshing(boolean refreshing) {
        if (refreshing && !isRefreshing()) {
            // scale and show
            setState(true);
            mNotify = false;

            mIsManualRefresh = true;
            if (mWaveView.getCurrentCircleCenterY() == 0) {
                return;
            }
            mWaveView.manualRefresh();
            reInitCircleView();
            mCircleView.setTranslationY(
                    mWaveView.getCurrentCircleCenterY() + mCircleView.getHeight() / 2);
            animateOffsetToCorrectPosition();
        } else {
            setRefreshing(refreshing, false /* notify */);
        }
    }

    public boolean canChildScrollUp() {
        if (mTarget == null) {
            return false;
        }

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0
                        || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    public void setShadowRadius(int radius) {
        radius = Math.max(0, radius); // set zero if negative
        mWaveView.setShadowRadius(radius);
    }

    public void setWaveColor(int argbColor) {
        int alpha = 0xFF & (argbColor >> 24);
        int red = 0xFF & (argbColor >> 16);
        int blue = 0xFF & (argbColor >> 0);
        int green = 0xFF & (argbColor >> 8);
        setWaveARGBColor(alpha, red, green, blue);
    }

    public void setWaveRGBColor(int r, int g, int b) {
        mWaveView.setWaveColor(Color.argb(0xFF, r, g, b));
    }

    public void setWaveARGBColor(int a, int r, int g, int b) {
        setWaveRGBColor(r, g, b);
        if (a == 0xFF) {
            return;
        }
        mWaveView.setAlpha((float) a / 255f);
    }

    private static int makeMeasureSpecExactly(int length) {
        return MeasureSpec.makeMeasureSpec(length, MeasureSpec.EXACTLY);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    private class ProgressAnimationImageView extends AnimationImageView {
        private final MaterialProgressDrawable mProgress;

        public ProgressAnimationImageView(Context context) {
            super(context);
            mProgress = new MaterialProgressDrawable(context, WaveSwipeRefreshLayout.this);

            if (DisplayUtil.isOver600dp(getContext())) { // Make the progress be big
                mProgress.updateSizes(MaterialProgressDrawable.LARGE);
            }
            initialize();
        }

        private void initialize() {
            setImageDrawable(null);

            mProgress.setBackgroundColor(Color.TRANSPARENT);

            setImageDrawable(mProgress);
            setVisibility(View.GONE);
        }

        public void measure() {
            final int circleDiameter = mProgress.getIntrinsicWidth();

            measure(makeMeasureSpecExactly(circleDiameter), makeMeasureSpecExactly(circleDiameter));
        }

        public void makeProgressTransparent() {
            mProgress.setAlpha(0xff);
        }

        public void showArrow(boolean show) {
            mProgress.showArrow(show);
        }

        public void setArrowScale(float scale) {
            mProgress.setArrowScale(scale);
        }

        public void setProgressAlpha(int alpha) {
            mProgress.setAlpha(alpha);
        }

        public void setProgressStartEndTrim(float startAngle, float endAngle) {
            mProgress.setStartEndTrim(startAngle, endAngle);
        }

        public void setProgressRotation(float rotation) {
            mProgress.setProgressRotation(rotation);
        }

        public void startProgress() {
            mProgress.start();
        }

        public void stopProgress() {
            mProgress.stop();
        }

        public void setProgressColorSchemeColors(@NonNull int... colors) {
            mProgress.setColorSchemeColors(colors);
        }

        public void setProgressColorSchemeColorsFromResource(@IdRes int... resources) {
            final Resources res = getResources();
            final int[] colorRes = new int[resources.length];

            for (int i = 0; i < resources.length; i++) {
                colorRes[i] = res.getColor(resources[i]);
            }

            setColorSchemeColors(colorRes);
        }

        public void scaleWithKeepingAspectRatio(float scale) {
            ViewCompat.setScaleX(this, scale);
            ViewCompat.setScaleY(this, scale);
        }
    }
}
