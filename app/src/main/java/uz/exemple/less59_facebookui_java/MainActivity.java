package uz.exemple.less59_facebookui_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import uz.exemple.less59_facebookui_java.adapter.FeedAdapter;
import uz.exemple.less59_facebookui_java.model.FeedModel;
import uz.exemple.less59_facebookui_java.model.PostModel;
import uz.exemple.less59_facebookui_java.model.StoryModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        refreshAdapter(getAllFeeds());
    }

    void refreshAdapter(ArrayList<FeedModel> feeds) {
        FeedAdapter adapter = new FeedAdapter(this, feeds);
        recyclerView.setAdapter(adapter);
    }

    ArrayList<FeedModel> getAllFeeds() {
        ArrayList<StoryModel> stories  = new  ArrayList<StoryModel>();
        stories.add(new StoryModel(R.drawable.profile));
        stories.add(new StoryModel(R.drawable.profile1, "JamolXon Kamolxon"));
        stories.add(new StoryModel(R.drawable.profile2, "Abdulfattoh Nematov"));
        stories.add(new StoryModel(R.drawable.profile3, "Muhammad Ikromov"));
        stories.add(new StoryModel(R.drawable.profile1, "JamolXon Kamolxon"));
        stories.add(new StoryModel(R.drawable.profile2, "Abdulfattoh Nematov"));
        stories.add(new StoryModel(R.drawable.profile3, "Muhammad Ikromov"));

        ArrayList<FeedModel> feeds = new ArrayList<FeedModel>();
        //Head
        feeds.add(new FeedModel());
        //Story
        feeds.add(new FeedModel(stories));
        //Post
        feeds.add(new FeedModel(new PostModel(R.drawable.profile3, "Muhammad", getPhotos())));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile3, "Muhammad", R.drawable.photo1)));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile1, "Jamolxon", R.drawable.photo2)));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile1, "Jamolxon")));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile2, "Abdulfattoh", R.drawable.photo3)));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile3, "Muhammad", R.drawable.photo4)));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile1, "Jamolxon", R.drawable.photo5)));
        feeds.add(new FeedModel(new PostModel(R.drawable.profile2, "Abdulfattoh", R.drawable.photo6)));
        return feeds;
    }
    int[] getPhotos(){
        int[] photos = new int[12];
        photos[0] = R.drawable.photo1;
        photos[1] = R.drawable.photo2;
        photos[2] = R.drawable.photo3;
        photos[3] = R.drawable.photo4;
        photos[4] = R.drawable.photo5;
        photos[5] = R.drawable.photo6;
        photos[6] = R.drawable.photo1;
        photos[7] = R.drawable.photo2;
        photos[8] = R.drawable.photo3;
        photos[9] = R.drawable.photo4;
        photos[10] = R.drawable.photo5;
        photos[11] = R.drawable.photo6;


        return photos;
    }
}