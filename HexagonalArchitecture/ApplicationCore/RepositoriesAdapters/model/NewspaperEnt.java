package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class NewspaperEnt extends ResourceEnt {
    private Integer issueNumber;

    public NewspaperEnt(String title, CategoryEnt category, Integer issueNumber) {
        super(title,category);
        this.issueNumber = issueNumber;
    }
    public NewspaperEnt(String title, String category, String issueNumber) {
        super(title, CategoryEnt.valueOf(category));
        this.issueNumber = Integer.parseInt(issueNumber);
    }

}
