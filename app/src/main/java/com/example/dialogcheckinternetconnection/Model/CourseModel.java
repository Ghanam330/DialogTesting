package com.example.dialogcheckinternetconnection.Model;

public class CourseModel {
    private int id;
    private String image;
    private String time;
    private String nameCourse;

    public CourseModel() {
    }

    public CourseModel(int id, String image, String time, String nameCourse) {
        this.id = id;
        this.image = image;
        this.time = time;
        this.nameCourse = nameCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }
}
