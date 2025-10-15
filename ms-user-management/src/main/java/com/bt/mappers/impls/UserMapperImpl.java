package com.bt.mappers.impls;

import com.bt.domain.entities.UserEntity;
import com.bt.dtos.UserRegisterDto;
import com.bt.dtos.UserViewDto;
import com.bt.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity convertToEntityFromView(UserViewDto userViewDto) {
        return UserEntity.builder().id(userViewDto.getId()).nombres(userViewDto.getNombres()).apellidos(userViewDto.getApellidos())
                .direccionEnvios(userViewDto.getDireccionEnvios()).email(userViewDto.getEmail())
                .fechaNacimiento(userViewDto.getFechaNacimiento()).password(userViewDto.getPassword()).build()
        ;
    }

    @Override
    public List<UserViewDto> convertToUserViewListFromUserEntityList(List<UserEntity> usersEntity) {
        List<UserViewDto> usersView = new ArrayList<>();
        usersEntity.forEach(userEntity -> usersView.add(convertToUserViewFromUserEntity(userEntity)));
        return usersView;
    }

    @Override
    public UserViewDto convertToUserViewFromUserEntity(final UserEntity userEntity) {
        return UserViewDto.builder().id(userEntity.getId()).nombres(userEntity.getNombres())
                .apellidos(userEntity.getApellidos()).direccionEnvios(userEntity.getDireccionEnvios())
                .email(userEntity.getEmail()).fechaNacimiento(userEntity.getFechaNacimiento())
                .password(userEntity.getPassword()).build()
        ;
    }

    @Override
    public UserEntity convertToUserRegisterFromUserEntity(final UserRegisterDto userRegisterDto) {
        return UserEntity.builder().nombres(userRegisterDto.getNombres()).apellidos(userRegisterDto.getApellidos())
                .direccionEnvios(userRegisterDto.getDireccionEnvios()).email(userRegisterDto.getEmail())
                .fechaNacimiento(userRegisterDto.getFechaNacimiento()).password(userRegisterDto.getPassword()).build()
        ;
    }
}
