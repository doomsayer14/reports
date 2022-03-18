package com.internship.reports.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Treatment {
    private int num;

    private String title;
    private String currency;
    private Double price;
    private LocalDate startDate;

    void n(){
        System.out.println();
    }

}
