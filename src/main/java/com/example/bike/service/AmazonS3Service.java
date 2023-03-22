package com.example.bike.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.bike.configuration.ApplicationProperties;
import com.example.bike.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.entity.ContentType.*;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    public static final String IMAGE = "/image";
    private final ApplicationProperties properties;
    private final AmazonS3 amazonS3;
    private final Set<String> imageType = Set.of(
            IMAGE_PNG.getMimeType(),
            IMAGE_BMP.getMimeType(),
            IMAGE_GIF.getMimeType(),
            IMAGE_JPEG.getMimeType()
    );

    public String uploadImage(MultipartFile file) {
        if (file.isEmpty()) throw new BadRequestException("Invalid Image");
        if (!imageType.contains(file.getContentType()))
            throw new BadRequestException("FIle uploaded is not an image");
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        String path = this.properties.amazon().bucketName() + IMAGE;
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        try {
            PutObjectRequest objectRequest = new PutObjectRequest(path, name, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(objectRequest);
        } catch (IOException e) {
            throw new BadRequestException("Upload image fail");
        }
        return this.properties.amazon().bucketUrl() + IMAGE + "/" + name;
    }
}
