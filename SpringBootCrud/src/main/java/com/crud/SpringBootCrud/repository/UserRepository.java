package com.crud.SpringBootCrud.repository;

import com.crud.SpringBootCrud.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u.id , u.active ,u.user_email ,u.gender ,u.name ,u.password  from `user` u ", nativeQuery = true)
    List<UserEntity> findAll();

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
