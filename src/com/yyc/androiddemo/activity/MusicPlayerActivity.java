package com.yyc.androiddemo.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.yyc.androiddemo.R;

public class MusicPlayerActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_musicplayer);
		
		MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.fuckyou);
		mediaPlayer.start(); 
	}
}
