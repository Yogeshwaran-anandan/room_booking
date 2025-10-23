package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;
    private String guestEmail;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String status;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
