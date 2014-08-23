package com.yyc.androiddemo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yyc.androiddemo.bean.TimerCount;
import com.yyc.androiddemo.constant.Constant;

public class TimerDao {
	private Context mContext;
	private TimerDBHelper mHelper;

	public TimerDao(Context mContext) {
		super();
		this.mContext = mContext;
		this.mHelper = new TimerDBHelper(mContext, Constant.DB_NAME);
	}

	public long insertTimerCount(TimerCount timerCount) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = this.mHelper.getWritableDatabase();
		cv.put(TimerDBHelper.count, timerCount.getCount());
		cv.put(TimerDBHelper.timerMin, timerCount.getTimerMin());
		cv.put(TimerDBHelper.timerSec, timerCount.getTimerSec());
		cv.put(TimerDBHelper.timerCentiSec, timerCount.getTimerCentiSec());
		cv.put(TimerDBHelper.timerDiffer, timerCount.getTimerDiffer());
		long id = db.insert(TimerDBHelper.TABLE_NAME, null, cv);
		Log.i("TimerDao_tag", "the new record id is : " + id);
		return id;
	}
	
	public void clearTable(){
		SQLiteDatabase db = this.mHelper.getWritableDatabase();
		int i = db.delete(TimerDBHelper.TABLE_NAME, null, null);
		System.out.println("删除了 "+i+"行");
	}

	public List<TimerCount> getAll() {
		List<TimerCount> counts = new ArrayList<TimerCount>();
		SQLiteDatabase db = this.mHelper.getReadableDatabase();
		Cursor cursor = db.query(TimerDBHelper.TABLE_NAME, null, null, null,
				null, null, null);
		int countColumn = cursor.getColumnIndex(TimerDBHelper.count);
		int timerMinColumn = cursor.getColumnIndex(TimerDBHelper.timerMin);
		int timerSecColumn = cursor.getColumnIndex(TimerDBHelper.timerSec);
		int timerCentiSecColumn = cursor
				.getColumnIndex(TimerDBHelper.timerCentiSec);
		int timerDifferColumn = cursor
				.getColumnIndex(TimerDBHelper.timerDiffer);
		while (cursor.moveToNext()) {
			int count = cursor.getInt(countColumn);
			int timerMin = cursor.getInt(timerMinColumn);
			int timerSec = cursor.getInt(timerSecColumn);
			int timerCentiSec = cursor.getInt(timerCentiSecColumn);
			int timerDiffer = cursor.getInt(timerDifferColumn);
			TimerCount t = new TimerCount(count, timerMin, timerSec,
					timerCentiSec, timerDiffer);
			counts.add(t);
		}
		return counts;
	}

}
