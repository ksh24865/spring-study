package com.fileUpDownloadTutorial.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Entity;
import javax.swing.filechooser.FileSystemView;

@ConfigurationProperties("storage")
//@Entity
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = FileSystemView.getFileSystemView().getHomeDirectory().toString()+"/files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}