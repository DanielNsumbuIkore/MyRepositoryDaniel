package com.parkit.parkingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.util.InputReaderUtil;

class InteractiveShellTest {

	private static final Logger logger = LogManager.getLogger("InteractiveShell");

	@BeforeEach
	private void loadMenu() {
		System.out.println("Please select an option. Simply enter the number to choose an action");
		System.out.println("1 New Vehicle Entering - Allocate Parking Space");
		System.out.println("2 Vehicle Exiting - Generate Ticket Price");
		System.out.println("3 Shutdown System");

	}

	@Test
	void testLoadInterface() {

		logger.info("App initialized!!!");
		System.out.println("Welcome to Parking System!");

		int yoyo = 0;
		boolean continueApp = true;
		InputReaderUtil inputReaderUtil = new InputReaderUtil();
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
		TicketDAO ticketDAO = new TicketDAO();
		ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);

		while (continueApp) {
			loadMenu();
			int option = 1;
			switch (option) {
			case 1: {
				parkingService.processIncomingVehicle();
				yoyo = 1;
				break;
			}
			case 2: {
				parkingService.processExitingVehicle();
				yoyo = 2;
				break;
			}
			case 3: {
				System.out.println("Exiting from the system!");
				continueApp = false;
				yoyo = 3;
				break;
			}
			default:
				System.out.println("Unsupported option. Please enter a number corresponding to the provided menu");
			}
		}

		assertEquals(1, yoyo);
	}

}
