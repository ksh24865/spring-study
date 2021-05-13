package com.springboot.community.batch.service;

import com.springboot.community.batch.repository.BoardPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class BoardPictureService {

    private final BoardPictureRepository boardPictureRepository;
    private int imageNum = 0;


    public BoardPictureService(BoardPictureRepository boardPictureRepository) {
        this.boardPictureRepository = boardPictureRepository;
    }

    public boolean SaveImageToServer(String data){
        data = data.replace("data:image/png;base64,", "").replace(' ', '+');
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
        try {
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufImg, "png", new File("C:/Users/seongho/Desktop/spring/img/image"+imageNum+".png"));
            imageNum++;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
