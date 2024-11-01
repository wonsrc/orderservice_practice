package com.playdata.orderservice.user.controller;

import com.playdata.orderservice.common.auth.JwtTokenProvider;
import com.playdata.orderservice.common.dto.CommonResDto;
import com.playdata.orderservice.user.dto.UserLoginReqDto;
import com.playdata.orderservice.user.dto.UserSaveReqDto;
import com.playdata.orderservice.user.entity.User;
import com.playdata.orderservice.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/create")
    public ResponseEntity<?> userCreate(@Valid @RequestBody UserSaveReqDto dto) {
        log.info("/user/create: POST, dto: {}", dto);
        User user = userService.userCreate(dto);

        CommonResDto resDto
                = new CommonResDto(HttpStatus.CREATED, "member create 성공", user.getId());
        return new ResponseEntity<>(resDto, HttpStatus.CREATED);
    }

    @PostMapping("/doLogin")
    public ResponseEntity<?> doLogin(@RequestBody UserLoginReqDto dto) {
        User user = userService.login(dto);

        // 회원 정보가 일치한다면, JWT를 클라이언트에게 발급해 주어야 한다. -> 로그인 유지를 위해!
        String token
                = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString());
        log.info("token: {}", token);

        return null;
    }



}



















