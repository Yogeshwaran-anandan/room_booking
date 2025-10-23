package com.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookingRequest {
    private String guestName;
    private String guestEmail;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long roomId;
}
