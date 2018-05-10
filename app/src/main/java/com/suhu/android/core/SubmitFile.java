package com.suhu.android.core;

import java.io.File;

/**
 * @author: 苏虎
 * @email: suhu0824@gmail.com
 * @data: 2018/1/9 13:18
 * @description:
 */

public class SubmitFile {
    private String name;
    private String fileName;
    private File file;

    public SubmitFile() {
    }

    public SubmitFile(String name, String fileName, File file) {
        this.name = name;
        this.fileName = fileName;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
