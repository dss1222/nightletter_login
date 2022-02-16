package project.nightletter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.nightletter.dto.SignupRequestDto;
import project.nightletter.model.User;
import project.nightletter.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //User 회원가입
    public void registerUser(SignupRequestDto requestDto) {

        //입력받은 내용을 유효성 검사
        ValidateChecker.registerValidCheck(requestDto);

        //username이 만약 DB에 해당 아이디가 있다면 throw new IllegalArgumentException처리
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }


        //nickname이 만약 DB에 해당 아이디가 있다면 throw new IllegalArgumentException처리
        String nickname = requestDto.getNickname();
        Optional<User> found1 = userRepository.findByNickname(nickname);
        if(found1.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임이 존재합니다.");
        }

        //password을 인코딩 처리로 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        //해당 내용을 저장한다.
        User user = new User(username, password, nickname);
        userRepository.save(user);
    }
}

