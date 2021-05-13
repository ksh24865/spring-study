package com.springboot.community.batch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
@RestController
public class BoardPirctureController {
    //    MultipartHttpServletRequest multipartHttpServletRequest
//    public final int num = 0;

    @PostMapping("/board/list")
    public String createBoard(@RequestBody MultiValueMap<String, String> imagedata) {
        String data = imagedata.get("imagedata").get(0);
        data = data.replace("data:image/png;base64,", "").replace(' ', '+');
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
        int num = 0;
        try {
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
            ImageIO.write(bufImg, "png", new File("C:/Users/seongho/Desktop/spring/img/image"+num+".png"));
            num++;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return "fail";
        }
        return "success";
    }
}