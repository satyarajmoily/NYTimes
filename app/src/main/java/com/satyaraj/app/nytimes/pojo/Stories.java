package com.satyaraj.app.nytimes.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "stories")
public class Stories implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    public String title;

    @ColumnInfo(name = "author")
    @SerializedName("byline")
    public String author;

    @Ignore
    @SerializedName("multimedia")
    public List<Multimedia> multimedia;

    @ColumnInfo(name = "abstract")
    @SerializedName("abstract")
    public String abstact;

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    public byte[] image;

    @ColumnInfo(name = "published_date")
    @SerializedName("published_date")
    public String publishedDate;

    @ColumnInfo(name = "url")
    @SerializedName("url")
    public String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getAbstact() {
        return abstact;
    }

    public void setAbstact(String abstact) {
        this.abstact = abstact;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


