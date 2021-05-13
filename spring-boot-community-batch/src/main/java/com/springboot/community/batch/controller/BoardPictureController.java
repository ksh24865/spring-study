package com.springboot.community.batch.controller;

import com.springboot.community.batch.service.BoardPictureService;
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
public class BoardPictureController {
    //    MultipartHttpServletRequest multipartHttpServletRequest
//    public final int num = 0;
    @Autowired
    BoardPictureService boardPictureService;

    @PostMapping("/board/list")
    public String createBoard(@RequestBody MultiValueMap<String, String> imagedata) {
        String data = imagedata.get("imagedata").get(0);
        if (!boardPictureService.SaveImageToServer(data)) {
            return "fail";
        }
        return "success";
    }
}