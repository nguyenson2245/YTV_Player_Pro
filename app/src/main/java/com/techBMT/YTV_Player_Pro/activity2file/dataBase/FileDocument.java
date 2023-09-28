package com.techBMT.YTV_Player_Pro.activity2file.dataBase;

import com.techBMT.YTV_Player_Pro.adapter.FileDocumentAdapter;


public class FileDocument {
    private String path;
    private String name;
    private FileDocumentAdapter.DOCUMENT_TYPE type;


    public FileDocument(String path, String name, FileDocumentAdapter.DOCUMENT_TYPE type) {
        this.path = path;
        this.name = name;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDocumentAdapter.DOCUMENT_TYPE getType() {
        return type;
    }

    public void setType(FileDocumentAdapter.DOCUMENT_TYPE type) {
        this.type = type;
    }
}
