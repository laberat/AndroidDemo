package com.yyc.androiddemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class HttpDownloaderUtil {
	public static InputStream httpDownload(String url, String method){
		URL mUrl = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		int attempt = 0;
		
		try {
			mUrl = new URL(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
			System.out.println("URI is invalid");
		}
		do {
			try {
				connection = (HttpURLConnection) mUrl.openConnection();
				connection.setRequestMethod(method);
				connection.setDoInput(true);
				if (method == "GET") {
					connection.setDoOutput(false);
				} else if(method == "POST"){
					connection.setDoOutput(true);
				}
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(15000);
				is = connection.getInputStream();
				attempt = 3;
			} catch (IOException e) {
				e.printStackTrace();
				attempt++;
				if (attempt == 3) {
					System.out.println("Read Timeout");
					if (is != null) {
						try {
							is.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					return null;
				}
			}
		} while (attempt < 3);
		return is;
	}
	
	public static String getJSON(String url) {
		String resultString = "";
		String thisLine = null;
		InputStream is = HttpDownloaderUtil.httpDownload(url, "POST");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();
		try {
			while ((thisLine = br.readLine()) != null) {
				builder.append(thisLine);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		resultString = builder.toString();
		return resultString;
	}

	public static Bitmap getBitmap(String url) {
		InputStream is = HttpDownloaderUtil.httpDownload(url, "GET");
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		try {
			if (is != null)
				is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
