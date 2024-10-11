package com.example.file_upload_app.dto;

public class FileMetadataDTO implements java.io.Serializable{

    private String fileName;
    private String fileType;
    private String uploadedBy;
    private String metadata;


    public FileMetadataDTO(String fileName, String fileType, String uploadedBy, String metadata) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadedBy = uploadedBy;
        this.metadata = metadata;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
