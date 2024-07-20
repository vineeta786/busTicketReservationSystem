package com.busTicketManagementSystem.busReservation.service;

import com.busTicketManagementSystem.busReservation.dao.BusRepository;
import com.busTicketManagementSystem.busReservation.entity.Bus;
import com.busTicketManagementSystem.busReservation.model.BusSeatAvailabilityModel;
import com.busTicketManagementSystem.busReservation.model.GeneralMetaDataResponse;
import com.busTicketManagementSystem.busReservation.model.Meta;
import com.busTicketManagementSystem.busReservation.service.inf.SetupService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SetupServiceImpl implements SetupService {

    Logger logger = LoggerFactory.getLogger(SetupService.class);

    @Autowired
    BusRepository busRepository;

    public GeneralMetaDataResponse saveBus(Bus bus) {
        GeneralMetaDataResponse response = validateBusData(bus);
        Meta meta = response.getMeta();

        if (!meta.getSuccess()) {
            logger.info("Bus data not valid. Retry with correct data.");
            meta.setMessageDescription("Bus data not valid. Retry with correct data.");
            response.setData(bus);
            response.setMeta(meta);
            return response;
        }

        bus.setAvailableSeats(bus.getCapacity());
        busRepository.save(bus);
        response.setMeta(meta);
        return response;
    }

    private GeneralMetaDataResponse validateBusData(Bus bus) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();

        if (!busRepository.findBusByBusNumberAndDate(bus.getBusNumber(), bus.getDate()).isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("Bus: "+ bus.getBusNumber()+" details already exist for date: "+ bus.getDate());
            response.setMeta(meta);
            return response;
        }
        response.setMeta(meta);
        return response;

    }

    public GeneralMetaDataResponse findBusesBySourceDestinationAndDate(String sourceLocation,
                                                                       String destinationLocation, String date) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        List<Bus> buses = busRepository.findBySourceDestinationDate(sourceLocation, destinationLocation, date);
        if (buses.isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("No buses found for date: "+ date);
            response.setMeta(meta);
            return response;
        }
        response.setData(buses);
        response.setMeta(meta);
        return response;
    }

    public GeneralMetaDataResponse getAvailableSeatsForBus(String busNumber) {
        GeneralMetaDataResponse response = new GeneralMetaDataResponse();
        Meta meta = new Meta();
        if (busRepository.findBusByBusNumber(busNumber).isEmpty()) {
            meta.setSuccess(false);
            meta.setMessageDescription("Bus: "+ busNumber+" not found!");
            response.setMeta(meta);
            return response;
        }
        List<Bus> buses = busRepository.findBusByBusNumber(busNumber);
        List<BusSeatAvailabilityModel> availabilityOfSeats = new ArrayList<>();
        for (Bus bus : buses) {
            BusSeatAvailabilityModel availability = new BusSeatAvailabilityModel();
            availability.setSeatsAvailable(bus.getAvailableSeats());
            availability.setBusNumber(bus.getBusNumber());
            availability.setPrice(bus.getPrice());
            availability.setDate(bus.getDate());
            availability.setSource(bus.getSource());
            availability.setDestination(bus.getDestination());
            availabilityOfSeats.add(availability);
        }
        response.setData(availabilityOfSeats);
        meta.setMessageDescription("Bus details fetched successfully!");
        response.setMeta(meta);
        return response;
    }
}
