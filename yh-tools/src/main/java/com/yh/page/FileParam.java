package com.yh.page;

public class FileParam {
    private String filePath;
    private String fileName;
    private int lineSize = '\uea60';

    public FileParam(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public FileParam(String filePath, String fileName, int lineSize) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.lineSize = lineSize;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineSize() {
        return this.lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }

    public String toString() {
        return "FileParam [filePath=" + this.filePath + ", fileName=" + this.fileName + ", lineSize=" + this.lineSize + "]";
    }
}
