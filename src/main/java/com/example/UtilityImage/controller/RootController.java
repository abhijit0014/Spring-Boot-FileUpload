package com.example.UtilityImage.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.UtilityImage.file.ImageResizerService;
import com.example.UtilityImage.file.StorageService;

@RestController
public class RootController {
	
    private final StorageService storageService;
    private final ImageResizerService imageResizerService;

    @Autowired
    public RootController(StorageService storageService, ImageResizerService imageResizerService) {
        this.storageService = storageService;
        this.imageResizerService = imageResizerService;
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = storageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/")
                .path(fileName)
                .toUriString();
        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 100;
            int scaledHeight = 200;
            imageResizerService.resize(fileDownloadUri, scaledWidth, scaledHeight);
            // resize smaller by 50%
            double percent = 0.5;
            imageResizerService.resize(fileDownloadUri, percent);
            // resize bigger by 50%
            percent = 1.5;
            imageResizerService.resize(fileDownloadUri, percent);
 
        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
        
        return fileDownloadUri;
    }
}
