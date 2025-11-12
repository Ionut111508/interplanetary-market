package ecommerce.interplanetary.controller;

import ecommerce.interplanetary.entity.Role;
import ecommerce.interplanetary.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
