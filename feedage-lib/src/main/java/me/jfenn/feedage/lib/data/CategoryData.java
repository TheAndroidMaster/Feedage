package me.jfenn.feedage.lib.data;

import java.util.ArrayList;
import java.util.List;

import me.jfenn.feedage.lib.util.SortOfAMarkovChainOrSomething;

public class CategoryData {

    private String title;
    private List<PostData> posts;

    public CategoryData(String title, List<PostData> posts) {
        this.title = title;
        this.posts = posts;
    }

    public String getTitle() {
        return title;
    }

    public List<PostData> getPosts() {
        return posts;
    }

    public static List<CategoryData> getCategories(List<FeedData> feeds) {
        List<CategoryData> categories = new ArrayList<>();
        List<PostData> allPosts = new ArrayList<>();
        for (FeedData feed : feeds)
            allPosts.addAll(feed.getPosts());

        double maxThreshold = 0;
        for (int i = 0; i < allPosts.size(); i++)
            maxThreshold = ((maxThreshold * (i - 1)) + getThreshold(allPosts.get(i).getChain(), allPosts)) / i;

        for (int i = 0; i < allPosts.size(); i++) {
            double threshold = getThreshold(allPosts.get(i).getChain(), allPosts);
            List<PostData> posts = new ArrayList<>();

            if (threshold < maxThreshold) {
                allPosts.add(allPosts.get(i));
                for (int i2 = 0; i2 < allPosts.size(); i2++) {
                    if (i != i2 && allPosts.get(i).getChain().getDifference(allPosts.get(i2).getChain()) < threshold)
                        posts.add(allPosts.get(i2));
                }

                categories.add(new CategoryData(posts.get(i).getTitle(), posts));
            }
        }

        return categories;
    }

    private static double getThreshold(SortOfAMarkovChainOrSomething base, List<PostData> posts) {
        double threshold = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (!posts.get(i).equals(base.getPost()))
                threshold = ((threshold * (i - 1)) + posts.get(i).getChain().getDifference(base)) / i;
        }

        return threshold;
    }

}
