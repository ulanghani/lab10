package com.example.arsalan.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity {
private ProgressBar progress;
Button btn;
Integer count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=findViewById(R.id.pb);
        btn=findViewById(R.id.btn);
        btn.setText("start");
        OnClickListener listener = new OnClickListener() {
            public void onClick(View view) {
                count =1;
                progress.setVisibility(View.VISIBLE);
                progress.setProgress(0);
                switch (view.getId()) {
                    case R.id.btn:
                        new AsyncTaskRunner().execute(10);
                        break;
                }
            }
        };
        btn.setOnClickListener(listener);
    }

    private class AsyncTaskRunner extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setMax(10);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (; count<=params[0]; count++){
                try {
                    Thread.sleep(2000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "task completed";

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"completed",Toast.LENGTH_SHORT).show();
        }
    }
}
