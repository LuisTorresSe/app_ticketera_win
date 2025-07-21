package com.win.managerat.domain;

import com.win.role.domain.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
public class ManagerAt {
    private final Long id;
    private final UUID managerId;
    private final String email;
    private String password;
    private String fullname ;
    private Role role;

    public ManagerAt(Long id, UUID managerId, String fullname, Role role, String email, String password) {
        if(managerId == null || managerId.toString().isEmpty()) throw new IllegalArgumentException("Manager id cannot be null or empty");
        if (fullname == null || fullname.isBlank()) throw new IllegalArgumentException("Name is required.");
        if (role == null) throw new IllegalArgumentException("Role is required.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required.");
        if (password == null || password.isBlank()) throw new IllegalArgumentException("Password is required.");
        this.id = id;
        this.managerId = managerId;
        this.fullname = fullname;
        this.role = role;
        this.email = email;
        this.password = password;
    }
    public boolean hasPermission(String permissionName) {
        return role.hasPermission(permissionName);
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean comparePassword(String password) {
        if(password.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty");
        return this.password.equals(password);
    }

}
