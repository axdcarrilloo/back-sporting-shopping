package com.bt.mappers;

import com.bt.domain.entities.UserEntity;
import com.bt.dtos.UserRegisterDto;
import com.bt.dtos.UserViewDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<UserViewDto> convertToUserViewListFromUserEntityList(final List<UserEntity> usersEntity);
    UserViewDto convertToUserViewFromUserEntity(final UserEntity userEntity);
    UserEntity convertToUserRegisterFromUserEntity(final UserRegisterDto userRegisterDto);
}
