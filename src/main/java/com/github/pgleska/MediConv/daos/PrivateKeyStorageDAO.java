package com.github.pgleska.MediConv.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.pgleska.MediConv.entities.PrivateKeyStorage;

public interface PrivateKeyStorageDAO extends JpaRepository<PrivateKeyStorage, Integer> {
	Optional<PrivateKeyStorage> findByUserId(Integer id);
}
