package com.example.Cloudstorage.components;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file1 = (File) o;
        return hash.equals(file1.hash) && file.equals(file1.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, file);
    }
}
