package com.ktw.app2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mainCntNum;
    private int newCntNum;
    private TextView mainTv, newTv;
    private Button btnCnt;
    ProgressHandler progressHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTv = (TextView)findViewById(R.id.tv_main_thread);
        newTv = (TextView)findViewById(R.id.tv_new1_thread);
        btnCnt = (Button)findViewById(R.id.btn_Counting);
        progressHandler = new ProgressHandler();

        btnCnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCount();
            }
        });





    }
    // Main Thread 역할
    private void startCount(){
        mainCntNum++;

        NewThread new1Thread = new NewThread();

        new1Thread.setDaemon(true);
        new1Thread.start();


        mainTv.setText("mainCntNum :" + mainCntNum);
        newTv.setText("newCntNum:"+newCntNum);

    }
// 익명클래스를 이용한 Handler객체를 생성
//    Handler progressHandler = new Handler(){
//        public void handleMessage(Message msg){
//            if(msg.what == 1){
//                newTv.setText("newCntNum :" + newCntNum);
//            }
//        }
//    };
    class ProgressHandler extends Handler{
        public void handleMessage(Message msg){
            if(msg.what == 1){
                newTv.setText("newCntNum :" + newCntNum);
            }
        }
    }

        //내부클래스
    class NewThread extends Thread {
        @Override
        public void run(){
            //꼭 1초 동안 카운트 되는 건 아니다 CPU 상황에 따라 달라진다
            while (true) {
                newCntNum++;
                try {
                    Thread.sleep(1000);
                }catch (Exception e){}

                Message msg = Message.obtain();
               //메세지를 구분하기 위해
                msg.what = 1;
                msg.arg1 = 0;
                msg.arg2 = 0;
                msg.obj = null;

                progressHandler.sendMessage(msg);

            } //while()
        }
    }


}
