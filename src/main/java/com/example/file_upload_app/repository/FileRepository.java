package com.example.file_upload_app.repository;

import com.example.file_upload_app.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByUploadedBy(String uploadedBy);
}
