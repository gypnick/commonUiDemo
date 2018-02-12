package site.site8.commonuidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import site.site8.commonui.dialog.ShowHintDialog;
import site.site8.commonui.dialog.ShowInputDialog;
import site.site8.commonui.loading.LoadingBuilder;

public class MainActivity extends AppCompatActivity {

    private LoadingBuilder loadingBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loading(View view) {
        loadingBuilder = LoadingBuilder.builder(this)
//              .setText("加载中")  设置加载文字
                .setOutsideTouchable(true)
                .show();
    }

    public void stopLoading(View view) {
        loadingBuilder.dismiss();
    }

    public void hintDialog(View view) {
        ShowHintDialog.newInstance("", "是否要退出")
                .setShowHintDialogCallBack(new ShowHintDialog.ShowHintDialogCallBack() {
                    @Override
                    public void confirmCallBack() {


                    }

                    @Override
                    public void cancelCallBack() {

                    }
                })
                .show(getFragmentManager(), "");

    }

    public void inputDialog(View view) {

        ShowInputDialog.newInstance("", "").setInputDialogCallBack(new ShowInputDialog.ShowInputDialogCallBack() {
            @Override
            public void confirmCallBack(String content) {
                Log.e("inputDialog", content);
            }

            @Override
            public void cancelCallBack() {
                Log.e("inputDialog", "取消");
            }
        }).show(getFragmentManager(), "");
    }
}
