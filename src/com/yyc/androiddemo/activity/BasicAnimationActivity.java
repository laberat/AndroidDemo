package com.yyc.androiddemo.activity;

import com.yyc.androiddemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class BasicAnimationActivity extends Activity {

	private Button btnXMove;
	private Button btnYMove;
	private Button btnXRotate;
	private Button btnYRotate;
	private Button btnXScale;
	private Button btnYScale;
	
	private Animation mAnimMove;
	private Animation mAnimRotate;
	private Animation mAnimScale;
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basicanimation);
		initView();
	}

	private void initView() {
		btnXMove = (Button) findViewById(R.id.btn_basicanim_X_move);
		btnYMove = (Button) findViewById(R.id.btn_basicanim_Y_move);
		btnXRotate = (Button) findViewById(R.id.btn_basicanim_X_rotate);
		btnYRotate = (Button) findViewById(R.id.btn_basicanim_Y_rotate);
		btnXScale = (Button) findViewById(R.id.btn_basicanim_X_scale);
		btnYScale = (Button) findViewById(R.id.btn_basicanim_Y_scale);
		mImageView = (ImageView) findViewById(R.id.imageview_basicanim_display);
		
		btnXMove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimMove = new TranslateAnimation(10, 100, 10, 100);
				mAnimMove.setDuration(3000);
				BasicAnimationActivity.this.mImageView.startAnimation(mAnimMove);
			}
		});
	}

}
