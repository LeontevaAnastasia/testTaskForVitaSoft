package com.applicationProcessingSystem.testTaskForVitaSoft.repository;

import com.applicationProcessingSystem.testTaskForVitaSoft.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    @Query("delete from Application a where a.id=:id and a.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("select a from Application a where a.id=:id and a.user.id=:userId")
    Application getAppById(@Param("id") int id, @Param("userId") int userId);

    @Query("select a from Application a where a.user.id=:userId")
    List<Application> getAll(@Param("userId") int userId);


}


