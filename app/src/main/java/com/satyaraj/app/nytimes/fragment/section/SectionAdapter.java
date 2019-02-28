package com.satyaraj.app.nytimes.fragment.section;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.satyaraj.app.nytimes.R;
import com.satyaraj.app.nytimes.pojo.Multimedia;
import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Stories> mStoriesList = new ArrayList<>(0);

    private ClickListener mClickListener;
    private SectionFragment mSectionFragment;
    private Context mContext;

    SectionAdapter(SectionFragment sectionFragment) {
        this.mClickListener = sectionFragment;
        this.mContext = sectionFragment.getContext();
    }

    void updateList(List<Stories> storiesList) {
        mStoriesList.clear();
        mStoriesList.addAll(storiesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;
        view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        viewHolder = new ViewHeader(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Stories story = mStoriesList.get(position);
        ((ViewHeader) holder).onBind(story);
    }

    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }

    private class ViewHeader extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;
        TextView textViewAuthor;
        ImageView imageView;
        LinearLayout linearLayout;
        Stories stories;

        ViewHeader(View view) {
            super(view);
            textViewTitle = view.findViewById(R.id.article_title);
            textViewAuthor = view.findViewById(R.id.article_author);
            imageView = view.findViewById(R.id.thumbnail);
            linearLayout = view.findViewById(R.id.linear);
            view.setOnClickListener(this);
        }

        void onBind(Stories story) {
            this.stories = story;
            textViewTitle.setText(stories.getTitle());
            textViewAuthor.setText(stories.getAuthor());

            List<Multimedia> multimedia = story.getMultimedia();
            String url = null;
            if (multimedia != null && multimedia.size() > 2){
                url = multimedia.get(3).getUrl();
            }

            Glide.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.nytimes)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                            Palette palette = new Palette.Builder(bitmap).generate();
                            int defaultColor = 0xFF333333;
                            int color = palette.getDarkMutedColor(defaultColor);
                            linearLayout.setBackgroundColor(color);

                            return false;
                        }

                    })
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClicked(stories);
        }
    }
}
