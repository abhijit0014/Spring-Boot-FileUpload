package com.example.UtilityImage.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String resizeDir;
    private String resizeFormat;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

	public String getResizeDir() {
		return resizeDir;
	}

	public void setResizeDir(String resizeDir) {
		this.resizeDir = resizeDir;
	}

	public String getResizeFormat() {
		return resizeFormat;
	}

	public void setResizeFormat(String resizeFormat) {
		this.resizeFormat = resizeFormat;
	}
}
