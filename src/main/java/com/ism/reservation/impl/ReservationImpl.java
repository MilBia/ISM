package com.ism.reservation.impl;

import com.ism.reservation.ReservationService;
import com.ism.reservation.model.Reservation;
import com.ism.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ReservationImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Collection<Reservation> getAll() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Reservation> getById(Integer id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation addReservation(Reservation reservation) throws IllegalArgumentException {
        if(reservation.getId() == null || !reservationRepository.existsById(reservation.getId())) {
            try {
                return reservationRepository.save(reservation);
            }
            catch (DataAccessException e){
                throw  new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Reservation does already exist");
    }

    @Override
    public Reservation modifyReservation(Reservation reservation) throws IllegalArgumentException {
        Optional<Reservation> originalReservation = reservationRepository.findById(reservation.getId());
        if(originalReservation.isPresent()) {
            try {
                Reservation originalReservationContent = originalReservation.get();
                if (reservation.getStartDate()==null)
                    reservation.setEndDate(originalReservationContent.getStartDate());
                if (reservation.getEndDate()==null)
                    reservation.setEndDate(originalReservationContent.getEndDate());
                if (reservation.getEmail()==null)
                    reservation.setEmail(originalReservationContent.getEmail());
                if (reservation.getMobilePhone()==null)
                    reservation.setMobilePhone(originalReservationContent.getMobilePhone());
                if (reservation.getTables()==null)
                    reservation.setTables(originalReservationContent.getTables());
                return reservationRepository.save(reservation);
            } catch (DataAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Reservation does not exist");
    }
}
