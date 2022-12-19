package hellojpa.V3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class TeamV3 {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //나의 반대편 사이드와 이어지는 키를 적어준다
    //지금 이 객체 class (team)의 입장으로 봤을 때 Member 와 1:N으로 이어지기 때문에
    //@OneToMany를 적어준다.
    //양방향 같은 경우 해당하는 key가 변경될 때
    //둘 다 변경하는 걸 방지하기 위해 mappedBy를 기재해준다.
    //mappedBy에 대한 반대편이 연관 관계 주인이 된다.
    //외래 키가 있는 곳을 주인으로 정하는게 좋다
    //여기서 둘을 이어주는 Column 및 필드는 TEAM_ID
    //해당 Column 및 필드는 TEAM 의 PK MEMBER 의 FK다.
    //외래 키를 가지고 있는 MEMBER 에서 연관관계에 값을 변경하는게 타당하다.
    @OneToMany(mappedBy = "teamV3")
    private List<MemberV3> members = new ArrayList<>();

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

    public List<MemberV3> getMembers() {
        return members;
    }

    public void setMembers(List<MemberV3> members) {
        this.members = members;
    }
}
