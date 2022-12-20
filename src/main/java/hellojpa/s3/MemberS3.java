package hellojpa.s3;

import javax.persistence.*;

//@Entity
@Table(name = "MEMBER")
public class MemberS3 {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USER_NAME")
    private String userName;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private TeamS3 team;

    public MemberS3() {

    }

    //연관 관계 편의 메서드
    public void changeMember(TeamS3 team) {
        //내가 필요한 객체 값을 저장하며
        //내가 필요로 한 객체에도 값을 같이 세팅해준다.
        team.getMemberList().add(this);
        this.team = team;
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

    public TeamS3 getTeam() {
        return team;
    }

    public void setTeam(TeamS3 team) {
        this.team = team;
    }
}
