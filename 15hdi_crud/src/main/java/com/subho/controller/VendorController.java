package com.subho.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
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
     
}
