package com.employees.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private Integer projectId;
    private String projectName;
    private String clientName;
    private String durationInDays;
    private String startDate;
    private String endDate;
}
