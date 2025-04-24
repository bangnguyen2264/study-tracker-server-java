package com.studytracker.schedule.service;

import com.studytracker.schedule.dto.request.ScheduleRequest;
import com.studytracker.schedule.dto.response.ScheduleResponse;
import com.studytracker.schedule.entity.Schedule;
import com.studytracker.schedule.exception.AppException;
import com.studytracker.schedule.exception.ErrorCode;
import com.studytracker.schedule.mapper.ScheduleMapper;
import com.studytracker.schedule.repository.ScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ScheduleService {

    ScheduleRepository scheduleRepository;
    ScheduleMapper scheduleMapper;

    public ScheduleResponse createSchedule(ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toSchedule(request);
        schedule = scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleResponse(schedule);
    }

    public ScheduleResponse getScheduleById(String id) {
         Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        return scheduleMapper.toScheduleResponse(schedule);
    }

    public List<Schedule> getAllSchedules(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Schedule> categoryPage = scheduleRepository.findAll(pageRequest);
        return categoryPage.getContent();
    }

    public List<ScheduleResponse> getScheduleByUserId(String userId) {
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        return scheduleMapper.toScheduleResponseList(schedules);
    }

    public ScheduleResponse updateSchedule(String id,
                               ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXISTED));

        scheduleMapper.updateSchedule(schedule, request);
        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    public void deleteSchedule(String id) {
        scheduleRepository.deleteById(id);
    }


}
