package com.eazybytes.eazyschool.model;

import com.eazybytes.eazyschool.repository.HolidayRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
@Data
@Getter
@Entity
@Table(name = "holidays")
public class Holiday extends  BaseEntity {

    Holiday(){}

    @Id
    private String day;
    private String reason;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type{
        FESTIVAL, FEDERAL;
    }
}
