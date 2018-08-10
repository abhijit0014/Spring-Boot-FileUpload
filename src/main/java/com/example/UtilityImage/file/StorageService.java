package com.example.UtilityImage.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String storeFile(MultipartFile file);

	Resource loadFileAsResource(String fileName);

}