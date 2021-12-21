package com.example.fast1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {

    private Button btn4;
    private ProgressBar progressBar, progressBar2;
    private TextView textView;
    private ImageView imageView;
    private int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btn4= (Button) findViewById(R.id.btn4);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        progressBar2= (ProgressBar) findViewById(R.id.progressBar2);
        textView= (TextView) findViewById(R.id.textView);
        imageView= (ImageView) findViewById(R.id.imageView);

        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                progressBar2.incrementProgressBy(10);
            }
        };

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<10; i++){
                    new Handler().postDelayed(runnable, 100000);
                }
                new Prueba().execute();
            }
        });
    }

    class Prueba extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView.setText("Iniciando sesion");
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            textView.setText("ok");
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }
    }
}