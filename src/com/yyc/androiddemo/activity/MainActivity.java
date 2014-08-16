package com.yyc.androiddemo.activity;

import com.yyc.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnStartPersonList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("first create project");
		//test
		
		

		initView();
		initListener();
	}

	private void initView() {
		btnStartPersonList = (Button) findViewById(R.id.btn_main_start_personlist);
	}

	private void initListener() {
		btnStartPersonList.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PersonListActivity.class);
				startActivity(intent);
			}
		});
	}
	

}
