package com.example.dialogcheckinternetconnection.Model;

public class ItemCourseModel {
    private String title , urlVideo,image, description;

    public ItemCourseModel() {
    }

    public ItemCourseModel(String title, String urlVideo, String image, String description) {
        this.title = title;
        this.urlVideo = urlVideo;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
