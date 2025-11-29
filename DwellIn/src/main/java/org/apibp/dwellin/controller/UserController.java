package org.apibp.dwellin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.User;
import org.apibp.dwellin.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated    // IMPORTANT → Enables validation on request bodies
public class UserController {

    private final UserService userService;

    @PostMapping
    public User registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @Valid @RequestBody User userPatch) {
        return userService.patchUser(id, userPatch);
    }
}

