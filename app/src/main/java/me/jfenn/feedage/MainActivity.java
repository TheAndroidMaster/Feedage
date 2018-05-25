package me.jfenn.feedage;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.jfenn.feedage.adapters.ItemAdapter;
import me.jfenn.feedage.data.CategoryItemData;
import me.jfenn.feedage.data.ItemData;
import me.jfenn.feedage.lib.Feedage;
import me.jfenn.feedage.lib.data.AtomFeedData;
import me.jfenn.feedage.lib.data.CategoryData;
import me.jfenn.feedage.lib.data.FeedData;

public class MainActivity extends AppCompatActivity implements Feedage.OnCategoriesUpdatedListener {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Feedage feedage = new Feedage(
                new AtomFeedData("https://www.androidpolice.com/feed/?paged=%s", 1, Color.parseColor("#af1c1c"), Color.WHITE),
                new AtomFeedData("https://www.androidauthority.com/feed/?paged=%s", 1, Color.parseColor("#01e0bd"), Color.BLACK),
                new AtomFeedData("https://www.theverge.com/rss/index.xml", Color.parseColor("#e5127d"), Color.WHITE),
                new AtomFeedData("https://techaeris.com/feed/?paged=%s", 1, Color.parseColor("#212121"), Color.WHITE),
                new AtomFeedData("https://www.engadget.com/rss.xml", Color.WHITE, Color.BLACK),
                new AtomFeedData("http://rss.nytimes.com/services/xml/rss/nyt/Technology.xml", Color.WHITE, Color.BLACK),
                new AtomFeedData("https://www.xda-developers.com/feed/?paged=%s", 1, Color.parseColor("#f59714"), Color.BLACK),
                new AtomFeedData("https://www.wired.com/feed", Color.parseColor("#BDBDBD"), Color.BLACK),
                new AtomFeedData("https://techbeacon.com/rss.xml", Color.parseColor("#0096D6"), Color.WHITE)
        );

        feedage.getNext(this);

        //findViewById(R.id.loadMore).setOnClickListener(v -> feedage.getNext(this));
    }

    @Override
    public void onFeedsUpdated(final List<FeedData> feeds) {
        /*new Handler(Looper.getMainLooper()).post(() -> {
            List<ItemData> items = new ArrayList<>();
            for (FeedData feed : feeds)
                items.add(new FeedItemData(feed));

            if (recycler.getAdapter() == null)
                recycler.setAdapter(new ItemAdapter(items));
            else recycler.swapAdapter(new ItemAdapter(items), true);
        });*/
    }

    @Override
    public void onCategoriesUpdated(final List<CategoryData> categories) {
        Log.d("CategoriesLoaded", categories.size() + "");

        new Handler(Looper.getMainLooper()).post(() -> {
            List<ItemData> items = new ArrayList<>();
            for (CategoryData category : categories)
                items.add(new CategoryItemData(category));

            if (recycler.getAdapter() == null)
                recycler.setAdapter(new ItemAdapter(items));
            else recycler.swapAdapter(new ItemAdapter(items), false);
        });
    }
}
