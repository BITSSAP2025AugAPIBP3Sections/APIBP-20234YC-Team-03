package org.apibp.dwellin.service;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.exception.ResourceNotFoundException;
import org.apibp.dwellin.model.Admin;
import org.apibp.dwellin.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        admin.setIsActive(true);
        return adminRepository.save(admin);
    }

    public Admin getByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found for email: " + email));
    }

    public List<Admin> getAll() {
        return adminRepository.findAll();
    }
}
