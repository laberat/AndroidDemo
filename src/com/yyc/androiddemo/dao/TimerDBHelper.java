package com.yyc.androiddemo.dao;

import com.yyc.androiddemo.constant.Constant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TimerDBHelper extends SQLiteOpenHelper {
	public static final String count = "timercount";
	public static final String timerMin = "timermin";
	public static final String timerSec = "timersec";
	public static final String timerCentiSec = "timercentisec";
	public static final String timerDiffer = "timerdiffer";
	public static final String TABLE_NAME = "Timer_Table";// 秒表计时器，该表存储的是分时计次条目
	public static final String sql = "create table " + TABLE_NAME + "(" + count
			+ " varchar(20)," + timerMin + " varchar(20)," + timerCentiSec
			+ " varchar(20)," + timerSec + " varchar(20)," + timerDiffer
			+ " varchar(20))";

	public TimerDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public TimerDBHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	public TimerDBHelper(Context context, String name) {
		this(context, name, Constant.DEFAULT_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("SQLITE_TAG", "Create");
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("SQLITE_TAG", "Upgrade");
	}

}
