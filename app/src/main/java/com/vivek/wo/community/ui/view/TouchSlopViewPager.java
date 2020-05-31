package com.vivek.wo.community.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class TouchSlopViewPager extends ViewPager {
    private static final String TAG = "TouchSlopViewPager";
    private int mTouchSlop;
    private boolean mTouchSlopTriggered = false;

    private OnTouchSlopListener mOnTouchSlopListener;

    /**
     * Position of the last motion event.
     */
    private int mLastMotionY;

    /**
     * ID of the active pointer. This is used to retain consistency during
     * drags/flings if multiple pointers are used.
     */
    private int mActivePointerId = INVALID_POINTER;

    /**
     * Sentinel value for no current active pointer.
     * Used by {@link #mActivePointerId}.
     */
    private static final int INVALID_POINTER = -1;

    public interface OnTouchSlopListener {
        public void onTouchSlop();
    }

    public TouchSlopViewPager(@NonNull Context context) {
        super(context);
        initTouchSlop();
    }

    public TouchSlopViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTouchSlop();
    }

    private void initTouchSlop() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    public void setOnTouchSlopListener(OnTouchSlopListener listener) {
        mOnTouchSlopListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                /*
                 * Remember location of down touch.
                 * ACTION_DOWN always refers to pointer index 0.
                 */
                mLastMotionY = (int) ev.getY();
                mActivePointerId = ev.getPointerId(0);

                mTouchSlopTriggered = false;
                break;
            case MotionEvent.ACTION_MOVE:
                /*
                 * Locally do absolute value. mLastMotionY is set to the y value
                 * of the down event.
                 */
                final int activePointerId = mActivePointerId;
                if (activePointerId == INVALID_POINTER) {
                    // If we don't have a valid id, the touch down wasn't on content.
                    break;
                }

                final int pointerIndex = ev.findPointerIndex(activePointerId);
                if (pointerIndex == -1) {
                    Log.e(TAG, "Invalid pointerId=" + activePointerId
                            + " in dispatchTouchEvent");
                    break;
                }

                final int y = (int) ev.getY(pointerIndex);
                final int yDiff = mLastMotionY - y;
                if (!mTouchSlopTriggered && yDiff > mTouchSlop) {
                    Log.w(TAG, "DispatchTouchEvent TouchSlop:" + yDiff);
                    mLastMotionY = y;
                    mTouchSlopTriggered = true;
                    if (mOnTouchSlopListener != null) {
                        mOnTouchSlopListener.onTouchSlop();
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN: {
                final int index = ev.getActionIndex();
                mLastMotionY = (int) ev.getY(index);
                mActivePointerId = ev.getPointerId(index);
                break;
            }
            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(ev);
                mLastMotionY = (int) ev.getY(ev.findPointerIndex(mActivePointerId));
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = ev.getActionIndex();
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose a new
            // active pointer and adjust accordingly.
            // TODO: Make this decision more intelligent.
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mLastMotionY = (int) ev.getY(newPointerIndex);
            mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }
}
