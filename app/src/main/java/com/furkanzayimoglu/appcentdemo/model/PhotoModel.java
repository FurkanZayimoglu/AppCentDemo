package com.furkanzayimoglu.appcentdemo.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotoModel implements Parcelable {

    public static final String TAG = PhotoModel.class.getSimpleName();
    public static final String PARCEL_PHOTO_KEY = TAG + " " + "PARCEL_KEY";


     private String id;
     private String owner;
     private String secret;
     private String server;
     private int farm;
     private String title;
     private int ispublic;
     private int isfriend;
     private int isfamily;
     private String url_s;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }


    private PhotoModel(String id, String owner, String secret, String server, int farm, String title, int ispublic, int isfriend, int isfamily, String url_s){
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.url_s = url_s;
    }

    public PhotoModel(){
    }




    public static final Parcelable.Creator<PhotoModel> CREATOR = new Creator<PhotoModel>(){

        @Override
        public PhotoModel createFromParcel(Parcel parcel) {
            return new PhotoModel(parcel);
        }

        @Override
        public PhotoModel[] newArray(int i) {
            return new PhotoModel[i];
        }
    };



    private PhotoModel(Parcel in){
        this(
                in.readString(),
                in.readString(),
                in.readString(),
                in.readString(),
                in.readInt(),
                in.readString(),
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readString()
        );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(owner);
        out.writeString(secret);
        out.writeString(server);
        out.writeInt(farm);
        out.writeString(title);
        out.writeInt(ispublic);
        out.writeInt(isfriend);
        out.writeInt(isfamily);
        out.writeString(url_s);

    }


    public PhotoModel getParcelable(Bundle savedInstanceState){
        return savedInstanceState.getParcelable(PARCEL_PHOTO_KEY);
    }

    public void putParcelable(Bundle savedInstanceState, PhotoModel photo){
        savedInstanceState.putParcelable(PARCEL_PHOTO_KEY, photo);
    }
}
