package com.applicationProcessingSystem.testTaskForVitaSoft.repository;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Modifying
    @Query("delete from Application a where a.id=:id and a.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("select a from Application a where a.id=:id and a.user.id=:userId")
    Optional<Application> getAppById(@Param("id") int id, @Param("userId") int userId);


    @Query("select a from Application a where a.user.id=:userId")
    Optional<List<Application>> getAllForUser(@Param("userId") int userId);

    @Query("select a from Application a where a.status='SENT'")
    List<Application> findSent();

}


