package uz.exemple.less59_facebookui_java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import uz.exemple.less59_facebookui_java.R;
import uz.exemple.less59_facebookui_java.model.StoryModel;

public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<StoryModel> items;
    int ITEM_CREATE = 0;
    int ITEM_STORY = 1;

    public StoryAdapter(Context context, ArrayList<StoryModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        StoryModel item = items.get(position);
        if (item.getFullname() == null){
            return ITEM_CREATE;
        }
        return ITEM_STORY;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_CREATE){
            View view =
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_create, parent, false);
            return new CreateViewHolder(view);
        }
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StoryModel item = items.get(position);
        if (holder instanceof ViewHolder){
            ShapeableImageView iv_background = ((ViewHolder) holder).iv_background;
            ShapeableImageView iv_profile = ((ViewHolder) holder).iv_profile;
            TextView tv_fullname = ((ViewHolder) holder).tv_fullname;

            iv_background.setImageResource(item.getProfile());
            iv_profile.setImageResource(item.getProfile());
            tv_fullname.setText(item.getFullname());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView iv_background;
        ShapeableImageView iv_profile;
        TextView tv_fullname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_background = itemView.findViewById(R.id.iv_background);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_fullname = itemView.findViewById(R.id.tv_fullname);
        }
    }
    public class CreateViewHolder extends RecyclerView.ViewHolder{

        public CreateViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
