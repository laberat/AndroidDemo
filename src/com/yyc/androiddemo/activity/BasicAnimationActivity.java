package com.yyc.androiddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yyc.androiddemo.R;

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

		// ***************** 平移 ************************
		btnXMove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimMove = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
						0, Animation.RELATIVE_TO_SELF, 1,
						Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 0);
				mAnimMove.setDuration(3000);
				mAnimMove.setFillAfter(true);
				// ↑ 设置image平移完成之后留在那里，不回到原处; 但是事实上那个位置并不能设置监听
				mAnimMove.setRepeatCount(Animation.INFINITE);
				// ↑ 设置无限次重复动画，当其他button被点击时，事件会停止；
				// ↑ 因为是无限次，因此上一个方法被覆盖掉了
				mAnimMove.setRepeatMode(Animation.REVERSE);
				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimMove);
			}
		});

		btnYMove.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mAnimMove = AnimationUtils.loadAnimation(
						BasicAnimationActivity.this, R.anim.basic_move_y);
				// mAnimMove.setRepeatCount(Animation.INFINITE);
				// mAnimMove.setRepeatMode(Animation.REVERSE);
				// ↑ 可以实现往复，但是如果本身没碰到墙壁，则该设置无效 -------这句话错了
				// ↑ 应该是如果没设置repeatCount，上句就会无效
				// ↑ 既然使用了XML定义动画，直接在XML中设置重复和往复就好了，不清楚为什么混合使用会失效

				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimMove);
			}
		});

		// ***************** 旋转 ************************
		btnXRotate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimRotate = new RotateAnimation(0, 90,
						Animation.RELATIVE_TO_SELF, 0,
						Animation.RELATIVE_TO_SELF, 1);
				// 0.5 0.5是沿着中心点旋转
				// 0 1就是沿着横坐标0，纵坐标100%，也就是左下角的点旋转
				mAnimRotate.setDuration(10000);
				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimRotate);
			}
		});

		btnYRotate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimRotate = AnimationUtils.loadAnimation(
						BasicAnimationActivity.this, R.anim.basic_rotate_y);
				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimRotate);
			}
		});

		// ***************** 缩放 ************************
		btnXScale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimScale = new ScaleAnimation(1, 2, 1, 0,
						Animation.RELATIVE_TO_SELF, 1,
						Animation.RELATIVE_TO_SELF, 1);
				// ↑ 如此设置会以1,1（即右下角顶点）为基点，X方向放大到两倍（向另一个定点方向放大），Y方向缩小到0（向本顶点方向缩小）
				// 最后会变成一条线，消失
				mAnimScale.setDuration(5000);
				mAnimScale.setFillAfter(true);
				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimScale);
			}
		});

		btnYScale.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAnimScale = AnimationUtils.loadAnimation(
						BasicAnimationActivity.this, R.anim.basic_scale_y);
				BasicAnimationActivity.this.mImageView
						.startAnimation(mAnimScale);
			}
		});
	}

}
