package com.example.Cloudstorage.components;

public class File {
    private String hash;
    private String file;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public File(String hash, String file) {
        this.hash = hash;
        this.file = file;
    }
}
