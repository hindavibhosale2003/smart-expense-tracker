package com.example.demo.dto;



public class AnalyticsDto {

    private String label;

    private Double value;

    public AnalyticsDto() {
    }

    public AnalyticsDto(
            String label,
            Double value
    ) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}