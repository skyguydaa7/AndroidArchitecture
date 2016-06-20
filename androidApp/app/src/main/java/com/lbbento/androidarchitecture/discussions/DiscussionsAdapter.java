package com.lbbento.androidarchitecture.discussions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbbento.androidarchitecture.R;
import com.lbbento.androidarchitecture.data.Discussion;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lbbento on 20/06/2016.
 * Discussions recyclerView adapter
 */


public class DiscussionsAdapter extends RecyclerView.Adapter<DiscussionsAdapter.ViewHolder> {

    private List<Discussion> items;
    private int itemLayout;

    public DiscussionsAdapter(List<Discussion> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Discussion item = items.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.image.setImageBitmap(null);
        Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
        Picasso.with(holder.image.getContext()).load(item.getImageUrl()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_discussion_imageView);
            title = (TextView) itemView.findViewById(R.id.card_discussion_title);
            description = (TextView) itemView.findViewById(R.id.card_discussion_description);
        }
    }
}