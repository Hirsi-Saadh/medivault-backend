package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUid(String uid);

}
