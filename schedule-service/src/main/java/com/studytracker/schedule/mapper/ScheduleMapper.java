package com.studytracker.schedule.mapper;


import com.studytracker.schedule.dto.request.ScheduleRequest;
import com.studytracker.schedule.dto.response.ScheduleResponse;
import com.studytracker.schedule.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule toSchedule(ScheduleRequest request);

    ScheduleResponse toScheduleResponse(Schedule schedule);

    void updateSchedule(@MappingTarget Schedule schedule, ScheduleRequest request);
    List<ScheduleResponse> toScheduleResponseList(List<Schedule> schedules);
}
