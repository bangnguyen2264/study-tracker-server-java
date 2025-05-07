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

import java.time.LocalDateTime;
import java.util.Date;
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
    public String addAllSchedules(List<ScheduleRequest> listRequest) {
        if (listRequest == null || listRequest.isEmpty()) {
            return "No schedules provided to create.";
        }

        try {
            // Convert each ScheduleRequest to Schedule entity
            List<Schedule> schedules = listRequest.stream()
                    .map(scheduleMapper::toSchedule)
                    .toList();

            // Save all schedules in a batch
            scheduleRepository.saveAll(schedules);

            // Return success message
            return String.format("Successfully created %d schedules.", listRequest.size());
        } catch (Exception e) {
            // Handle any errors (e.g., database issues, validation errors)
            return String.format("Failed to create schedules: %s", e.getMessage());
        }
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

    public List<ScheduleResponse> getSchedulesByUserIdAndDate(String userId, Date date) {
        log.info("Date at time {}", date);
        List<Schedule> schedules = scheduleRepository.findSchedulesByUserIdAndDate(userId,date);
        log.info("Found " + schedules.size() + " schedules");
        return scheduleMapper.toScheduleResponseList(schedules);
    }

}
