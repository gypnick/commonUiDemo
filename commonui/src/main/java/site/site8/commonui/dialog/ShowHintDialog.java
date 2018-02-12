package site.site8.commonui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import site.site8.commonui.R;


/**
 * 提示框
 * Created by Ledev2 on 2018-01-29.
 */

public class ShowHintDialog extends DialogFragment {
    private ShowHintDialogCallBack showHintDialogCallBack;
    private Dialog dialog;

    public static ShowHintDialog newInstance(String title, String info) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("info", info);
        ShowHintDialog fragment = new ShowHintDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.ShowPushTypeDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hint);
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
        String info = bundle.getString("info", "");
        TextView titleTv = dialog.findViewById(R.id.title_tv);
        TextView infoTv = dialog.findViewById(R.id.dialog_info);
        titleTv.setText(title.isEmpty()?"提示":title);
        infoTv.setText(info);
        dialog.findViewById(R.id.dialog_confirm_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showHintDialogCallBack != null) showHintDialogCallBack.confirmCallBack();
                dismiss();
            }
        });
        dialog.findViewById(R.id.dialog_confirm_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showHintDialogCallBack != null) showHintDialogCallBack.cancelCallBack();
                dismiss();
            }
        });
    }

    public  ShowHintDialog setShowHintDialogCallBack(ShowHintDialogCallBack showHintDialogCallBack) {
        this.showHintDialogCallBack = showHintDialogCallBack;
        return this;
    }

    public interface ShowHintDialogCallBack {
        void confirmCallBack();

        void cancelCallBack();
    }

}
