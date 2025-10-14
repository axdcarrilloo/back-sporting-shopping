package com.bt.services;

import com.bt.dtos.ResponseMainDto;
import com.bt.dtos.UserRegisterDto;
import com.bt.dtos.UserViewDto;

public interface UserService {
    ResponseMainDto deleteById(final Long id);
    ResponseMainDto update(final UserViewDto userViewDto);
    ResponseMainDto getAll();
    ResponseMainDto register(final UserRegisterDto userRegisterDto);
}
