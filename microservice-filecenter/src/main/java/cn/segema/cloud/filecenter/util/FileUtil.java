package cn.segema.cloud.filecenter.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUtil {

	/**
	 * 上传文件
	 * @param file
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		long fileLength = new File(storeName).length();
		response.setContentType(contentType);
		String filename=realName;
		String header = request.getHeader("User-Agent").toUpperCase();
		if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {  
		    filename = URLEncoder.encode(filename, "UTF-8"); 
		    filename = filename.replace("+", "%20"); //IE下载文件名空格变+号问题
		} else { 
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");  
		}
		response.setHeader("Content-disposition", "attachment; filename="+ filename);
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(storeName));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
	
}
