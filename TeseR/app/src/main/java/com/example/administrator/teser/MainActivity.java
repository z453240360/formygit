package com.example.administrator.teser;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.GeneralParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.baidu.ocr.sdk.model.WordSimple;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.example.administrator.teser.bean.ZiDianBean;
import com.example.administrator.teser.fragment.MyFragment;
import com.example.administrator.teser.model.ModelFromIntent;
import com.example.administrator.teser.util.ColorState;
import com.example.administrator.teser.util.FileUtil;
import com.example.administrator.teser.util.GsonResult;
import com.example.administrator.teser.util.ResultJson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Manifest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.R.attr.data;
import static android.R.attr.visible;

public class MainActivity extends AppCompatActivity {

    private List<String> mDatas = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private FrameLayout layout;
    private LikeListAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    private Retrofit retrofit;
    private Bundle bundle;
    private File file = null;
    private ImageView mImg1, mImg2;
    private RelativeLayout myrl;
    private int up = 1;
    private int down = 1;
    private int pos = 1;
    private TextView mTxt1, mTxt2, mTxt3, mTxt4;

    private ImageView mBtn_second, img1, img2;
    private SpeechSynthesizer ss;
    private HashMap<String, Integer> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ColorState.setWindowStatusBarColor(this, Color.BLACK);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=59658cec");
        myrl = (RelativeLayout) findViewById(R.id.myrl);
        initView();
        ss = SpeechSynthesizer.createSynthesizer(this, null);

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        Toast.makeText(this, ""+widthPixels+heightPixels, Toast.LENGTH_SHORT).show();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.general_button:

                mDatas.clear();
                mAdapter.notifyDataSetChanged();
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_GENERAL);
                startActivityForResult(intent, 105);

                break;

            case R.id.mBtn_2:
                mRecyclerView.setVisibility(View.VISIBLE);
                myrl.setVisibility(View.INVISIBLE);

                break;
            case R.id.mBtn_txtshow:

                String s = mTxt1.getText().toString();
                textToVoice(s);

                break;

            case R.id.img4://上一页

                pos--;
                if (pos > 0 && pos < mDatas.size()) {
                    String s3 = mDatas.get(pos);
                    initData(s3);

                }

                break;

            case R.id.img2://下一页

                pos++;
                if (pos > 0 && pos < mDatas.size()) {
                    String s3 = mDatas.get(pos);
                    initData(s3);
                }

                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // 获取调用参数

        if (resultCode == Activity.RESULT_OK) {

            String contentType = intent.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
            String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
            mImg1.setVisibility(View.INVISIBLE);
            if (CameraActivity.CONTENT_TYPE_GENERAL.equals(contentType)) {
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

                        for (int i = 0; i < sb.length(); i++) {
                            char c = sb.charAt(i);

                            if (isGB2312(String.valueOf(c))) {
                                mDatas.add(String.valueOf(c));
                            }
                        }

                        Log.i("dd", "onResult: " + mDatas.toString());
                        gridLayoutManager = new GridLayoutManager(MainActivity.this, 3, GridLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(gridLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                        mRecyclerView.setVisibility(View.VISIBLE);
                        mAdapter.setOnItemClickListener(new LikeListAdapter.OnItemClickListener() {

                            @Override
                            public void onItemClick(int pos1, View view) {
                                String s = mDatas.get(pos1);
                                pos = pos1;
                                initData(s);
                                myrl.setVisibility(View.VISIBLE);
//                                img1.setVisibility(View.GONE);
                            }
                        });
                    }

                    @Override
                    public void onError(OCRError error) {
                        // 调用失败，返回OCRError对象
                    }
                });


            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    //判断是否为汉字
    public static Boolean isGB2312(String str) {
        for (int i = 0; i < str.length(); i++) {
            String bb = str.substring(i, i + 1);
            // 生成一个Pattern,同时编译一个正则表达式,其中的u4E00("一"的unicode编码)-\u9FA5("龥"的unicode编码)
            boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc == false) {
                return cc;
            }
        }
        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OCR.getInstance().release();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg1 = (ImageView) findViewById(R.id.img1);

        file = new File(getFilesDir(), "pic.jpg");
        getBaidiToken();
        mAdapter = new LikeListAdapter(this, mDatas);

        mTxt1 = (TextView) findViewById(R.id.mTxt_1);
        mTxt2 = (TextView) findViewById(R.id.mTxt_2);
        mTxt3 = (TextView) findViewById(R.id.mTxt_3);
        mTxt4 = (TextView) findViewById(R.id.mTxt_4);

        mTxt2.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTxt4.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    //请求获取字典词条信息
    private void initData(String text) {

        if (!isGB2312(text)) {
            Toast.makeText(this, "只能查询汉字", Toast.LENGTH_SHORT).show();
            return;
        }
        bundle = new Bundle();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .build();
        ModelFromIntent modelFromIntent = retrofit.create(ModelFromIntent.class);
        Call<ResponseBody> call = modelFromIntent.getZidian(text, Constant.APPKEY);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String s = response.body().string().toString();

                    String zi = GsonResult.getZi(s);
                    String pinYin = GsonResult.getPinYin(s);
                    String xiangJie = GsonResult.getXiangJie(s);
                    StringBuffer b = new StringBuffer();
                    String[] split = pinYin.split(",");
                    String s78=null;

                    for (int i = 0; i < split.length; i++) {

                        String s1 = split[i].toString();

                        s78 = b.append(s1+"\n").toString();
                    }

                    Log.i("dd", "onResponse: "+s78);

                    mTxt1.setText(zi);
                    mTxt2.setText(b.toString());
                    mTxt4.setText(xiangJie);
                    myrl.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    textToVoice(zi);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBaidiToken() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
            }

            @Override
            public void onError(final OCRError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "获取Token失败" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }, getApplicationContext(), "7OPlGiEtdukdo8KUB9rL9A0x", "WI55W4fUHOfLIWhmDxTgGM3rzFeqFZfo");
    }

    //语音合成
    private void textToVoice(String text) {
        ss.setParameter(SpeechConstant.SPEED, "25");
        ss.setParameter(SpeechConstant.VOLUME, "100");
        ss.setParameter(SpeechConstant.VOICE_NAME, "nannan");
        ss.startSpeaking(text, new SynthesizerListener() {
            @Override
            public void onSpeakBegin() {
            }

            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onCompleted(SpeechError speechError) {

            }

            @Override
            public void onEvent(int i, int i1, int i2, Bundle bundle) {

            }
        });
    }


}
