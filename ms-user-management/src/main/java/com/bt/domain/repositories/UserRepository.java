package com.bt.domain.repositories;

import com.bt.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailAndPassword(final String email, final String password);
    Boolean existsByEmail(final String email);
}
