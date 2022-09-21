package uz.exemple.less59_facebookui_java.model;

import java.util.ArrayList;

public class FeedModel {
   private Boolean isHeader = false;
    PostModel post = null;
    ArrayList<StoryModel> stories = new ArrayList<>();

   public FeedModel() {
        this.isHeader = true;
    }

   public FeedModel(PostModel post) {
        this.post = post;
        this.isHeader = false;
    }

   public FeedModel(ArrayList<StoryModel> stories) {
        this.stories = stories;
        this.isHeader = false;
    }

    public Boolean getHeader() {
        return isHeader;
    }

    public PostModel getPost() {
        return post;
    }

    public ArrayList<StoryModel> getStories() {
        return stories;
    }
}
