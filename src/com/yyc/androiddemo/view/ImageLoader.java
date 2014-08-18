package com.yyc.androiddemo.view;

import com.yyc.androiddemo.util.HttpDownloaderUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader extends ImageView {

	public ImageLoader(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ImageLoader(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageLoader(Context context) {
		super(context);
	}
	
	
	public void setUrl(final String urlString){
		new Thread( new Runnable() {
			
			@Override
			public void run() {
				Log.e("TAG", "....");
				final Bitmap bitmap = HttpDownloaderUtil.getBitmap(urlString);
				ImageLoader.this.post(new Runnable() {
					
					@Override
					public void run() {
						ImageLoader.this.setImageBitmap(bitmap);
					}
				});
			  
			}
		}).start();

	}

}
