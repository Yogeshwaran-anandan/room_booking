package com.example.service;

import com.example.entity.Booking;
import com.example.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updated) {
        return bookingRepository.findById(id).map(existing -> {
            if (updated.getFromDate() != null) existing.setFromDate(updated.getFromDate());
            if (updated.getToDate() != null) existing.setToDate(updated.getToDate());
            if (updated.getStatus() != null) existing.setStatus(updated.getStatus());
            return bookingRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
