package hellojpa.s2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Table(name = "TEAM")
public class TeamS2 {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    public TeamS2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
