package com.subho.controller;
import java.sql.SQLException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.subho.businesslogic.VendorOperation;
import com.subho.entities.Vendor;

@RestController
public class VendorController {
	
	@Autowired
	VendorOperation vendorRepo;
	
	@RequestMapping("/vendors")
	public List<Vendor> getAllVendors(){
		return vendorRepo.getAllVendors();
	} 
	
	
	@RequestMapping("/vendors/{id}")
	public Vendor getVendorById(@PathVariable("id")String id) {
		return vendorRepo.getSingleVendor(id);
	}
	
	@PostMapping("/vendors")
	public Vendor createNewVendor(Vendor payload) throws SQLException {
		return vendorRepo.createVendor(payload);
	}
     
}
