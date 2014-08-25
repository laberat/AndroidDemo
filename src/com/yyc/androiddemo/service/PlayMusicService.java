package com.yyc.androiddemo.service;

import com.yyc.androiddemo.R;
import com.yyc.androiddemo.activity.MusicPlayerActivity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayMusicService extends Service {
	private MediaPlayer mPlayer;
	private static final String TAG = "MusicServiceTag";

	@Override
	public IBinder onBind(Intent intent) {
		Log.i("SERVICE_TAG", "here onBind is called");
		return null;
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "here onCreate is called");
		mPlayer = MediaPlayer.create(this, R.raw.fuckyou);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "here onStartCommand is called");
		int value = intent.getIntExtra("operate", 0);
		switch (value) {
		case 0x0100://播放按钮发送的intent
			play();
			break;
		case 0x0101://暂停按钮发送的intent
			pause();
			break;
		case 0x0102://下一曲按钮发送的intent
			next();
			break;
		case 0x0103://上一曲按钮发送的intent
			previous();
			break;
		case 0x0104://停止按钮发送的intent
			stop();
			break;
		case 0://intent中没有获得整数值
			Log.i(TAG, "Intent error: no extra int");
			Log.i(TAG, "start service here");
		default:
			break;
		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void play() {
		if (! mPlayer.isPlaying()) {
			mPlayer.start();
		}
	}

	private void pause() {
		if (mPlayer.isPlaying()) {
			mPlayer.pause();
		}
	}

	private void next() {
		Log.i(TAG, "next");
	}

	private void previous() {
		Log.i(TAG, "previous");
	}

	private void stop() {
		if (mPlayer.isPlaying()) {
			mPlayer.stop();
			//如果不重新create出来一个mediaplayer，就会失效
			mPlayer = MediaPlayer.create(this, R.raw.fuckyou);
		}
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "here onDestroy is called");
		mPlayer.stop();
		mPlayer.release();
		super.onDestroy();
	}

}
