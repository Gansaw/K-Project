package com.airport.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airport.domain.Notice;

public interface NoticeRepo extends JpaRepository<Notice, Long> {

}
