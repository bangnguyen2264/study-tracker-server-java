package com.studytracker.schedule.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "categories")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {

    @Id
    String id;
    String userId;
    String title;
    String description;
    Date start;
    Date end;
    @Field("all_day")
    Boolean allDay;
    String type;
    String status;
    String category;
    @Field("roadmap_id")
    String roadmapId;
    String color;
}
