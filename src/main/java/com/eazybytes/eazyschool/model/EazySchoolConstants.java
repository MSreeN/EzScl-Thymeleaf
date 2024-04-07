package com.eazybytes.eazyschool.model;

import lombok.Getter;

@Getter
public enum EazySchoolConstants {
    ANONYMOUS("Anonymous"),
    OPEN("Open"),
    CLOSE("Close"),
    STUDENT_ROLE("Student"),
    ADMIN_ROLE("Role");
    EazySchoolConstants(String value){
        this.value = value;
    }
    String value;
}
