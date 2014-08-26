package com.yyc.androiddemo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class FileIOUtil {
	public static List<String> readFileList(String path){//参数为文件所在的相对目录，如"Music"
		List<String> list = new ArrayList<String>();
		String uri = Environment.getExternalStorageDirectory() + File.separator + path;
		File file = new File(uri);
		for(File f : file.listFiles()){
			list.add(f.getName());
			System.out.println(f.getName());
		}
		
		return list;
	}
}
