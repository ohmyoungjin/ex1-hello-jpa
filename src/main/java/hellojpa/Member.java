package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
//시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용됨
//데이터베이스 시퀀스 값이 하나씩 증가하도록 설정되어 있으면
// 이 값 을 반드시 [1]로 설정해야 한다
@SequenceGenerator(name = "member_seq_generator",
        sequenceName = "member_seq",
        initialValue = 1, allocationSize = 50)
public class Member {

    public Member() {
    }

    @Id
    //GenerationType
    //AUTO = 방언에 따로 자동 지정
    //IDENTITY = 기본 키 생성을 데이터베이스에 위임, INSERT SQL 실행 후 ID 값을 알 수 있다.
    //IDENTITY 같은 경우 DML 쿼리가 바로 실행된다.
    //SEQUENCE = 데이터베이스 시퀀스 오브젝트 사용, ORACLE 같은 경우 @SequenceGenerator 필요
    //SEQUENCE 같은 경우 DML 실행 되기 전에 SEQUENCE 값을 먼저 가져와서 넣어준다.
    //TABLE = 키 생성 전용 테이블을 하나 만들어서 시퀀스를 생성한다 단점 : 성능
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;

    @Column(name = "name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
