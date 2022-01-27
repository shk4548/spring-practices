package com.poscoict.fileupload.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/upload-images";
	public static String restore(MultipartFile multipartFile) {
		String url = "null";
		try {
		if(multipartFile.isEmpty()) {
			return url;
		}
		
		String originFileName = multipartFile.getOriginalFilename();
		long fileSize = multipartFile.getSize();
		
		
		System.out.println("################" + originFileName);
		System.out.println("################" + fileSize);
		
		
		// 파일 뽑아오기
		byte[] data =multipartFile.getBytes();
		OutputStream os = new FileOutputStream(SAVE_PATH + "E:\\eclipse-workspace\\spring-practices\\fileupload\\src\\main\\webapp\\upload-images");
		
		} catch(IOException ex) {  // mysite에서는 wrap 해서 던져버리는게 좋음
			throw new RuntimeException("file uplad error :" + ex);
		}
		return url;
	}

}
