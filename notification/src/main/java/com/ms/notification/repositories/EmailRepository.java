package com.ms.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.notification.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{

    
}