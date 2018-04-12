package com.ism.reservation;

import com.ism.reservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController {

    @Autowired
    public ReservationService reservationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Reservation> getReservations(){
        return reservationService.getAll();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id){
        return reservationService.getById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        try {
            return new ResponseEntity<>(reservationService.addReservation(reservation), HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Reservation> modifyReservation(@RequestBody Reservation reservation){
        Optional<Reservation> optionalReservation = reservationService.getById(reservation.getId());
        if(!optionalReservation.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            return new ResponseEntity<>(reservationService.modifyReservation(reservation),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
