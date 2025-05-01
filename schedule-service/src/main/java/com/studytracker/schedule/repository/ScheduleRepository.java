package com.studytracker.schedule.repository;

import com.studytracker.schedule.entity.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByUserId(String userId);

    @Query("{$and: [{ 'userId': ?0 }, " +
            "{ $expr: { $and: [" +
            "  { $lte: [ { $dateTrunc: { date: '$start', unit: 'day' } }, ?1 ] }," +
            "  { $gte: [ { $dateTrunc: { date: '$end', unit: 'day' } }, ?1 ] }" +
            "] } } ] }")
    List<Schedule> findSchedulesByUserIdAndDate(String userId, Date date);
}
