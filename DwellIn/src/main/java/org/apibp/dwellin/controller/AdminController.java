package org.apibp.dwellin.controller;

import lombok.RequiredArgsConstructor;
import org.apibp.dwellin.model.Admin;
import org.apibp.dwellin.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping("/email/{email}")
    public Admin getByEmail(@PathVariable String email) {
        return adminService.getByEmail(email);
    }

    @GetMapping
    public List<Admin> getAll() {
        return adminService.getAll();
    }
}
