package org.apibp.dwellin.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.exception.ResourceAlreadyExistsException;
import org.apibp.dwellin.exception.ResourceNotFoundException;
import org.apibp.dwellin.model.User;
import org.apibp.dwellin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {

        if (userRepository.existsByMail(user.getMail())) {
            throw new ResourceAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByPhoneNo(user.getPhoneNo())) {
            throw new ResourceAlreadyExistsException("Phone number already exists");
        }

        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User patchUser(Long id, User patch) {
        User existing = getUser(id);

        if (patch.getPhoneNo() != null) {
            if (!patch.getPhoneNo().equals(existing.getPhoneNo())
                    && userRepository.existsByPhoneNo(patch.getPhoneNo())) {
                throw new ResourceAlreadyExistsException("Phone number already exists");
            }
            existing.setPhoneNo(patch.getPhoneNo());
        }

        if (patch.getMail() != null) {
            if (!patch.getMail().equals(existing.getMail())
                    && userRepository.existsByMail(patch.getMail())) {
                throw new ResourceAlreadyExistsException("Email already exists");
            }
            existing.setMail(patch.getMail());
        }

        if (patch.getName() != null) existing.setName(patch.getName());
        if (patch.getGender() != null) existing.setGender(patch.getGender());
        if (patch.getDob() != null) existing.setDob(patch.getDob());
        if (patch.getPassword() != null) existing.setPassword(patch.getPassword());
        if (patch.getAddress() != null) existing.setAddress(patch.getAddress());
        if (patch.getRole() != null) existing.setRole(patch.getRole());

        return userRepository.save(existing);
    }
}
