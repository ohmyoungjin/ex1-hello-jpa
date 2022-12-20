package hellojpa.v2;

import javax.persistence.*;

//@Entity
@Table(name = "MEMBER")
public class MemberV2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    //객체지향적으로 객체 자체를 매핑해준다.
    //Member N : Team 1 의 관계이기 때문에 이 때는 @ManyToOne annotation을 사용한다.
    @ManyToOne
    //관계에 대한 join column (foreign key)을 적어준다.
    @JoinColumn(name = "TEAM_ID")
    private TeamV2 teamV2;

    public MemberV2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TeamV2 getTeam() {
        return teamV2;
    }

    public void setTeam(TeamV2 teamV2) {
        this.teamV2 = teamV2;
    }
}
