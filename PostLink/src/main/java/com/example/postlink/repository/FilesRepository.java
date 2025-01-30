package com.example.postlink.repository;

import com.example.postlink.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Integer> {

    public Files getFilesByHash(String hash);

}
