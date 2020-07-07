package com.codmind.orderapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codmind.orderapi.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String username);
}
