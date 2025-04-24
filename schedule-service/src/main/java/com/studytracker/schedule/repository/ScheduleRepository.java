package com.studytracker.schedule.repository;

import com.studytracker.schedule.entity.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByUserId(String userId);
}
