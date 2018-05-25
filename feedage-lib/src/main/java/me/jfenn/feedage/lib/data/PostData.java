package me.jfenn.feedage.lib.data;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.jfenn.feedage.lib.utils.SortOfAMarkovChainOrSomething;

public class PostData {

    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private List<String> tags;

    private Date publishDate;
    private Date updateDate;
    private List<AuthorData> authors;

    private FeedData parent;
    private SortOfAMarkovChainOrSomething chain;

    public PostData(FeedData parent) {
        this.parent = parent;
        tags = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        if (title != null)
            this.title = Jsoup.parse(title).text();
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionText() {
        if (parent.getBasicHomepage().equals("theverge.com"))
            System.out.println("Description: " + description + ", Content: " + getContentText());
        return description != null && description.length() > 0 ? description : getContentText();
    }

    void setDescription(String description) {
        if (description != null)
            this.description = Jsoup.parse(description).text();
    }

    public String getContent() {
        return content;
    }

    public String getContentText() {
        return content != null ? Jsoup.parse(content).text() : null;
    }

    void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(String imageUrl) {
        if (imageUrl != null)
            return imageUrl;
        else if (content != null) {
            Matcher matcher = Pattern.compile("(<img)([\"A-Za-z0-9\\s=-_]*)(src=\")([A-Za-z0-9./?-_:]*)(\")").matcher(content);
            if (matcher.find())
                return matcher.group(4);
        }

        return null;
    }

    void setPublishDate(String publishDate) {
    }

    public Date getPublishDate() {
        return publishDate;
    }

    void setUpdateDate(String updateDate) {
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    void addAuthor(AuthorData author) {
        authors.add(author);
    }

    public List<AuthorData> getAuthors() {
        return authors;
    }

    public FeedData getParent() {
        return parent;
    }

    public SortOfAMarkovChainOrSomething getChain() {
        if (chain == null)
            chain = new SortOfAMarkovChainOrSomething(this);

        return chain;
    }
}
