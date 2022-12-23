package hellojpa.v3;

import javax.persistence.*;

//@Entity
public class Child {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    //연관 관계 편의 메서드
    public void changeChild(Parent parent) {
        this.parent = parent;
        parent.childList.add(this);
    }


    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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
