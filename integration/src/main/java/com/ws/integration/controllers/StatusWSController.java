package com.ws.integration.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 25 de fev de 2021
 ***************************************************************/
@RestController
public class StatusWSController {
	
	@GetMapping("/ws/status/verification")
	public String verification() {
		return "WS active in server";
	}

}
