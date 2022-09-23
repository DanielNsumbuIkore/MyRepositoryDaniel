package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();

        //Fixed logic that is more suitable
        double duration = (outHour - inHour) / 3600000;

        //Condition: If the duration (in hours) does not exceed 30 minutes (i.e. 0.5)
        //Parking is free
        if(duration <= 0.5) {
            ticket.setPrice(0);}

        else {
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");
            }
        }
        //5% reduction for a user who has come once
        if (ticket.isRecurrent()) {
            ticket.setPrice(ticket.getPrice() * 0.95);
        }

    }
}