package com.inai.oorpo.library.Library.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, max = 256)
    private String title;


    @Size(min = 3, max = 100)
    private String author;

    private String writtenDate;

    public Book() {
    }

    public Book(@NotNull @Size(min = 3, max = 256) String title, @Size(min = 3, max = 100) String author, String writtenDate) {
        this.title = title;
        this.author = author;
        this.writtenDate = writtenDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", writtenDate='" + writtenDate + '\'' +
                '}';
    }
}
