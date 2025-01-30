package com.example.postlink.service;

import com.example.postlink.model.Files;

public interface FilesService {

    public Files saveFiles(Files files);

    public Files getFilesByHash(String hash);

}
