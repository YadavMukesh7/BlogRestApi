package com.springboot.blog.service.Impl;

import com.springboot.blog.entity.Image;
import com.springboot.blog.repository.ImageRepository;
import com.springboot.blog.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;

    public List<Image> uploadImage(MultipartFile[] files) throws IOException {

        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : files) {
            Image img = new Image();
            img.setName(file.getOriginalFilename());
            img.setType(file.getContentType());
            img.setImageData(ImageUtil.compressImage(file.getBytes()));
            imageRepo.save(img);
            imageList.add(img);
        }
        return imageList;
    }

    public byte[] downloadImage(String fileName) {
        Optional<Image> imageData = imageRepo.findByName(fileName);
        return ImageUtil.decompressImage(imageData.get().getImageData());
    }
}
