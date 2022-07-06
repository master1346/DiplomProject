package com.example.Cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "filename", "size" })
public class FileProperty {
    private String fileName;
    private int size;

    @JsonProperty("filename")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public FileProperty(String fileName, int size) {
        this.fileName = fileName;
        this.size = size;
    }
    public FileProperty() {
    }
}
