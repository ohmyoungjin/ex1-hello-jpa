package hellojpa.resulttype;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name ="USERNAME")
    private String username;

    //기간 Period
    @Embedded
    private Period period;

    //집 주소
    @Embedded
    private Address homeAddress;

    //회사 주소
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
            column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name="street",
                    column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column = @Column(name = "WORK_ZIPCODE")),
    })
    private Address workAddress;

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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}