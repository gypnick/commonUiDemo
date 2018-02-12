package site.site8.commonui.loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import site.site8.commonui.R;


public class LoadingBuilder {
    //OutsideTouchable
    private Context context;
    private BackgroundLinearLayout backgroundLayout;
    private LoadingView loadingView;
    //属性 ff000000
    private int outsideBackgroundColor = 0x000033;//空白区域背景色
    private boolean outsideTouchable = true;//点击空白区域是否关闭
    private boolean backTouchable = true;//点击返回键是否关闭

    private LoadingBuilder(Context context) {
        this.context = context;
        loadingView = new LoadingView(context);
        this.setSpacing(30);
        this.setPadding(50,30,50,30);

        this.setCornerRadius(8);
        this.setIcon(R.drawable.loading);
        this.setOutsideTouchable(false);
        this.setBackTouchable(true);
        this.setText("loading...");
    }

    public  static  LoadingBuilder builder(Context context){
      return   new LoadingBuilder(context);
    }



    /**
     * 显示dialog
     */
    public LoadingBuilder show() {
        //创建背景布局
        backgroundLayout = new BackgroundLinearLayout(context);
        backgroundLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        backgroundLayout.setGravity(Gravity.CENTER);
        backgroundLayout.setBackgroundColor(outsideBackgroundColor);
        //backgroundLayout.setBackgroundDrawable(new BitmapDrawable());
        backgroundLayout.addView(loadingView);

        //创建WindowManager
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.format = PixelFormat.RGBA_8888;


        if (!this.backTouchable)
            wlp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//不获得焦点
        //set translucent status after API 19
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            wlp.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        wm.addView(backgroundLayout, wlp);
        loadingView.startAnimation();
        return this;
    }

    /**
     * 关闭
     */
    public void dismiss() {
        try {
            if (backgroundLayout == null)
                return;
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            backgroundLayout.removeView(loadingView);
            wm.removeView(backgroundLayout);
            backgroundLayout = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示背景的容器
     *
     * @author pc     *
     */
    private class BackgroundLinearLayout extends LinearLayout {
        public BackgroundLinearLayout(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                dismiss();
            return super.dispatchKeyEvent(event);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (outsideTouchable && event.getAction() == MotionEvent.ACTION_UP) {
                dismiss();
            }
            return true;
        }
    }


    public LoadingBuilder setText(String text) {
        this.loadingView.setText(text);
        return this;
    }

    public LoadingBuilder setIcon(int icon) {
        this.loadingView.setIcon(icon);
        return this;
    }

    public int getOutsideBackgroundColor() {
        return outsideBackgroundColor;
    }

    public LoadingBuilder setOutsideBackgroundColor(int outsideBackgroundColor) {
        this.outsideBackgroundColor = outsideBackgroundColor;
        return this;
    }

    public boolean isOutsideTouchable() {
        return outsideTouchable;
    }

    public LoadingBuilder setOutsideTouchable(boolean outsideTouchable) {
        this.outsideTouchable = outsideTouchable;
        return  this;
    }

    public boolean isBackTouchable() {
        return backTouchable;
    }

    public LoadingBuilder setBackTouchable(boolean backTouchable) {
        this.backTouchable = backTouchable;
        return this;
    }

    public int getTextSize() {
        return this.loadingView.getTextSize();
    }

    public LoadingBuilder setTextSize(int textSize) {
        this.loadingView.setTextSize(textSize);
        return this;
    }

    public int getTextColor() {
        return this.loadingView.getTextColor();
    }

    public LoadingBuilder setTextColor(int textColor) {
        this.loadingView.setTextColor(textColor);
        return this;
    }

    public int getIconWidth() {
        return this.loadingView.getIconWidth();
    }

    public LoadingBuilder setIconWidth(int iconWidth) {
        this.loadingView.setIconWidth(iconWidth);
        return this;
    }

    public int getIconHeight() {
        return this.loadingView.getIconHeight();
    }

    public LoadingBuilder setIconHeight(int iconHeight) {
        this.loadingView.setIconHeight(iconHeight);
        return this;
    }

    public int getCornerRadius() {
        return this.loadingView.getCornerRadius();
    }

    public LoadingBuilder setCornerRadius(int cornerRadius) {
        this.loadingView.setCornerRadius(cornerRadius);
        return this;
    }

    public int getBackgroundColor() {
        return this.loadingView.getBackgroundColor();
    }

    public LoadingBuilder setBackgroundColor(int backgroundColor) {
        this.loadingView.setBackgroundColor(backgroundColor);
        return this;
    }

    public int getSpacing() {
        return this.loadingView.getSpacing();
    }

    public LoadingBuilder setSpacing(int spacing) {
        this.loadingView.setSpacing(spacing);
        return this;
    }

    public int getPaddingLeft() {
        return this.loadingView.getPaddingLeft();
    }

    public LoadingBuilder setPaddingLeft(int paddingLeft) {
        this.loadingView.setPaddingLeft(paddingLeft);
        return this;
    }

    public int getPaddingTop() {
        return this.loadingView.getPaddingTop();
    }

    public LoadingBuilder setPaddingTop(int paddingTop) {
        this.loadingView.setPaddingTop(paddingTop);
        return this;
    }

    public int getPaddingRight() {
        return this.loadingView.getPaddingRight();
    }

    public LoadingBuilder setPaddingRight(int paddingRight) {
        this.loadingView.setPaddingRight(paddingRight);
        return this;
    }

    public int getPaddingBottom() {
        return this.loadingView.getPaddingBottom();
    }

    public LoadingBuilder setPaddingBottom(int paddingBottom) {
        this.loadingView.setPaddingBottom(paddingBottom);
        return this;
    }

    public int getCycle() {
        return this.loadingView.getCycle();
    }

    public LoadingBuilder setCycle(int cycle) {
        this.loadingView.setCycle(cycle);
        return this;
    }

    public LoadingBuilder setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        this.loadingView.setPaddingLeft(paddingLeft);
        this.loadingView.setPaddingTop(paddingTop);
        this.loadingView.setPaddingRight(paddingRight);
        this.loadingView.setPaddingBottom(paddingBottom);
        return this;
    }
}
