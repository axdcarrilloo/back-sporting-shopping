package com.bt.mappers.impls;

import com.bt.domain.entities.UserEntity;
import com.bt.dtos.UserViewDto;
import com.bt.mappers.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserViewDto convertToViewFromEntity(UserEntity userEntity) {
        return UserViewDto.builder().id(userEntity.getId()).nombres(userEntity.getNombres())
                .apellidos(userEntity.getApellidos()).direccionEnvios(userEntity.getDireccionEnvios())
                .email(userEntity.getEmail()).fechaNacimiento(userEntity.getFechaNacimiento())
                .password(userEntity.getPassword()).build()
        ;
    }

    @Override
    public UserEntity convertToEntityFromView(UserViewDto userViewDto) {
        return UserEntity.builder().id(userViewDto.getId()).nombres(userViewDto.getNombres())
                .apellidos(userViewDto.getApellidos()).direccionEnvios(userViewDto.getDireccionEnvios())
                .email(userViewDto.getEmail()).fechaNacimiento(userViewDto.getFechaNacimiento())
                .password(userViewDto.getPassword()).build()
        ;
    }
}
