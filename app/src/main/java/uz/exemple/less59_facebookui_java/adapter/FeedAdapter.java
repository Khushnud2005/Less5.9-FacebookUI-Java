package uz.exemple.less59_facebookui_java.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import uz.exemple.less59_facebookui_java.R;
import uz.exemple.less59_facebookui_java.model.FeedModel;
import uz.exemple.less59_facebookui_java.model.StoryModel;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<FeedModel> items;

    private int TYPE_ITEM_HEAD = 0;
    private int TYPE_ITEM_STORY = 1;
    private int TYPE_ITEM_POST = 2;
    private int TYPE_ITEM_UPDATED = 3;
    private int TYPE_ITEM_PHOTOS = 4;
    public FeedAdapter(Context context, ArrayList<FeedModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        FeedModel feed = items.get(position);
        if (feed.getHeader()){
            return TYPE_ITEM_HEAD;
        }else if(feed.getStories().size() > 0){
            return TYPE_ITEM_STORY;
        }else if (feed.getPost().isUpdated()){
            return TYPE_ITEM_UPDATED;
        }else if (feed.getPost().getPhotos() != null){
            return TYPE_ITEM_PHOTOS;
        }
        return TYPE_ITEM_POST;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_head, parent, false);
            return new HeadViewHolder(context, view);
        }else if (viewType == TYPE_ITEM_STORY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_story, parent, false);
            return new  StoryViewHolder(context, view);
        }else if (viewType == TYPE_ITEM_UPDATED) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_updated_post, parent, false);
            return new  UpdatedViewHolder(view);
        }else if (viewType == TYPE_ITEM_PHOTOS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_5x_photo, parent, false);
            return new  PhotosViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_post, parent, false);
        return new  PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedModel feed = items.get(position);

        if (holder instanceof HeadViewHolder) {

        }

        if (holder instanceof StoryViewHolder) {
            RecyclerView recyclerView = ((StoryViewHolder) holder).recyclerView;
            refreshAdapter(feed.getStories(), recyclerView);
        }

        if (holder instanceof PostViewHolder) {
            ShapeableImageView iv_profile = ((PostViewHolder) holder).iv_profile;
            ImageView iv_photo = ((PostViewHolder) holder).iv_photo;
            TextView tv_fullname = ((PostViewHolder) holder).tv_fullname;
            iv_profile.setImageResource(feed.getPost().getProfile());
            iv_photo.setImageResource(feed.getPost().getPhoto());
            tv_fullname.setText(feed.getPost().getFullname());
        }
        if (holder instanceof PhotosViewHolder) {
            ShapeableImageView iv_profile = ((PhotosViewHolder) holder).iv_profile;
            ImageView iv_photo1 = ((PhotosViewHolder) holder).iv_photo1;
            ImageView iv_photo2 = ((PhotosViewHolder) holder).iv_photo2;
            ImageView iv_photo3 = ((PhotosViewHolder) holder).iv_photo3;
            ImageView iv_photo4 = ((PhotosViewHolder) holder).iv_photo4;
            ImageView iv_photo5 = ((PhotosViewHolder) holder).iv_photo5;
            TextView tv_fullname = ((PhotosViewHolder) holder).tv_fullname;
            TextView tv_count_photo = ((PhotosViewHolder) holder).tv_count_photo;
            LinearLayout ll_bottom = ((PhotosViewHolder) holder).ll_bottom;
            LinearLayout ll_top = ((PhotosViewHolder) holder).ll_top;
            Context context = ((PhotosViewHolder) holder).itemView.getContext();
            iv_profile.setImageResource(feed.getPost().getProfile());
            int[] photos = feed.getPost().getPhotos();

            iv_photo1.setImageResource(photos[0]);
            iv_photo2.setImageResource(photos[1]);
            iv_photo3.setImageResource(photos[2]);
            iv_photo4.setImageResource(photos[3]);
            iv_photo5.setImageResource(photos[4]);

            tv_fullname.setText(feed.getPost().getFullname());
            int count_photos = feed.getPost().getPhotos().length -5;
            tv_count_photo.setText("+"+count_photos);

            setLinearHeight(context,ll_bottom,3);
            setLinearHeight(context,ll_top,2);
        }
        if (holder instanceof UpdatedViewHolder) {
            ShapeableImageView iv_profile = ((UpdatedViewHolder) holder).iv_profile;
            ShapeableImageView iv_updated_profile = ((UpdatedViewHolder) holder).iv_updated_profile;
            TextView tv_fullname = ((UpdatedViewHolder) holder).tv_fullname;

            iv_profile.setImageResource(feed.getPost().getProfile());
            iv_updated_profile.setImageResource(feed.getPost().getProfile());

            String upd_fullNaim = feed.getPost().getFullname() + "  updated his profile picture.";

            Spannable spannable = new SpannableString(upd_fullNaim);
            spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#90000000")), feed.getPost().getFullname().length(), upd_fullNaim.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            tv_fullname.setText(spannable, TextView.BufferType.SPANNABLE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void refreshAdapter(ArrayList<StoryModel> stories,RecyclerView recyclerView) {
        StoryAdapter adapter = new  StoryAdapter(context, stories);
        recyclerView.setAdapter(adapter);
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(@NonNull Context context, View itemView) {
            super(itemView);
        }
    }

    class StoryViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public StoryViewHolder(@NonNull Context context, View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            LinearLayoutManager manager = new  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(manager);
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_profile;
        ImageView iv_photo;
        TextView tv_fullname;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }
    class UpdatedViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_profile;
        ShapeableImageView iv_updated_profile;
        TextView tv_fullname;

        public UpdatedViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_updated_profile = itemView.findViewById(R.id.iv_updated_profile);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }
    class PhotosViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_profile;
        ImageView iv_photo1;
        ImageView iv_photo2;
        ImageView iv_photo3;
        ImageView iv_photo4;
        ImageView iv_photo5;
        TextView tv_fullname;
        TextView tv_count_photo;
        LinearLayout ll_bottom;
        LinearLayout ll_top;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            iv_photo1 = itemView.findViewById(R.id.iv_photo1);
            iv_photo2 = itemView.findViewById(R.id.iv_photo2);
            iv_photo3 = itemView.findViewById(R.id.iv_photo3);
            iv_photo4 = itemView.findViewById(R.id.iv_photo4);
            iv_photo5 = itemView.findViewById(R.id.iv_photo5);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
            tv_count_photo = itemView.findViewById(R.id.tv_count_photo);
            ll_bottom = itemView.findViewById(R.id.ll_bottom);
            ll_top = itemView.findViewById(R.id.ll_top);
        }
    }
    void setLinearHeight(Context context,LinearLayout layout,int parts ){
        // Get screen width programmatically
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int pxWidth = displayMetrics.widthPixels;
        float dpWidth = pxWidth / displayMetrics.density;
        int pxHeight = displayMetrics.heightPixels;
        float dpHeight = pxHeight / displayMetrics.density;

        // Set layout width programmatically
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = (pxWidth-(5*(parts-1))) / parts;
        layout.setLayoutParams(params);
    }
}
