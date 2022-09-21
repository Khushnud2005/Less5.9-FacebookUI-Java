package uz.exemple.less59_facebookui_java.model;

import java.util.ArrayList;

public class PostModel {
    int profile;
    String fullname;
    int photo;
    boolean isUpdated = false;
    int[] photos = null;

    public PostModel(int profile, String fullname, int photo) {
        this.profile = profile;
        this.fullname = fullname;
        this.photo = photo;
        this.isUpdated = false;
    }
    public PostModel(int profile, String fullname, int[] photos) {
        this.profile = profile;
        this.fullname = fullname;
        this.photos = photos;
        this.isUpdated = false;
    }

    public PostModel(int profile, String fullname) {
        this.profile = profile;
        this.fullname = fullname;
        this.isUpdated = true;
    }
    public boolean isUpdated() {
        return isUpdated;
    }

    public int getProfile() {
        return profile;
    }

    public String getFullname() {
        return fullname;
    }

    public int getPhoto() {
        return photo;
    }

    public int[] getPhotos() {
        return photos;
    }
}
