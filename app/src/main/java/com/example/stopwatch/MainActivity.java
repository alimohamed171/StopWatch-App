package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.stopwatch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean isRunning = false;
    int timerSeconds = 0 ;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timerSeconds++;
            int hours = timerSeconds/3600;
            int minutes = (timerSeconds%3600)/60;
            int seconds = timerSeconds%60;
            String time = String.format("%02d:%02d:%02d",hours,minutes,seconds);

            binding.txt.setText(time);
            handler.postDelayed(this,1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
                Toast.makeText(MainActivity.this, "Timer Started", Toast.LENGTH_SHORT).show();
            }
        });
        binding.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              stopTimer();
                Toast.makeText(MainActivity.this, "Timer Stopped", Toast.LENGTH_SHORT).show();
            }
        });
        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              resetTimer();
                Toast.makeText(MainActivity.this, "Timer Reset", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startTimer(){
        if(!isRunning){
            handler.postDelayed(runnable,1000);
            isRunning = true;
            binding.start.setEnabled(false);
            binding.stop.setEnabled(true);
            binding.reset.setEnabled(true);
            // enable for start false
            // for stop & reset true
        }
    }
    private void stopTimer(){
        if (isRunning){
            handler.removeCallbacks(runnable);
            isRunning = false;
            binding.start.setText("Resume");
            binding.start.setEnabled(true);
            binding.stop.setEnabled(false);
            binding.reset.setEnabled(true);
            // enable for stop false
            // start text will be resume
            // for start & reset true
        }
    }
    private void resetTimer(){

            stopTimer();
            timerSeconds = 0 ;
            binding.txt.setText("00:00:00");
            binding.start.setText("Start");
            binding.start.setEnabled(true);
            binding.reset.setEnabled(false);


            //set text 00:00:00
            // set start text and enable true
            // reset false
            // انا هنا ناديت الاول stop  عشان كدا مش محتاج اعملها false


    }



}