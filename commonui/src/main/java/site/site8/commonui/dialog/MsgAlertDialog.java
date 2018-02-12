package site.site8.commonui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Ledev2 on 2017-12-19.
 */

public class MsgAlertDialog  {


    private final AlertDialog mDialog;
    private final Window mDialogWindow;

    private MsgAlertDialog(Context context) {
        mDialog = new AlertDialog.Builder(context).create();
        mDialogWindow = mDialog.getWindow();
    }

    public static MsgAlertDialog builder(Context context){
        return new MsgAlertDialog(context);
    }
    public MsgAlertDialog show(){
        mDialog.show();
        return this;
    }
    public MsgAlertDialog dismiss(){
        mDialog.dismiss();
        return this;
    }

    /**
     * 设置点击外部是否dismiss
     * @param cancel
     * @return
     */
    public MsgAlertDialog setCanceledOnTouchOutside(boolean cancel){
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 设置透明度
     * @param alpha
     * @return
     */
    public MsgAlertDialog setAlpha(float alpha){
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.alpha = alpha;
        mDialogWindow.setAttributes(lp);
        return this;
    }




}
