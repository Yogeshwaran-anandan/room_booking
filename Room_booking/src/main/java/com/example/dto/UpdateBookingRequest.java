package com.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBookingRequest {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String status;
}
