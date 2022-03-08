package it.epicode.be.repository.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import it.epicode.be.model.security.Role;
import it.epicode.be.model.security.Roles;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
