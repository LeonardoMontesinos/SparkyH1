package com.hack1.sparky.user.domain;

import com.hack1.sparky.company.domain.Company;
import com.hack1.sparky.company.infrastructure.CompanyRepository;
import com.hack1.sparky.limit.domain.Limit;
import com.hack1.sparky.limit.infrastructure.LimitRepository;
import com.hack1.sparky.user.infrastructure.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LimitRepository limitRepository;
    private final CompanyRepository companyRepository;

    public User createUser(Long companyId, User user) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        user.setCompany(company);
        user.setRole(Rol.ROLE_USER);
        return userRepository.save(user);
    }

    public List<User> getAllByCompany(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User updateUser(Long id, User updatedData) {
        User user = getById(id);
        user.setEmail(updatedData.getEmail());
        user.setActive(updatedData.isActive());
        return userRepository.save(user);
    }

    @Transactional
    public Limit assignLimit(Long userId, Limit limit) {
        User user = getById(userId);
        limit.setUser(user);
        return limitRepository.save(limit);
    }
}

