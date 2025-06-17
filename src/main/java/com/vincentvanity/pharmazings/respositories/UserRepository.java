package com.vincentvanity.pharmazings.respositories;

import com.vincentvanity.pharmazings.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
