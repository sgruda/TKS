package pl.lodz.p.tks.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class ResourceEnt {
    public void setUUID(String UUID) {
        this.UUID = UUID;
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

    public CategoryEnt getCategory() {
        return category;
    }

    public void setCategory(CategoryEnt category) {
        this.category = category;
    }

    private String UUID;
    private String title;
    private CategoryEnt category;

    public ResourceEnt(String title, CategoryEnt category) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.category = category;
    }
    @Override
    public String toString() {
        return "Resource{" +
                "UUID='" + UUID + '\'' +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }

}
