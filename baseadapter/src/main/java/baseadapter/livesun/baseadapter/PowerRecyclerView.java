package baseadapter.livesun.baseadapter;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 类描述：
 * 创建人：livesun
 * 创建时间：2017/9/14
 * 修改人：
 * 修改时间：
 * github：https://github.com/livesun
 */

public class PowerRecyclerView extends FrameLayout {

    private ViewDragHelper mDragHelper;
    private View mRefshView;
    private View mReclcerView;
    private int mRefshViewHeight;
    private boolean mMenuIsOpen;
    public PowerRecyclerView(Context context) {
        this(context,null);
    }

    public PowerRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PowerRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
    }

    ViewDragHelper.Callback mDragHelperCallback=new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child==mReclcerView;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            if(top<=0) {
                top = 0;
            }
            Log.d("AAA","top ="+(int)(top*0.3)+" dy= "+dy);

            return (int)(top*0.9);
        }

        //抬起手指的回掉
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //弹性滑动
            if(mReclcerView.getTop()<=mRefshViewHeight/2){
                //关闭
                mDragHelper.settleCapturedViewAt(0,0);
                mMenuIsOpen=false;
            }else{
                //打开
                mDragHelper.settleCapturedViewAt(0,mRefshViewHeight);
                mMenuIsOpen=true;
            }
            invalidate();
        }

    };

    /**
     * 响应滚动
     */
    @Override
    public void computeScroll() {
        if(mDragHelper.continueSettling(true)){
            invalidate();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRefshView = getChildAt(0);
        mReclcerView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRefshViewHeight = mRefshView.getMeasuredHeight();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
