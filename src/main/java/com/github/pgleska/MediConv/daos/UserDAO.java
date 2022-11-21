package com.github.pgleska.MediConv.daos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.pgleska.MediConv.entities.Role;
import com.github.pgleska.MediConv.entities.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	List<User> findByNameContainingAndRole(String name, Role role);
}
