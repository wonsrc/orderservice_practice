package com.playdata.orderservice.user.service;

import com.playdata.orderservice.user.dto.UserLoginReqDto;
import com.playdata.orderservice.user.dto.UserSaveReqDto;
import com.playdata.orderservice.user.entity.User;
import com.playdata.orderservice.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public User userCreate(@Valid UserSaveReqDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다!");
        }
        User saved = userRepository.save(dto.toEntity(encoder));
        log.info("saved: {}", saved);
        return saved;
    }

    public User login(UserLoginReqDto dto) {
        // 이메일로 user 조회하기
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new EntityNotFoundException("User not found")
        );

        // 비밀번호 확인하기 (암호화 되어있으니 encoder에게 부탁)
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}

















