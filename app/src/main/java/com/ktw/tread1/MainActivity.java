package com.ktw.tread1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mainCntNum;
    private int newCntNum;
    private TextView mainTv, newTv;
    private Button btnCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTv = (TextView)findViewById(R.id.tv_main_thread);
        newTv = (TextView)findViewById(R.id.tv_new1_thread);
        btnCnt = (Button)findViewById(R.id.btn_Counting);

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
        //새로운 스레드 생성
//        NewThread new1Thread = new NewThread();
        //startCount()와 Thread를 같이 종료시킨다
//        new1Thread.setDaemon(true);
//        new1Thread.start();

        //Thread를 이용하는 경우에는 인자가 필요없다
        //Runnable를 이용하는 경우에는 스레드에 인자로 Runnable객체를 사용한다
        NewRunnable runnable = new NewRunnable();

        Thread newThread = new Thread(runnable);
        newThread.setDaemon(true);
        newThread.start();

        mainTv.setText("mainCntNum :" + mainCntNum);
        newTv.setText("newCntNum:"+newCntNum);

    }
    //    내부클래스
//    class NewThread extends Thread {
//        @Override
//        public void run(){
//            //꼭 1초 동안 카운트 되는 건 아니다 CPU 상황에 따라 달라진다
//            while (true) {
//                newCntNum++;
//                try {
//                    Thread.sleep(1000);
//                }catch (Exception e){}
//            } //while()
//        }
//    }

    class NewRunnable implements Runnable {
        @Override
        public void run(){
            while (true){
                newCntNum++;
                try{
                    Thread.sleep(1000);
                }catch (Exception e){}
            }
        }
    }
}
