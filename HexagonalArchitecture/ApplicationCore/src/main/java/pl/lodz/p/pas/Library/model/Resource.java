package pl.lodz.p.pas.Library.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
public abstract class Resource implements Serializable {
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    private String UUID;
    private String title;
    private Category category;

    public Resource(String title, Category category) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
    }

    public Resource() {
    }

    @Override
    public String toString() {
        return "Resource{" +
                "UUID='" + UUID + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }

    public String getUUID() {
        return UUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
