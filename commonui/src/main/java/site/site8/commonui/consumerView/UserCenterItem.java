package site.site8.commonui.consumerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import site.site8.commonui.R;

/**
 * TODO: document your custom view class.
 */
public class UserCenterItem extends LinearLayout {

    private ImageView leftIv;
    private ImageView rightIv;
    private TextView leftTv;
    private TextView rightTv;
    private String leftString;
    private String rightString;
    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private float leftTextSize;
    private float rightTextSize;
    private int leftTextColor = Color.parseColor("#666666");
    private int rightTextColor = Color.parseColor("#666666");

    public UserCenterItem(Context context) {
        super(context);
        init(null, 0, context);
    }

    public UserCenterItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, context);
    }

    public UserCenterItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle, context);
    }

    private void init(AttributeSet attrs, int defStyle, Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_item_user_center, this, true);
        leftIv = rootView.findViewById(R.id.left_iv);
        rightIv = rootView.findViewById(R.id.right_iv);
        leftTv = rootView.findViewById(R.id.left_tv);
        rightTv = rootView.findViewById(R.id.right_tv);
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.UserCenterItem, defStyle, 0);

        leftString = a.getString(
                R.styleable.UserCenterItem_leftString);
        rightString = a.getString(
                R.styleable.UserCenterItem_rightString);

        leftTextColor = a.getColor(
                R.styleable.UserCenterItem_leftTextColor,
                leftTextColor);
        rightTextColor = a.getColor(
                R.styleable.UserCenterItem_rightTextColor,
                rightTextColor);


        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        leftTextSize = a.getDimension(
                R.styleable.UserCenterItem_leftTextSize,
                sp2px(14,context));
        rightTextSize = a.getDimension(
                R.styleable.UserCenterItem_leftTextSize,
                sp2px(14,context));




        if (a.hasValue(R.styleable.UserCenterItem_rightImage)) {
            rightDrawable = a.getDrawable(
                    R.styleable.UserCenterItem_rightImage);
            rightDrawable.setCallback(this);
        }
        if (a.hasValue(R.styleable.UserCenterItem_leftImage)) {
            leftDrawable = a.getDrawable(
                    R.styleable.UserCenterItem_leftImage);
            leftDrawable.setCallback(this);
        }


        leftTv.setText(leftString);
        rightTv.setText(rightString);
        leftTv.setTextColor(leftTextColor);
        rightTv.setTextColor(rightTextColor);
        leftIv.setImageDrawable(leftDrawable);
        rightIv.setImageDrawable(rightDrawable);


        a.recycle();

    }

    public  int sp2px(int sp,Context context) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,context.getResources().getDisplayMetrics()) + 0.5f);
    }
}
