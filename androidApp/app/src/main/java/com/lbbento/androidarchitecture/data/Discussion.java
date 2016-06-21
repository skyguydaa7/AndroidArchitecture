package com.lbbento.androidarchitecture.data;

import android.database.Cursor;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.lbbento.androidarchitecture.data.source.local.DiscussionsPersistenceContract;

import java.util.UUID;

/**
 * Created by lbbento on 20/06/2016.
 */

public final class Discussion {

    private final String id;
    private final String title;
    private final String description;
    private final String imageUrl;


    /**
     * Use this constructor to create a new Discussion.
     *
     * @param title
     * @param description
     */
    public Discussion(String title, @Nullable String description, @Nullable String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    /**
     * Use this constructor to specify a discussion
     *
     * @param title
     * @param description
     * @param id
     * @param imageUrl
     */
    public Discussion(@Nullable String title, @Nullable String description, String id, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    /**
     * Convert the cursor to a object
     *
     * @param cursor
     * @return Discussion
     */
    public static Discussion fromCursor(Cursor cursor) {
        try
        {
            String entryId = cursor.getString(cursor.getColumnIndexOrThrow(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_ENTRY_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_TITLE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_DESCRIPTION));
            String imageUrl = cursor.getString(cursor.getColumnIndexOrThrow(DiscussionsPersistenceContract.DiscussionEntry.COLUMN_NAME_IMAGE_URL));
            return new Discussion(title, description, entryId, imageUrl);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discussion discussion = (Discussion) o;
        return Objects.equal(id, discussion.id) &&
                Objects.equal(title, discussion.title) &&
                Objects.equal(description, discussion.description) &&
                Objects.equal(imageUrl, discussion.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, description, imageUrl);
    }
}
