package com.furkanzayimoglu.appcentdemo.model;

public class ImageResponseModel {

   private PhotosModel photos;
   private String stat;

    public PhotosModel getPhotos() {
        return photos;
    }

    public void setPhotos(PhotosModel photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
