package com.company.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.project.entity.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

}