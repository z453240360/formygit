package com.example.administrator.teser;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.administrator.teser.util.FileUtil;

import java.io.File;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private int MEDIA_IMAGECAUPTURE = 1;
    private Button mBtn_imagevIEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getBaidiToken();

        mBtn_imagevIEW = (Button) findViewById(R.id.mBtn_imagevIEW);

    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.mBtn_imagevIEW:
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra("outputFilePath",
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent,1);
                break;

            case R.id.mBtn_da:

                Animator m= ObjectAnimator.ofFloat(mBtn_imagevIEW,"scaleX",1,2);
                    m.setDuration(0);

                m.start();
                break;

            case R.id.mBtn_xiao:
                Animator m2= ObjectAnimator.ofFloat(mBtn_imagevIEW,"scaleX",2,1);
                m2.setDuration(3000);

                m2.start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 获取调用参数

        if (resultCode == Activity.RESULT_OK) {

            String contentType = intent.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
            String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();

//            if (CameraActivity.CONTENT_TYPE_GENERAL.equals(contentType)) {
                GeneralParams param = new GeneralParams();
                param.setDetectDirection(true);
                param.setImageFile(new File(filePath));

                // 调用通用文字识别服务
                OCR.getInstance().recognizeGeneral(param, new OnResultListener<GeneralResult>() {
                    @Override
                    public void onResult(GeneralResult result) {
                        // 调用成功，返回GeneralResult对象

                        List<? extends WordSimple> wordList = result.getWordList();

                        StringBuffer sb = new StringBuffer();

                        for (int i = 0; i < wordList.size(); i++) {
                            WordSimple wordSimple = wordList.get(i);
                            sb.append(wordSimple);
                        }
                        Log.i("dd", "onResult: "+sb.toString());
                    }

                    @Override
                    public void onError(OCRError error) {
                        // 调用失败，返回OCRError对象
                        Log.i("dd", "onError: "+error.toString());
                    }
                });


            }
//        }


    }

    private void getBaidiToken() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
                Log.i("dd", "onResult: "+token);
            }

            @Override
            public void onError(final OCRError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Main2Activity.this, "获取Token失败" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }, getApplicationContext(), "7OPlGiEtdukdo8KUB9rL9A0x", "WI55W4fUHOfLIWhmDxTgGM3rzFeqFZfo");
    }
}
