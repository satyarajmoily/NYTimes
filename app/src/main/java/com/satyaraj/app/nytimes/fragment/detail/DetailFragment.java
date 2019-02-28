package com.satyaraj.app.nytimes.fragment.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.satyaraj.app.nytimes.MainActivity;
import com.satyaraj.app.nytimes.R;
import com.satyaraj.app.nytimes.base.BaseFragment;
import com.satyaraj.app.nytimes.fragment.section.SectionFragment;
import com.satyaraj.app.nytimes.pojo.Multimedia;
import com.satyaraj.app.nytimes.pojo.Stories;

import java.util.List;

public class DetailFragment extends BaseFragment<MainActivity> {

    public static String STORY_KEY =  "100";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment getDetailNewInstance(Stories story){
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(STORY_KEY, story);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachParent((MainActivity)getActivity());
        ImageView imageView = view.findViewById(R.id.image_view);
        TextView author = view.findViewById(R.id.author);
        TextView publishedDate = view.findViewById(R.id.published_date);
        TextView title = view.findViewById(R.id.title);
        TextView description = view.findViewById(R.id.description);
        CoordinatorLayout root = view.findViewById(R.id.root_layout);

        if (getArguments() != null) {
            Stories stories = (Stories) getArguments().getSerializable(STORY_KEY);
            if (stories != null) {
                author.setText(stories.getAuthor());
                publishedDate.setText(stories.getPublishedDate());
                title.setText(stories.getTitle());

                List<Multimedia> multimedia = stories.getMultimedia();
                String url = null;
                if (multimedia != null && multimedia.size() > 2){
                    url = multimedia.get(3).getUrl();
                }

                description.setText(stories.getAbstact());

                Glide.with(getParentActivity())
                        .load(url)
                        .placeholder(R.drawable.nytimes)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
        }

        root.setOnClickListener(v -> {

        });
    }
}
