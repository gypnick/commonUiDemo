package site.site8.commonui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import site.site8.commonui.R;


/**
 * 输入框
 * Created by Ledev2 on 2018-01-29.
 */

public class ShowInputDialog extends DialogFragment {
    private ShowInputDialogCallBack showInputDialogCallBack;
    private Dialog dialog;
    private EditText editText;

    public static ShowInputDialog newInstance(String title, String hintText) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("hintText", hintText);
        ShowInputDialog fragment = new ShowInputDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.ShowPushTypeDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_input);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消


        initView(dialog);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lp.gravity = Gravity.CENTER;
        lp.alpha = 0.9f;
        window.setAttributes(lp);
        return dialog;
    }


    private void initView(Dialog dialog) {
        Bundle bundle = getArguments();
        String title = bundle.getString("title", "提示");
        String hintText = bundle.getString("hintText", "");
        TextView titleTv = dialog.findViewById(R.id.title_tv);
        editText = dialog.findViewById(R.id.dialog_ed);
        titleTv.setText(title.isEmpty() ? "提示" : title);
        editText.setHint(hintText);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.findViewById(R.id.dialog_confirm_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = editText.getText().toString().trim();
                if (trim.isEmpty()) {
                    editText.setError("输入内容不能为空！");
                    return;
                }
                if (showInputDialogCallBack != null) {
                    showInputDialogCallBack.confirmCallBack(trim);

                    dismiss();
                }
            }
        });
        dialog.findViewById(R.id.dialog_confirm_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showInputDialogCallBack != null) showInputDialogCallBack.cancelCallBack();
                dismiss();
            }
        });
    }

    public ShowInputDialog setInputDialogCallBack(ShowInputDialogCallBack showInputDialogCallBack) {
        this.showInputDialogCallBack = showInputDialogCallBack;
        return this;
    }

    public interface ShowInputDialogCallBack {
        void confirmCallBack(String content);

        void cancelCallBack();
    }


}
