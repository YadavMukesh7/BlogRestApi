package com.springboot.blog.service.Impl;

import com.springboot.blog.entity.Document;
import com.springboot.blog.repository.DocumentRepository;
import com.springboot.blog.utils.FileUploadUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(String name, String description, MultipartFile[] files) throws IOException {

        List<String> docs = new ArrayList<>();
        for (MultipartFile file : files) {
            // Get the original filename and extension
            String fileName = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(fileName);

            String fileNameWithoutExtension = fileName.replaceAll("\\.\\w+$", "");
            String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String finalFileName = fileNameWithoutExtension + '-' + fileSuffix + '.' + ext;
            System.out.println(finalFileName);
            // Save the file to disk
            String uploadDir = "uploads/";
            FileUploadUtil.saveFile(uploadDir, finalFileName, file);
            docs.add(uploadDir + finalFileName);
        }
        Document d = new Document();
        d.setName(name);
        d.setDescription(description);
        d.setDocument(docs);
        return documentRepository.save(d);
    }
}
