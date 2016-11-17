package com.example.muhammed.eggtimer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    SeekBar seekbar;
    TextView textView;
    Button btn;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    Boolean activeMode =false;


    public void setCountDownTime(View view){

        if (activeMode==false) {
            activeMode=true;
            seekbar.setEnabled(false);
            btn.setText("Stop");
            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setSeekbarAction((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    mediaPlayer.start();
                    textView.setText("00:00");
                    resetTimer();
                }
            }.start();
        }else{
            resetTimer();

        }
    }
    public  void resetTimer(){
        activeMode=false;
        countDownTimer.cancel();
        seekbar.setEnabled(true);
        btn.setText("Go!");
        textView.setText("00:30");
    }
    public void setSeekbarAction(int Second){
        int minute=(int)Second/60;
        int second=Second - minute *60;
        String Minutes=Integer.toString(minute);
        String Seconds=Integer.toString(second);
        if (Seconds =="0"){
            Seconds="00";
        }
         else if(second <=9 && second>0){
            Seconds="0"+second;
        }
         if(minute <=9 && minute>0){
            Seconds="0"+second;
        }

        textView.setText(Minutes +":"+Seconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.music);
        textView=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);
        seekbar=(SeekBar)findViewById(R.id.seekBar);
        seekbar.setMax(1800);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               setSeekbarAction(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}
