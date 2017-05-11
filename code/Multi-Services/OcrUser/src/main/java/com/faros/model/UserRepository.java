package com.faros.model;

/**
 * Created by guang on 2017/4/21.
 */
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
