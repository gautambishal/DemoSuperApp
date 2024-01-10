package com.example.demo.service.impl;

import com.example.demo.exception.DroneException;
import com.example.demo.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    private final String UPLOAD_LOCATION = "uploads";
    @Override
    public String uploadFile(byte[] bytes,String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new DroneException("Unable to write file with issue");
        }
        return path.toString();
    }
}
