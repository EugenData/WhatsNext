package com.company.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.project.entity.Watchlist;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

}