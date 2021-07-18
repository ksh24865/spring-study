package com.fileUpDownloadTutorial.service;

import com.fileUpDownloadTutorial.domain.Image;
import com.fileUpDownloadTutorial.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public Long add(Image image){
        imageRepository.save(image);
        return image.getId();
    }
}
