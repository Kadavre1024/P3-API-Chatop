package com.chatop.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;
	private String webServerUrl;
	
	public String getUploadDir() {
		System.out.println("UPLOADDIR" + uploadDir);
		return uploadDir;
	}
	
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	public String getWebServerUrl() {
		System.out.println("WEBSERVER" + webServerUrl);
		return webServerUrl;
	}
	
	public void setWebServerUrl(String webServerUrl) {
		this.webServerUrl = webServerUrl;
	}
	
}
