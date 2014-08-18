package com.yyc.androiddemo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LocationDBhelper extends SQLiteOpenHelper {

	public static final int DEFAULT_VERSION = 1;
	public static final String title = "title";
	public static final String description = "description";
	public static final String picURL = "url";
	public static final String TABLE_NAME = "Location_Table";
	public static final String sql = "create table " + TABLE_NAME + "(" + title
			+ " varchar(20)," + description + " varchar(200)," + picURL
			+ " varchar(100))";

	public LocationDBhelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public LocationDBhelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	public LocationDBhelper(Context context, String name) {
		this(context, name, DEFAULT_VERSION);
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
