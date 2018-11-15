package com.yicheejia.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileRenameUtil {
	public static StringBuffer fileRename(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String time = sdf.format(new Date());
		StringBuffer buf = new StringBuffer(time);
		Random r = new Random();
		for(int x=0;x<3;x++){//循环取得三个不大于10的随机整数
			buf.append(r.nextInt(10));
		}
		return buf;
	}
}
