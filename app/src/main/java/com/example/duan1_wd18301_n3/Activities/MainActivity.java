package com.example.duan1_wd18301_n3.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.duan1_wd18301_n3.Login_Register.LoginActivity;
import com.example.duan1_wd18301_n3.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.CubeGrid;

public class MainActivity extends AppCompatActivity {
    public static int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        Sprite sprite = new Circle();
        progressBar1.setIndeterminateDrawable(new Circle());

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    Intent i = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();
    }
    //
    protected void onPause() {
        super.onPause();
        finish();
    }

}