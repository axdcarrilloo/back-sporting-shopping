package com.bt.controllers;

import com.bt.dtos.LoginRequestDto;
import com.bt.dtos.ResponseMainDto;
import com.bt.dtos.UserRegisterDto;
import com.bt.dtos.UserViewDto;
import com.bt.services.UserService;
import com.bt.utils.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = Route.BASE_PATH)
public class UserController {
    private final UserService userSvc;

    @PostMapping(value = Route.UPDATE)
    private ResponseEntity<ResponseMainDto> update(@RequestBody UserViewDto userViewDto) {
        return new ResponseEntity<>(userSvc.update(userViewDto), HttpStatus.OK);
    }

    @PostMapping(value = Route.LOGIN)
    private ResponseEntity<ResponseMainDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(userSvc.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping(value = Route.REGISTER)
    private ResponseEntity<ResponseMainDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        return new ResponseEntity<>(userSvc.register(userRegisterDto), HttpStatus.CREATED);
    }

    @GetMapping(value = Route.GET_ALL)
    public ResponseEntity<ResponseMainDto> getAll() {
        return new ResponseEntity<>(userSvc.getAll(), HttpStatus.OK);
    }
}
