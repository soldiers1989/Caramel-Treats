package com.otc.api.util;

import sun.misc.BASE64Decoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

public class BASE64 {

	public static byte[] getFromBASE64(String s) throws IOException {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(s);
		for(int i=0;i<b.length;++i)
		{
			if(b[i]<0)
			{//调整异常数据
				b[i]+=256;
			}
		}
		return b;
	}
	
	public static String getImageStr(String imgFile) {
	    InputStream inputStream = null;
	    byte[] data = null;
	    try {
	        inputStream = new FileInputStream(imgFile);
	        data = new byte[inputStream.available()];
	        inputStream.read(data);
	        inputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 加密
	    Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(data);
	    return encode.replace("+", "%2B");
	}
}