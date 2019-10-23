package com.ktw.app3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar pBar;
    TextView tv01;
    boolean flag = false;

    ProgressHandler progresshandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar = (ProgressBar)findViewById(R.id.pgBar);
        tv01 = (TextView)findViewById(R.id.txt01);

        progresshandler = new ProgressHandler();

    }

    public void onStart(){
        super.onStart();

        pBar.setProgress(0);
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i = 0; i<50 && flag; i++) {
                        Thread.sleep(1000);
                        Message msg = progresshandler.obtainMessage();
                        progresshandler.sendMessage(msg);
                    }
                }catch (Exception e) {}
            }
        });

        flag = true;
        subThread.start();
    }

    public void onStop(){
        super.onStop();

        flag = false;
    }

    //내장클래스
    public class ProgressHandler extends Handler {
        public void handleMessage(Message msg){
            pBar.incrementProgressBy(2);

            if(pBar.getProgress() == pBar.getMax()){
                tv01.setText("작업완료");
            }else {
                tv01.setText("작업중 ..."+pBar.getProgress()+"%");
            }
        }
    }
}
