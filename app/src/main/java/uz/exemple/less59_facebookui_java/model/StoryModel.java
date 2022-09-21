package uz.exemple.less59_facebookui_java.model;

public class StoryModel {
    int profile;
    String fullname;

    public StoryModel(int profile, String fullname) {
        this.profile = profile;
        this.fullname = fullname;
    }
    public StoryModel(int profile) {
        this.profile = profile;
    }

    public int getProfile() {
        return profile;
    }

    public String getFullname() {
        return fullname;
    }
}
