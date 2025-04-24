package com.studytracker.schedule.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleResponse {

    String id;
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
