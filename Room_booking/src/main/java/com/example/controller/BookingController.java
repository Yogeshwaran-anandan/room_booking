package com.example.controller;

import com.example.dto.CreateBookingRequest;
import com.example.dto.UpdateBookingRequest;
import com.example.entity.Booking;
import com.example.entity.Room;
import com.example.service.BookingService;
import com.example.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    // ✅ Create Booking
    @PostMapping
    public Booking createBooking(@RequestBody CreateBookingRequest req) {
        Room room = roomService.getRoomById(req.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Booking booking = Booking.builder()
                .guestName(req.getGuestName())
                .guestEmail(req.getGuestEmail())
                .fromDate(req.getFromDate())
                .toDate(req.getToDate())
                .room(room)
                .totalPrice(room.getPrice())
                .build();

        return bookingService.saveBooking(booking);
    }

    // ✅ Get all Bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // ✅ Get single Booking by ID
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // ✅ Update Booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody UpdateBookingRequest req) {
        Booking updatedBooking = Booking.builder()
                .fromDate(req.getFromDate())
                .toDate(req.getToDate())
                .status(req.getStatus())
                .build();

        return bookingService.updateBooking(id, updatedBooking);
    }

    // ✅ Delete Booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
