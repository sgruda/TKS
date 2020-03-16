package pl.lodz.p.pas.Library.model;

import java.io.Serializable;

public class Book extends Resource  implements Serializable {
    private Integer releaseYear;
    private String author;

    public Book(String title, Category category, Integer releaseYear, String author) {
        super(title, category);
        this.releaseYear = releaseYear;
        this.author = author;
    }
    public Book(String title, String category, String releaseYear, String author) {
        super(title, Category.valueOf(category));
        this.releaseYear = Integer.parseInt(releaseYear);
        this.author = author;
    }
    public Book() {
        super();
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
