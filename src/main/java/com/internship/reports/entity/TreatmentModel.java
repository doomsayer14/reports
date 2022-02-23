package com.internship.reports.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.time.LocalDate;

@JsonDeserialize(builder = TreatmentModel.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
public class TreatmentModel {
    private String title;
    //    private String currency;
    private Double price;
    private LocalDate startDate;

    @Setter
    @Getter
    @JsonPOJOBuilder
    public static class Builder {
        private String title;
        //    private String currency;
        private Double price;
        private LocalDate startDate;

        public TreatmentModel build() {
            return new TreatmentModel(title, price, startDate);
        }
    }
}
