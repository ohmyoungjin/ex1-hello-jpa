package hellojpa.s2;

import javax.persistence.*;

//@Entity
@Table(name = "MEMBER")
public class MemberS2 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamS2 team;

    @Column(name = "USER_NAME")
    private String userName;

    public MemberS2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TeamS2 getTeam() {
        return team;
    }

    public void setTeam(TeamS2 team) {
        this.team = team;
    }
}
