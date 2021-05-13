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
    @PostMapping("/board/list")
    public String createBoard(@RequestBody MultiValueMap<String, String> imagedata){
//    public String createBoard(@RequestBody MultipartHttpServletRequest imagedata){
        List<String> data = imagedata.get("imagedata");
        String data2 = data.get(0);
        data2=data2.replace("data:image/png;base64,","").replace(' ','+');
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(data2);

//        System.out.println("!!!!!"+data2+"!!!!!!!");
        try {
            System.out.println("h");
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));

            ImageIO.write(bufImg, "jpg", new File("k.jpg"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return "bye, faker";
        }
        return "hi, faker";
    }
//    a= a.replace("대한", "민국");
//    str_replace(' ','+',str_replace('data:image/png;base64,','',$_POST["imagedata"]));
////                MultipartHttpServletRequest multipartHttpServletRequest
//        ) throws Exception {
//            Board board = boardService.addBoard(Board.builder()
//                    .user(user)
//                    .content(content)
//                    .build(), files);
//
//            URI uriLocation = new URI("/board/" + board.getID());
//            return ResponseEntity.created(uriLocation).body("{}");
//        }

}
