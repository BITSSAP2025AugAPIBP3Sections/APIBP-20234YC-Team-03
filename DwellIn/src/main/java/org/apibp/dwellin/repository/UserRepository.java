package org.apibp.dwellin.repository;

import org.apibp.dwellin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNo(String phoneNo);

    Optional<User> findByMail(String mail);

    boolean existsByPhoneNo(String phoneNo);

    boolean existsByMail(String mail);
}
