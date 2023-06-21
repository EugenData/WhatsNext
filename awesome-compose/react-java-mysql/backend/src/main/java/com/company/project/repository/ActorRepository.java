package com.company.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.project.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

}