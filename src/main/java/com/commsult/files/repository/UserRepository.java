package com.commsult.files.repository;

import com.commsult.files.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT u.* FROM users u WHERE u.username = ?1 AND u.password = ?2 LIMIT 1", nativeQuery = true)
    User findByUsernamePassword(String username, String password);
}
