package com.furkanzayimoglu.appcentdemo.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PhotosModel implements Parcelable {

    public static final String TAG = PhotosModel.class.getSimpleName();
    public static final String PARCEL_PHOTOARRAY_KEY = TAG + " " + "PARCEL_KEY";



    private int page;
    private int pages;
    private int perPage;
    private String total;
    private ArrayList<PhotoModel> photo = new ArrayList<>();


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<PhotoModel> getPhotoModels() {
        return photo;
    }

    public void setPhotoModels(ArrayList<PhotoModel> photoModels) {
        this.photo = photoModels;
    }

    public PhotosModel() {
    }

    private PhotosModel(ArrayList<PhotoModel> photosModel){
        this.photo = photosModel;
    }




    public static final Creator<PhotosModel> CREATOR = new Parcelable.Creator<PhotosModel>(){
        @Override
        public PhotosModel createFromParcel(Parcel in) {
            return new PhotosModel(in);
        }

        @Override
        public PhotosModel[] newArray(int i) {
            return new PhotosModel[i];
        }
    };

    private PhotosModel(Parcel in){
        this(new ArrayList<PhotoModel>());
        in.readTypedList(photo,PhotoModel.CREATOR);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(photo);

    }

    public static void putParcelableArray(Bundle savedInstanceState, PhotosModel photos){
        savedInstanceState.putParcelable(PARCEL_PHOTOARRAY_KEY, photos);
    }

    public static PhotosModel getParcelableArray(Bundle savedInstanceState){
        return savedInstanceState.getParcelable(PARCEL_PHOTOARRAY_KEY);
    }
}
