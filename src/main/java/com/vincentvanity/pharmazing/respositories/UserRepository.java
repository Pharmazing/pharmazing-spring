package com.vincentvanity.pharmazing.respositories;

import com.vincentvanity.pharmazing.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
