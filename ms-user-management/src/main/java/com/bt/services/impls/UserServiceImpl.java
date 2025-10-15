package com.bt.services.impls;

import com.bt.domain.entities.UserEntity;
import com.bt.domain.repositories.UserRepository;
import com.bt.dtos.*;
import com.bt.exceptions.AlreadyExistsException;
import com.bt.exceptions.ErrorCredentialsException;
import com.bt.exceptions.NotFoundException;
import com.bt.mappers.UserMapper;
import com.bt.services.UserService;
import com.bt.utils.Constans;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomLogger customLogger;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private BuildLog buildCustomLogger(final String nameMethod, final String message) {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod(nameMethod).message(message).build()
        ;
    }

    @Override
    public ResponseMainDto login(LoginRequestDto loginRequestDto) {
        UserEntity userEntity =
                userRepository.findByEmailAndPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        ;
        if(Objects.isNull(userEntity)) {
            ErrorCredentialsException credentialsException =
                    new ErrorCredentialsException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_LOGIN_FAILED)
                            .response("Error de credenciales al iniciar").build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE,
                    buildCustomLogger("login()", Constans.MSG_RESPONSE_LOGIN_FAILED
                            +": Error de credenciales al iniciar")
                    )
            ;
            throw credentialsException;
        } else {
            return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_LOGIN_SUCCESSFUL).response(userEntity).build();
        }
    }

    @Override
    public ResponseMainDto deleteById(Long id) {
        if(Boolean.FALSE.equals(userRepository.existsById(id))) {
            NotFoundException notFoundException =
                    new NotFoundException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_DELETE_FAILED)
                            .response(Constans.LOG_USER_NOT_EXISTS).build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE,
                    buildCustomLogger("deleteById()", Constans.MSG_RESPONSE_DELETE_FAILED
                            +": "+Constans.LOG_USER_NOT_EXISTS)
                    )
            ;
            throw notFoundException;
        }
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("deleteById()",
                "Eliminando producto por id: " + id))
        ;
        userRepository.deleteById(id);
        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_DELETE_SUCCESSFUL).response(id).build();
    }

    @Override
    public ResponseMainDto update(UserViewDto userViewDto) {
        if(Boolean.FALSE.equals(userRepository.existsById(userViewDto.getId()))) {
            NotFoundException notFoundException =
                    new NotFoundException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_UPDATE_FAILED)
                            .response(Constans.LOG_USER_NOT_EXISTS).build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE,
                    buildCustomLogger("update()", Constans.MSG_RESPONSE_UPDATE_FAILED
                            +": "+Constans.LOG_USER_NOT_EXISTS)
                    )
            ;
            throw notFoundException;
        }
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("update()",
                "Actualizando usuario..!"))
        ;

        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_UPDATE_SUCCESSFUL).response(
                userRepository.save(userMapper.convertToEntityFromView(userViewDto)).getId()
        ).build();
    }

    @Override
    public ResponseMainDto getAll() {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger("getAll()",
                "Consultando todos los usuarios..!"))
        ;
        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_QUERY_SUCCESS)
                .response(userMapper.convertToUserViewListFromUserEntityList(userRepository.findAll())).build()
        ;
    }

    @Override
    public ResponseMainDto register(UserRegisterDto userRegisterDto) {
        if(Boolean.TRUE.equals(userRepository.existsByEmail(userRegisterDto.getEmail()))) {
            AlreadyExistsException alreadyExistsException =
                    new AlreadyExistsException(ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_FAILED)
                            .response("Usuario ya existe en la BD").build()
                    )
            ;
            customLogger.logMain(Boolean.TRUE, buildCustomLogger("register()",
                    alreadyExistsException.getMessage() +": Usuario ya existe en la BD"))
            ;
            throw alreadyExistsException;
        }

        customLogger.logMain(Boolean.FALSE, buildCustomLogger("register()",
                "Registrando usuario..!"))
        ;

        return ResponseMainDto.builder().message(Constans.MSG_RESPONSE_REGISTER_SUCCESS)
                .response(
                        userRepository.save(userMapper.convertToUserRegisterFromUserEntity(userRegisterDto)).getId()
                ).build()
        ;
    }
}
