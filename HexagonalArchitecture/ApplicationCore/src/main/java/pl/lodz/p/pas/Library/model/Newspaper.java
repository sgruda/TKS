package pl.lodz.p.pas.Library.model;

import java.io.Serializable;

public class Newspaper extends Resource  implements Serializable {
    private Integer issueNumber;

    public Newspaper(String title, Category category, Integer issueNumber) {
        super(title,category);
        this.issueNumber = issueNumber;
    }
    public Newspaper(String title, String category, String issueNumber) {
        super(title,Category.valueOf(category));
        this.issueNumber = Integer.parseInt(issueNumber);
    }

    public Newspaper() {
        super();
    }

    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Integer getIssueNumber() {
        return issueNumber;
    }
}
