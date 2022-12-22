package hellojpa.v3;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
public class MemberV3 extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    //객체지향적으로 객체 자체를 매핑해준다.
    //Member N : Team 1 의 관계이기 때문에 이 때는 @ManyToOne annotation을 사용한다.
    //지연 로딩
//    @ManyToOne(fetch = FetchType.LAZY)
    //즉시로딩
    @ManyToOne(fetch = FetchType.EAGER)
    //관계에 대한 join column (foreign key)을 적어준다.
    @JoinColumn(name = "TEAM_ID")
    private TeamV3 teamV3;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

    public MemberV3() {
    }

    public MemberV3(String username) {
        this.username = username;
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

    public TeamV3 getTeam() {
        return teamV3;
    }

    public void setTeamV3(TeamV3 teamV3) {
        this.teamV3 = teamV3;
    }

    public void changeTeam(TeamV3 teamV3) {
        this.teamV3 = teamV3;
        //내 객체를 담아준다.
        teamV3.getMembers().add(this);
    }

//    public Locker getLocker() {
//        return locker;
//    }
//
//    public void changeLocker(Locker locker) {
//        this.locker = locker;
//        locker.setMember(this);
//    }
//
//    public void setLocker(Locker locker) {
//        this.locker = locker;
//    }
}
