package com.ism.reservation;

import com.ism.reservation.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface ReservationService {

    Collection<Reservation> getAll();

    Optional<Reservation> getById(Integer id);

    Reservation addReservation(Reservation reservation) throws IllegalArgumentException;

    Reservation modifyReservation(Reservation reservation) throws IllegalArgumentException;
}
