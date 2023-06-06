package com.codecool.gift_rocket.repository.JPA;

import com.codecool.gift_rocket.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

     Optional<UserEntity> findByUsername(String username);
}
