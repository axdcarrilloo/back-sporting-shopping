package com.bt.mappers;

import com.bt.domain.entities.UserEntity;
import com.bt.dtos.UserViewDto;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserViewDto convertToViewFromEntity(final UserEntity userEntity);
    UserEntity convertToEntityFromView(final UserViewDto userViewDto);
}
