package com.chatop.api.service;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.configuration.FileStorageProperties;

@Service
public class FileStorageService {

	@Autowired
	private static FileStorageProperties storageProperties;

	public static String storePicturePath(MultipartFile file, String prefixPath) {
        String path = "";
        try {
            path = storageProperties.getUploadDir() + prefixPath + file.getOriginalFilename(); //"D:/ProjetsAngular/Formation OC/P3 - ChâTop back-end/Developpez-le-back-end-en-utilisant-Java-et-Spring/src/assets/RentalPictures/"+prefixPath+file.getOriginalFilename();
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream myfile = new FileOutputStream(newFile);
            path = storageProperties.getWebServerUrl() + prefixPath + file.getOriginalFilename();//"http://127.0.0.1:3002/"+ prefixPath + file.getOriginalFilename();
            myfile.write(file.getBytes());

            myfile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
