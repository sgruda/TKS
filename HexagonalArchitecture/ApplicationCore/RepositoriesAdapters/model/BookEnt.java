package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookEnt extends ResourceEnt{
    private Integer releaseYear;
    private String author;

    public BookEnt(String title, CategoryEnt category, Integer releaseYear, String author) {
        super(title, category);
        this.releaseYear = releaseYear;
        this.author = author;
    }
    public BookEnt(String title, String category, String releaseYear, String author) {
        super(title, CategoryEnt.valueOf(category));
        this.releaseYear = Integer.parseInt(releaseYear);
        this.author = author;
    }
}
