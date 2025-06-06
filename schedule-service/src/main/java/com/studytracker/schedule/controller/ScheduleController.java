package com.studytracker.schedule.controller;

import com.studytracker.schedule.dto.ApiResponse;
import com.studytracker.schedule.dto.request.ScheduleRequest;
import com.studytracker.schedule.dto.response.ScheduleResponse;
import com.studytracker.schedule.entity.Schedule;
import com.studytracker.schedule.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:8888")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleController {

    ScheduleService scheduleService;

    @PostMapping("/create-schedule")
    public ApiResponse<ScheduleResponse> createSchedules(
            @Valid @RequestBody ScheduleRequest request) {
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.createSchedule(request))
                .build();
    }

    @GetMapping("/get-all-schedules")
    @Operation(summary = "Lấy tất cả")
    public ResponseEntity<List<Schedule>> getAllSchedules(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false ) Integer limit
    ) {
        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 20;
        }
        List<Schedule> categories = scheduleService.getAllSchedules(page, limit);
        return ResponseEntity.ok(categories);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Cập nhat")
    ApiResponse<ScheduleResponse> updateSchedule(@PathVariable String id, @RequestBody ScheduleRequest request) {
        return ApiResponse.<ScheduleResponse>builder()
                .result(scheduleService.updateSchedule(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa mục")
    public void deleteSchedules(@PathVariable String id) {
        scheduleService.deleteSchedule(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lấy danh muc bằng id")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable("id") String id) {
        ScheduleResponse schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/get-schedules-by/{userId}")
    public List<ScheduleResponse> getSchedulesByUserId(@PathVariable String userId) {
        return scheduleService.getScheduleByUserId(userId);
    }
    @GetMapping("/my-schedules")
    public ApiResponse<List<ScheduleResponse>> getSchedulesByUserIdAnDate(
            @RequestParam @NotBlank String userId,
            @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
    ) {
        return ApiResponse.<List<ScheduleResponse>>builder()
                .result(scheduleService.getSchedulesByUserIdAndDate(userId, date))
                .build();
    }

    @PostMapping("/schedules/add-all")
    public ApiResponse<String> addAllSchedules( @RequestBody List<ScheduleRequest> schedules) {
        return ApiResponse.<String>builder()
                .result(scheduleService.addAllSchedules(schedules))
                .build();

    }
}
