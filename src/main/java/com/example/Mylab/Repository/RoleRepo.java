package com.example.Mylab.Repository;

import com.example.Mylab.Model.Role;
import com.example.Mylab.Model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {
    public Role findByRolename(RoleName rolename);
}
