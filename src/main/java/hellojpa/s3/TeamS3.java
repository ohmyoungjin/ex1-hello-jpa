package hellojpa.s3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class TeamS3 {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    //매핑된 객체 이름 적어준다. (ManyToOne에 기재된 객체이름 적어주면 된다)
    @OneToMany(mappedBy = "team")
    private List<MemberS3> memberList = new ArrayList<>();

    //연관 관계 편의 메서드
    public void addMember(MemberS3 member) {
        member.setTeam(this);
        memberList.add(member);
    }

    public TeamS3() {

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

    public List<MemberS3> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberS3> memberList) {
        this.memberList = memberList;
    }
}
