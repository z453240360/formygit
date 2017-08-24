package com.example.dongdong.dongdong;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dongdong.dongdong.SingGetInstance.MyIntence;
import com.example.dongdong.dongdong.builder.ManBuilder;
import com.example.dongdong.dongdong.builder.MyBuilder;
import com.example.dongdong.dongdong.recylerview.LikeListActivity;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private TextView mTxt;
    private AlertDialog.Builder builder;
    final String[] strings = {"1", "2", "3", "4", "5", "6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxt = (TextView) findViewById(R.id.mTxt);
        String myInstanceMethed = MyIntence.getInstance().getMyInstanceMethed();
        builder = new AlertDialog.Builder(this);
        MyBuilder builder = new MyBuilder();
        ManBuilder man = new ManBuilder();
        man.builderBody();
        man.builderHead();
        man.buildPerson();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                AlertDialog dialog = new AlertDialog.Builder(this).setTitle("title")
                        .setMessage("message").create();
                Window window = dialog.getWindow();
                window.setWindowAnimations(R.style.AppTheme);
                window.setGravity(Gravity.BOTTOM); //window.setGravity(Gravity.BOTTOM); 
                dialog.show();


//                builder.setTitle("设置的标题")
//                        .setMessage("消息主题")
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setNegativeButton("取消", null)
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this, "你点我干嘛", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setCancelable(false);
//
//                Window window = builder.create().getWindow();
//                window.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
//
//                builder .show();



                break;
            //列表对话框
            case R.id.button2:


                builder.setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "点击：" + strings[i], Toast.LENGTH_SHORT).show();
                    }
                })
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();


                break;
            case R.id.button3:
                builder.setTitle("单选列表")
                        .setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, ""+ strings[i], Toast.LENGTH_SHORT).show();
//                                dialogInterface.cancel();
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button4:

                builder.setTitle("多选列表")
                        .setMultiChoiceItems(strings, new boolean[]{false,false,false,false,true}, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {



                            }
                        })

                        .show();


                break;
            case R.id.button5:
                View inflate = LayoutInflater.from(this).inflate(R.layout.items, null);
                final TextView textView=(TextView) inflate.findViewById(R.id.txt_dialog);


                builder.setTitle("自定义显示内容")
                        .setView(inflate)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                textView.setText("你在跳什么");
                            }
                        })
                        .show();




                break;

            case R.id.button6:

                View inflate1 = LayoutInflater.from(this).inflate(R.layout.items, null);

                PopupWindow pop = new PopupWindow(inflate1,200,200);

                pop.setFocusable(true);
                pop.showAtLocation(mTxt,Gravity.TOP,0,0);


                break;

            case R.id.button7:

                startActivity(new Intent(this, LikeListActivity.class));

                break;

        }
    }
}
