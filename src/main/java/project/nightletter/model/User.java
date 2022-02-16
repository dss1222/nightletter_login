package project.nightletter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {


    //modoel 유저 테이블

    @GeneratedValue(strategy = GenerationType.IDENTITY)//id 값 자동 생성
    @Id
    private Long id; //id 값

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, unique = true)
    private String username; //유저 아이디 칼럼

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String password; //유저 비밀번호 칼럼

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, unique = true)
    private String nickname; //유저 닉네임 칼럼



    public User(String username, String password, String nickname) {

        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

}
