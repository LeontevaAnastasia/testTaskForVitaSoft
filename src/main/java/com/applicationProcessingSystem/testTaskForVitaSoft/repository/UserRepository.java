package com.applicationProcessingSystem.testTaskForVitaSoft.repository;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("delete from User u where u.id=:id")
    int delete(@Param("id") int id);

    @Query("select u from User u where u.id=:id")
    User getUserById(int id);

    @Query("select u from User u where u.name=:name")
    User findByUsername(String name);



}
