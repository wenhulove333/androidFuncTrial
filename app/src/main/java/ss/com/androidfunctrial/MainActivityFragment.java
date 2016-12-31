package ss.com.androidfunctrial;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    private final String TAG = "main";
    private Button dropdown;
    private Button measureView;
    private LinearLayout dynamicLayout;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        initViews();
    }

    /**
     * init views
     * @author zhangwenhu
     * @time 16/12/31 下午2:15
     */
    private void initViews() {
        dropdown = (Button) getActivity().findViewById(R.id.dropDownID);
        measureView = (Button) getActivity().findViewById(R.id.measureViewID);
        dynamicLayout = (LinearLayout) getActivity().findViewById(R.id.dynamicLayoutID);
        dropdown.setOnClickListener(this);
        measureView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dropDownID: {
                View popupView = getActivity().getLayoutInflater().inflate(R.layout.content_demo_popup, null);

                measureView(popupView);

                //创建PopupWindow对象，指定宽度和高度
                PopupWindow window = new PopupWindow(popupView, popupView.getMeasuredWidth(), popupView.getMeasuredHeight());
                //设置动画
                window.setAnimationStyle(R.style.popup_window_anim);
                //设置背景颜色
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                //设置可以获取焦点
                window.setFocusable(true);
                //设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);
                //更新popupwindow的状态
                window.update();
                //以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(dropdown, 0, 0);
                break;
            }
            case R.id.measureViewID: {
                View view = getActivity().getLayoutInflater().inflate(R.layout.view_layout, null);
                View viewGroup = getActivity().getLayoutInflater().inflate(R.layout.viewgroup_layout, null);
                View textView = getActivity().getLayoutInflater().inflate(R.layout.textview_layout, null);

                //measureView(view);
                //measureView(viewGroup);
                //measureView(textView);

                //dynamicLayout.addView(view);
                dynamicLayout.addView(viewGroup);
                dynamicLayout.addView(textView);

                Log.i(TAG, "view's width and height is " + view.getMeasuredWidth() + ", " + view.getMeasuredHeight() + ".");
                Log.i(TAG, "view group's width and height is " + viewGroup.getMeasuredWidth() + ", " + viewGroup.getMeasuredHeight() + ".");
                Log.i(TAG, "text view's width and height is " + textView.getMeasuredWidth() + ", " + textView.getMeasuredHeight() + ".");
                break;
            }
            default:{

            }
        }
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();
        if(lp == null){
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        //headerView的宽度信息
        int childMeasureWidth = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int childMeasureHeight;
        if(lp.height > 0){
            childMeasureHeight = MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.EXACTLY);
            //最后一个参数表示：适合、匹配
        } else {
            childMeasureHeight = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);//未指定
        }

        //将宽和高设置给child
        child.measure(childMeasureWidth, childMeasureHeight);
    }
}
