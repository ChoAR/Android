package com.ktw.app4;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tv01;
    ProgressRunnable progressRunnable;
    Handler handler;
    boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.pgBar);
        tv01 = (TextView)findViewById(R.id.txt01);

        handler = new Handler();
        progressRunnable = new ProgressRunnable();


    }

    public void onStart(){
        super.onStart();

        progressBar.setProgress(0);
        Thread subThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0; i<50&&flag; i++){
                        Thread.sleep(1000);

                        // 핸들러에 post 메소드에 Runnable 객체를 전달
                       handler.post(progressRunnable);
                    }
                } catch (Exception e) {}
            }
        });

        flag = true;
        subThread.start();

    }

    public void onStop(){
        super.onStop();

    }

    public class ProgressRunnable implements Runnable{
        public void run(){
            progressBar.incrementProgressBy(2);

            if(progressBar.getProgress() == progressBar.getMax()){
                tv01.setText("서브 스레드 작업 종료");
            }else {
                tv01.setText("서브스레드 작업 진행 중 ..." + progressBar.getProgress()+"%");
            }
        }
    }


}
