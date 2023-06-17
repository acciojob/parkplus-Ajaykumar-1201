package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//ParkingLotController:
//Add a new parking lot to the database with a given name and address.
//Add a new spot within a specific parking lot with a given number of wheels and price per hour. The spot type should be determined by the number of wheels (2 or 4 wheels for "car" and more than 4 wheels for "others").
//Delete a specific spot from a parking lot.
//Update the details of a specific spot within a parking lot, including the price per hour.
//Delete a specific parking lot and all spots within it.

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(address);
        parkingLot.setName(name);
        parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        //create a new spot in the parkingLot with given id
        //the spot type should be the next biggest type in case the number of wheels are not 2 or 4, for 4+ wheels, it is others
        Spot spot = new Spot();
        if(numberOfWheels > 4) {
            spot.setSpotType(SpotType.OTHERS);
        } else if(numberOfWheels > 2) {
            spot.setSpotType(SpotType.FOUR_WHEELER);
        } else {
            spot.setSpotType(SpotType.TWO_WHEELER);
        }
        spot.setPricePerHour(pricePerHour);
        spot.setOccupied(Boolean.FALSE);

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        spot.setParkingLot(parkingLot);

        //Bidirectional
        parkingLot.getSpotList().add(spot);
        parkingLotRepository1.save(parkingLot);

        return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
        spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();

        Spot spot = spotRepository1.findById(spotId).get();
//        List<Spot> spotList = parkingLot.getSpotList();
//        for(Spot spot1 : spotList){
//            if(spot1.getId()==spotId)
//                spot=spot1;
//        }

        spot.setPricePerHour(pricePerHour);
        spot.setParkingLot(parkingLot);
        spotRepository1.save(spot);

        return spot;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
