package me.jfenn.feedage.utils;

import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.view.View;

public class UrlClickListener implements View.OnClickListener {

    private Uri uri;

    public UrlClickListener(String url) {
        uri = Uri.parse(url);
    }

    @Override
    public void onClick(View v) {
        new CustomTabsIntent.Builder()
                .build()
                .launchUrl(v.getContext(), uri);
    }
}
