package com.chatop.api.service;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	
	public static String storePicturePath(MultipartFile file, String prefixPath, String uploadDir, String webServerUrl) {
        String path = "";
        try {
            path = uploadDir + prefixPath + file.getOriginalFilename(); //"D:/ProjetsAngular/Formation OC/P3 - ChâTop back-end/Developpez-le-back-end-en-utilisant-Java-et-Spring/src/assets/RentalPictures/"+prefixPath+file.getOriginalFilename();
            System.out.println(123 + path);
            File newFile = new File(path);
            newFile.createNewFile();
            FileOutputStream myfile = new FileOutputStream(newFile);
            path = webServerUrl + prefixPath + file.getOriginalFilename();//"http://127.0.0.1:3002/"+ prefixPath + file.getOriginalFilename();
            myfile.write(file.getBytes());

            myfile.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
