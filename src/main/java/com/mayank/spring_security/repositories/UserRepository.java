package com.mayank.spring_security.repositories;

import com.mayank.spring_security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("""
    SELECT u FROM User u WHERE u.username = :username
    """)
    Optional<User> findUserByUsername(String username);
}
