package com.example.dialogcheckinternetconnection.Model;

public class CourseModel {
    private String image;
    private String time;
    private String nameCourse;

    public CourseModel() {
    }

    public CourseModel(String image, String time, String nameCourse) {
        this.image = image;
        this.time = time;
        this.nameCourse = nameCourse;
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
