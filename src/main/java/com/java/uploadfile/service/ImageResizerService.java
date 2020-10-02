package com.java.uploadfile.service;

import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;
import javax.imageio.ImageIO;  
import org.springframework.stereotype.Service;

@Service
public class ImageResizerService {

	public File resizeImage(String srcImgPath, String distImgPath, int width,
			int height) throws IOException {

		File srcFile = new File(srcImgPath);
		Image srcImg = ImageIO.read(srcFile);
		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffImg.getGraphics().drawImage(
				srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		ImageIO.write(buffImg, "JPEG", new File(distImgPath));
		
		return new File(distImgPath);
	}


}  