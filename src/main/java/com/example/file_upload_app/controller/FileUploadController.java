package com.example.file_upload_app.controller;

import com.example.file_upload_app.dto.FileMetadataDTO;
import com.example.file_upload_app.entity.File;
import com.example.file_upload_app.repository.FileRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileRepository fileRepository;
    private final RestTemplate restTemplate;

    // Allowed file types
    private final List<String> allowedFileTypes = Arrays.asList("image/jpeg", "image/png", "image/gif");

    public FileUploadController(FileRepository fileRepository, RestTemplate restTemplate) {
        this.fileRepository = fileRepository;
        this.restTemplate = restTemplate;
    }


    @Cacheable(value = "files", key = "#authentication.name")
    @GetMapping("/all")
    public List<FileMetadataDTO> getAllFiles(Authentication authentication) {
        String username = authentication.getName();


        List<File> files = fileRepository.findByUploadedBy(username);


        return files.stream()
                .map(file -> new FileMetadataDTO(file.getFileName(), file.getFileType(), file.getUploadedBy(), file.getMetadata()))
                .collect(Collectors.toList());
    }


    @CacheEvict(value = "files", key = "#authentication.name")
    @PostMapping("/upload")
    public ResponseEntity<List<FileMetadataDTO>> uploadFile(@RequestParam("file") MultipartFile file, Authentication authentication) {
        // Validate file type
        if (!allowedFileTypes.contains(file.getContentType())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);  // Return null in the body in case of error
        }

        try {
            // Fetch metadata from an external API (https://jsonplaceholder.typicode.com/todos/1)
            String externalApiUrl = "https://jsonplaceholder.typicode.com/todos/1";
            String externalApiResponse = restTemplate.getForObject(externalApiUrl, String.class);

            // Extract the current user's username from the authentication context
            String uploadedBy = authentication.getName();

            // Create a new File entity and populate it with the file data and metadata
            File newFile = new File();
            newFile.setFileName(file.getOriginalFilename());
            newFile.setFileType(file.getContentType());
            newFile.setUploadedBy(uploadedBy);
            newFile.setUploadedAt(LocalDateTime.now());
            newFile.setFileData(file.getBytes());  // Store the binary file data
            newFile.setMetadata(externalApiResponse);  // Save the external API response as metadata

            // Save the File entity in the database
            fileRepository.save(newFile);

            // Fetch the updated list of files uploaded by the user after the upload
            List<File> files = fileRepository.findByUploadedBy(uploadedBy);

            // Map File entities to FileMetadataDTO and return the updated list
            List<FileMetadataDTO> updatedFileList = files.stream()
                    .map(f -> new FileMetadataDTO(f.getFileName(), f.getFileType(), f.getUploadedBy(), f.getMetadata()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(updatedFileList);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);  // Return null in case of server error
        }
    }
}
