package com.lbbento.androidarchitecture.discussions;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lbbento.androidarchitecture.R;
import com.lbbento.androidarchitecture.custom.RecyclerViewCursorAdapter;
import com.lbbento.androidarchitecture.data.Discussion;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lbbento on 20/06/2016.
 * Discussions recyclerView adapter using cursor
 */

public class DiscussionsAdapter extends RecyclerViewCursorAdapter<DiscussionsAdapter.ViewHolder> {

    private final DiscussionsContract.DiscussionItemListener mItemListener;

    public DiscussionsAdapter(Context context, Cursor cursor, DiscussionsContract.DiscussionItemListener discussionItemListener){
        super(context,cursor);
        mItemListener = discussionItemListener;
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_card_discussion, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        final Discussion mDiscussion = Discussion.fromCursor(cursor);


        viewHolder.title.setText(mDiscussion.getTitle());
        viewHolder.description.setText(mDiscussion.getDescription());
        viewHolder.image.setImageBitmap(null);
        Picasso.with(viewHolder.image.getContext()).cancelRequest(viewHolder.image);
        Picasso.with(viewHolder.image.getContext()).load(mDiscussion.getImageUrl()).into(viewHolder.image);
        viewHolder.itemView.setTag(mDiscussion);

        //OnClick
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onDiscussionClick(mDiscussion);
            }
        });
    }
}
