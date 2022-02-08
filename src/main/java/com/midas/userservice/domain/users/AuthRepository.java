package com.midas.userservice.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUserId(Long userId);
}
