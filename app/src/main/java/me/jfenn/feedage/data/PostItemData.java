package me.jfenn.feedage.data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.jfenn.feedage.R;
import me.jfenn.feedage.lib.data.PostData;
import me.jfenn.feedage.utils.StringUtils;

public class PostItemData extends ItemData<PostItemData.ViewHolder> {

    private PostData post;

    public PostItemData(PostData post) {
        super(R.layout.item_post);
        this.post = post;
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void bind(Context context, ViewHolder viewHolder) {
        if (post.getTitle() != null)
            viewHolder.title.setText(StringUtils.toPlainText(post.getTitle()));
        if (post.getDescription() != null)
            viewHolder.subtitle.setText(StringUtils.toPlainText(post.getDescription()));
        if (post.getImageUrl() != null) {
            viewHolder.image.setVisibility(View.VISIBLE);
            Glide.with(context).load(post.getImageUrl()).into(viewHolder.image);
        } else viewHolder.image.setVisibility(View.GONE);
        viewHolder.website.setText(post.getParent().getBasicHomepage());
    }

    public static class ViewHolder extends ItemData.ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView subtitle;
        private TextView website;

        public ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            title = v.findViewById(R.id.title);
            subtitle = v.findViewById(R.id.subtitle);
            website = v.findViewById(R.id.website);
        }
    }

}
