package com.example.vadim.hw_7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends MainMenu {

    Button button;
    TextView textViewProgress;
    ProgressBar progressBar;
    MyTask myTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        button = (Button) findViewById(R.id.BUTTON_2);
        progressBar = (ProgressBar) findViewById(R.id.PROGRESS_BAR_2);
        textViewProgress = (TextView) findViewById(R.id.TEXT_VIEW_PROG_2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask = new MyTask();
                myTask.execute();
            }
        });
    }

    public class MyTask extends AsyncTask<String, Integer, String> {

        static final int PROGRESS_MAX = 3;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
            textViewProgress.setText(R.string.START);
            button.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            int i;
            for (i = 1; i <= 3; i++) {
                some_method();
                publishProgress(i);
            }

            return ("Операция выполнена");
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            textViewProgress.setText(R.string.FINISH);
            progressBar.setProgress(PROGRESS_MAX);
            button.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }


        private void some_method() {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
