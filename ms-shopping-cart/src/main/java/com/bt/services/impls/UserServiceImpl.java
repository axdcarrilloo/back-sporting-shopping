package com.bt.services.impls;

import com.bt.domain.repositories.UserRepository;
import com.bt.dtos.BuildLog;
import com.bt.services.UserService;
import com.bt.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomLogger customLogger;
    private final UserRepository userRepository;

    private BuildLog buildCustomLogger() {
        return BuildLog.builder().nameClass(this.getClass().getName())
                .nameMethod("existsById()").message("Validando existencia de usuario por Id").build()
        ;
    }

    @Override
    public Boolean existsById(Long id) {
        customLogger.logMain(Boolean.FALSE, buildCustomLogger());
        return userRepository.existsById(id);
    }
}
