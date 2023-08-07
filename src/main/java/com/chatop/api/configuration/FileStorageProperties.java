package com.chatop.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;
	private String webServerUrl;
}
