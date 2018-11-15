package com.yicheejia.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yicheejia.common.exception.RRException;



/**
 * 文件下载
 *
 * @author 	hunk
 * @date 	2018年7月12日
 * @Copyright 
 *
 * <pre>
 * =================Modify Record=================
 * Modifier			date				Content
 * cao_hailong		2017年12月18日			 新增
 *
 * </pre>
 */
public class DownloadUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadUtil.class);
	
    /**
     * 下载文件，并获取文件内容
     * @param fileName
     * @param urlStr
     * @return
     */
	public static String getFileResult(String fileName,String urlStr) {

		File downloadFile = null;
		try {
			logger.info("开始生成临时文件：" + fileName);
			downloadFile = File.createTempFile(fileName, null);
			// 因为影像系统不允许重复名称，所以临时文件是有部分随机数的，所以重新获取
			fileName = downloadFile.getName();
			if (!downloadFile.exists()) {
				downloadFile.createNewFile();
			}
			try {
				downLoadFromUrl(urlStr, downloadFile);
			} catch (IOException e) {
				e.printStackTrace();
				//throw new CommonException("下载文件异常:" + urlStr, e);
			}
			String result = readFile(downloadFile);
			logger.info("文件报文下载成功如下："+System.lineSeparator() + result);
			// 文件MD5校验一致一致性
			return result;
		} catch (IOException e) {
			logger.error("文件处理异常", e);
			// 如果异常，提前删除临时文件
			if (downloadFile != null) {
				downloadFile.deleteOnExit();
			}
			logger.error(e.getMessage(),e);
			throw new RRException("文件处理异常");
		} finally {
			if (downloadFile != null) {
				downloadFile.deleteOnExit();
			}
		}

	}
    
    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param downloadFile
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr,File downloadFile) throws IOException{
        //得到输入流
		InputStream inputStream = null;
		//文件保存位置
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlStr);  
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
			        //设置超时间为3秒
			conn.setConnectTimeout(3*1000);
			//防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			inputStream = conn.getInputStream();  
			//获取自己数组
			byte[] getData = readInputStream(inputStream);
			fos = new FileOutputStream(downloadFile);
			fos.write(getData);
		} catch (Exception e) {
			logger.error("文件处理异常", e);
			throw new  RRException("文件处理异常");
		}finally {
			if(fos!=null){
	            fos.close();  
	        }
	        if(inputStream!=null){
	            inputStream.close();
	        }
		}
    }
    
	/**
	 * 读取文件
	 * 
	 * @param remoteFilePath
	 *            远程文件路径
	 */
	public static String readFile(File file) {
		BufferedReader in = null;// 读取响应输入流 = null;
		StringBuilder result = new StringBuilder();// 返回的结果
		try {
			//防止屏蔽程序抓取而返回403错误
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line+System.lineSeparator());
			}
		} catch (Exception e) {
			logger.error("文件处理异常", e);
			throw new RRException("文件读取异常");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("文件处理异常", e);
				throw new RRException("文件读取异常");
			}
		}
		return result.toString();
	}
    
    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  
    
    /**
     * 判断含中文
     * @param str
     * @return
     */
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	/**  
     * 下载远程文件并保存到本地  
     * @param remoteFilePath 远程文件路径   
     * @param localFilePath 本地文件路径  
     */
    public static void downloadFile(String remoteFilePath, String localFilePath) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            logger.error("文件处理异常", e);
            throw new RRException("下载文件失败"+e.getMessage());
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RRException("下载文件失败");
            }
        }
    }
	
    

}
