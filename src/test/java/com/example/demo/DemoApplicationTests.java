package com.example.demo;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.controller.PharmaciesController;
import com.example.demo.controller.UserController;

import com.example.dto.request.BuyMaskRequest;
import com.example.dto.response.OpeningHoursResponse.DAY;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private PharmaciesController pramaciesController;

	@Autowired
	private UserController userController;

	@Test
	void pramaciesControllerTest() {

		pramaciesController.getsPharmacies(DAY.Sat, 10);
		pramaciesController.getsPharmacies(DAY.Sat, -1);

		pramaciesController.getSoldPharmacies("DFW Wellness", "name");
		pramaciesController.getSoldPharmacies("DFW Wellness", "price");

		pramaciesController.SearchRangePharmacies(9, 35, "greater:3");
		pramaciesController.SearchRangePharmacies(9, 35, "less:3");

		pramaciesController.searchPharmacies("blue", "mask");
		pramaciesController.searchPharmacies("Well", "pharamcies");
	}

	@Test
	void userControllerTest() throws ParseException, java.text.ParseException {

		userController.getTopUsers("2021-01-02 15:18:51", "2021-01-20 15:18:51", 2);

		userController.searcHistory("2021-01-02 15:18:51", "2021-01-20 15:18:51");

		BuyMaskRequest maskInfomation = new BuyMaskRequest("Yvonne Guerrero",
				"Keystone Pharmacy",
				"True Barrier (green) (3 per pack)");

		userController.buyMask(maskInfomation);
	}

}
