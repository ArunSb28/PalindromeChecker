package com.palindromechecker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.palindromechecker.dto.PalindromeDto;
import com.palindromechecker.entity.PalindromeInput;
import com.palindromechecker.entity.PalindromeStringCalc;
import com.palindromechecker.handler.PalindromeHandler;


@RestController
@RequestMapping("/palindrome")
public class PalindromeController {

	@Autowired
	private PalindromeHandler palindromHandler;
		
	@PostMapping("/save")
	public String save(@RequestBody PalindromeInput palindromeInput) {
		
		String isSuccess = "false";
		String message = "Invalid Request";
		
		JsonObject response = new JsonObject();
		
		if(palindromeInput.getContent()!=null && palindromeInput.getTimestamp()!=null) {
			palindromHandler.save(palindromeInput);
		isSuccess = "true";
		message = "Processed Successfully";
		}
		response.addProperty("success", isSuccess);
		response.addProperty("message", message);
		
		return response.toString();
	}
	
	@GetMapping("/findAll")
	public List<PalindromeStringCalc> getAllProducts(){
		return palindromHandler.getAllProducts();
	}
	
	
}
