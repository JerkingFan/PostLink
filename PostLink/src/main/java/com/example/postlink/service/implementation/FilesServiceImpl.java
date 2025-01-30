package com.example.postlink.service.implementation;

import com.example.postlink.model.Files;
import com.example.postlink.repository.FilesRepository;
import com.example.postlink.service.FilesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesRepository repository;

    @Override
    public Files saveFiles(Files files) {
        String hash = HashUtil.generateHash();
        files.setHash(hash);
        return repository.save(files);
    }

    @Override
    public Files getFilesByHash(String hash) {
        return repository.getFilesByHash(hash);
    }
}


