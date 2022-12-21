package hellojpa.v3;

import javax.persistence.*;

//@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "LOCKER_NAME")
    private String lockerName;

//    @OneToOne(mappedBy = "locker")
//    private MemberV3 member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLockerName() {
        return lockerName;
    }

    public void setLockerName(String lockerName) {
        this.lockerName = lockerName;
    }

//    public MemberV3 getMember() {
//        return member;
//    }
//
//    public void setMember(MemberV3 member) {
//        this.member = member;
//    }
}
