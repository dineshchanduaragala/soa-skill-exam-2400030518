package com.klef.soa.exam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.exam.entity.*;
import com.klef.soa.exam.service.*;

@RestController
@RequestMapping("/servicecenter")
public class ServiceCenterController 
{
	@Autowired
	private ServiceCenterService service;

	@GetMapping("/")
	public String test() 
	{
		return "SOA Service Center REST API";
	}

	// 1. Insert Record
	@PostMapping("/add")
	public ResponseEntity<ServiceCenter> addServiceCenter(@RequestBody ServiceCenter sc) 
	{
		ServiceCenter center = service.addServiceCenter(sc);
		return ResponseEntity.status(201).body(center);
	}

	// 2. Display All Records
	@GetMapping("/displayall")
	public ResponseEntity<List<ServiceCenter>> displayAllServiceCenters() 
	{
		List<ServiceCenter> list = service.displayAllServiceCenters();
		return ResponseEntity.status(200).body(list);
	}

	// 3. Display Record By ID
	@GetMapping("/display")
	public ResponseEntity<?> displayServiceCenterById(@RequestParam Long id) 
	{
		ServiceCenter center = service.displayServiceCenterById(id);

		if (center != null) 
		{
			return ResponseEntity.status(200).body(center);
		} 
		else 
		{
			return ResponseEntity.status(404).body("Service Center ID Not Found");
		}
	}

	// 4. Update Record
	@PutMapping("/update")
	public ResponseEntity<?> updateServiceCenter(@RequestBody ServiceCenter sc) 
	{
		ServiceCenter center = service.updateServiceCenter(sc);

		if (center != null) 
		{
			return ResponseEntity.ok(center);
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Center ID Not Found");
		}
	}

	// 5. Delete Record By ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteServiceCenter(@PathVariable Long id) 
	{
		String message = service.deleteServiceCenter(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	// 6. Derived Query Method 1: findByName
	@GetMapping("/displaybyname/{name}")
	public ResponseEntity<List<ServiceCenter>> displayByName(@PathVariable String name) 
	{
		List<ServiceCenter> list = service.displayByName(name);
		return ResponseEntity.status(200).body(list);
	}

	// 7. Derived Query Method 2: findByType
	@GetMapping("/displaybytype/{type}")
	public ResponseEntity<List<ServiceCenter>> displayByType(@PathVariable String type) 
	{
		List<ServiceCenter> list = service.displayByType(type);
		return ResponseEntity.status(200).body(list);
	}
}
