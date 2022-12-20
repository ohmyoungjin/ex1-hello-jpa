package hellojpa.OneToN;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Table(name = "TEAM")
public class TeamOTN1 {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<MemberOTN1> memberList = new ArrayList<>();

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

    public List<MemberOTN1> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberOTN1> memberList) {
        this.memberList = memberList;
    }
}
