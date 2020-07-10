package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,String>{
    List<Registration> findByDispatchIdLike(String id);
}