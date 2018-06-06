package com.fbw.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbw.service.entity.portal.TrackEntity;

@Repository
public interface TrackLogRepository extends JpaRepository<TrackEntity, Long>
{

}
