package com.hack1.sparky.user.infrastructure;

import com.hack1.sparky.user.domain.Rol;
import com.hack1.sparky.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByCompanyId(Long companyId);

    List<User> findByRole(Rol role);
}
