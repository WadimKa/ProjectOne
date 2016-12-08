package com.example.waadi.projectone;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    ImageView imageView;
    int GLOBAL = 0;
    String DATA_STREAM = "";
    MediaPlayer mediaPlayer;
    Button bStart, bStop, bPause, bResume;

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(GLOBAL==1)
        {
            mediaPlayer.release();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_activity);

        DATA_STREAM = getIntent().getExtras().getString("path");

        imageView = (ImageView) findViewById(R.id.imageView);
        bStart = (Button) findViewById(R.id.buttonTest);
    }

    public void onClick(View view) throws IOException
    {
            if (GLOBAL==0)
            {
                mediaPlayer = new MediaPlayer();
                Log.d("B", "create");
                mediaPlayer.setDataSource(DATA_STREAM);
                Log.d("B", "set source");
                // mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare();
                mediaPlayer.start();

                imageView.setImageResource(R.drawable.turn_off);
                GLOBAL=1;
            }
             else
            {
                mediaPlayer.release();
                imageView.setImageResource(R.drawable.turn_on);
                GLOBAL=0;
            }

    }

    public void testClick(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.first);
        bStart.startAnimation(animation);
    }
}

