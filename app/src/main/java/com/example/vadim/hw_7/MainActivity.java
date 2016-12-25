package com.example.vadim.hw_7;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends MainMenu {

    Button button;
    TextView textView;
    TextView testViewProgress;
    private Handler handler;
    int i = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.TEXT_VIEW);
        button = (Button) findViewById(R.id.BUTTON_1);
        progressBar = (ProgressBar) findViewById(R.id.PROGRESS_BAR_1);
        testViewProgress = (TextView) findViewById(R.id.TEXT_VIEW_PROGRESS);

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {

                if (msg.what < 3){
                    progressBar.setProgress(msg.what);
                    testViewProgress.setText(R.string.START);
                    textView.setText("Загрузка = " + msg.what);
                }
                else if (msg.what == 3){
                    testViewProgress.setText(R.string.FINISH);
                    progressBar.setProgress(msg.what);
                    textView.setText("Загрузка = " + msg.what);
                    button.setEnabled(true);
                }
            }
        };


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                Thread thread = new Thread() {
                    public void run() {
                        for (int i = 1; i <= 3; i++) {
                            some_method();
                            handler.sendEmptyMessage(i);
                        }
                    }
                };
                thread.start();
            }
        });
    }



    public void some_method() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
