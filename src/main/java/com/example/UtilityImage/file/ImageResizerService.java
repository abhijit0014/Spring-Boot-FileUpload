package com.example.UtilityImage.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageResizerService {
 
	@Autowired
	private FileStorageProperties fileStorageProperties;
	
    public void resize(String inputImagePath, int scaledWidth, int scaledHeight)
            throws IOException, URISyntaxException {
    		
    	URL url = new URL(inputImagePath);
        BufferedImage inputImage = ImageIO.read(url);
        

        float aspectRatio = (float) inputImage.getWidth() / (float) inputImage.getHeight();
        System.out.println(aspectRatio);
        System.out.println(scaledHeight * aspectRatio);
        scaledWidth = (int) ( scaledHeight * aspectRatio );        	
 
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
        
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = fileStorageProperties.getResizeFormat();
        String outputImagePath = Paths.get(fileStorageProperties.getResizeDir())
                .toAbsolutePath().normalize()+
        		"\\"+new Date().getTime()+"."+fileStorageProperties.getResizeFormat();

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    public void resize(String inputImagePath, double percent) 
            		throws IOException, URISyntaxException {
        URL inputFile = new URL(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, scaledWidth, scaledHeight);
    }
 
}
