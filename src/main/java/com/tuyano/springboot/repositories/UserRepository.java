package com.tuyano.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.User;

@Repository //繋げる役目を果たしている
public interface UserRepository extends JpaRepository<User,Long>{

}
