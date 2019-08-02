package com.wethebest.spaceinvaders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import com.wethebest.spaceinvaders.BackgroundSoundService;




import androidx.appcompat.app.AppCompatActivity;

/*
Intro is the main activity
Buttons go to SpaceInvaders or Story
Options menu goes to Settings, SpaceInvaders, or Intro
 */

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //background music
        new BackgroundSoundService();

        Button Storybtn = findViewById(R.id.Story_btn);
        Button Playbtn = findViewById(R.id.Play_btn);

        //Story button goes directly to Story class
        Storybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intro.this,Story.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });

        //goes directly to the game
        Playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intro.this,SpaceInvaders.class);
                startActivityForResult(intent,0);
                overridePendingTransition( R.anim.trans_left_in, R.anim.trans_left_out );

            }
        });


    }


    //main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //items in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(item.getItemId() == R.id.action_startover) {
            startActivity(new Intent(Intro.this, SpaceInvaders.class));

        }
        if(item.getItemId() == R.id.action_newgame) {
            startActivity(new Intent(Intro.this, Intro.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //back button forces to go to intro activity and stops app from crashing
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            startActivity(new Intent(Intro.this, Intro.class));

        }
        return super.onKeyDown(keyCode, event);
    }

    private MediaPlayer mMediaPlayer;
    @Override
    protected void onResume() {
        super.onResume();
        mMediaPlayer = MediaPlayer.create(this,R.raw.example);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    protected void onPause() {
        mMediaPlayer.stop(); mMediaPlayer.release();
        super.onPause();
    }

}
