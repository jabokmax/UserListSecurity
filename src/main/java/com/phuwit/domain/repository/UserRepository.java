package com.phuwit.domain.repository;

import com.phuwit.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByEmail(String email);
    User findById(int id);
}
