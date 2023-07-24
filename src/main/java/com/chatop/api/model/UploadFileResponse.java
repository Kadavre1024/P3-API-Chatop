package com.chatop.api.model;

import lombok.Data;

@Data
public class UploadFileResponse {

	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
