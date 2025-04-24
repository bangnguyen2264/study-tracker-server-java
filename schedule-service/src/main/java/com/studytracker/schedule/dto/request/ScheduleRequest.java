package com.studytracker.schedule.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {

    String userId;
    String title;
    String description;
    Date start;
    Date end;
    Boolean allDay;
    String type;
    String status;
    String category;
    String roadmapId;
    String color;
}
