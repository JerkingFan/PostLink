package com.example.postlink.controller;

import com.example.postlink.model.Files;
import com.example.postlink.service.FilesService;
import com.example.postlink.service.implementation.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1/files")
public class FilesController {

    private static final Logger logger = LoggerFactory.getLogger(FilesController.class);
    private final FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping
    public ResponseEntity<String> saveFiles(@RequestParam("file") MultipartFile file) {
        try {
            // Проверяем имя файла
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: имя файла пустое!");
            }

            // Логируем оригинальное имя
            logger.info("Загруженный файл: {}", originalFilename);

            // Сохраняем файл
            String filePath = "F:\\tech_task\\files\\" + originalFilename;
            File dest = new File(filePath);
            logger.info("Сохранение файла: {}", filePath);
            file.transferTo(dest);

            // Сохраняем в БД
            Files files = new Files();
            files.setFilePath(filePath);
            files.setText(getFileExtension(originalFilename)); // Сохраняем расширение файла
            files.setHash(HashUtil.generateHash());
            Files savedFiles = filesService.saveFiles(files);

            return ResponseEntity.ok("Файл успешно загружен. Хэш: " + savedFiles.getHash());
        } catch (IOException e) {
            logger.error("Ошибка загрузки файла", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка загрузки файла");
        }
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }



    @GetMapping("/{hash}")
    public String downloadFile(@PathVariable String hash) {
        Files files = filesService.getFilesByHash(hash);
        if (files == null) {
            logger.warn("Файл с хэшем {} не найден в БД", hash);
            return "redirect:/error"; // Редирект на страницу ошибки, если файл не найден
        }

        String absoluteFilePath = new File(files.getFilePath()).getAbsolutePath();
        logger.info("Абсолютный путь к файлу: {}", absoluteFilePath);

        return "redirect:" + absoluteFilePath;
    }





}
