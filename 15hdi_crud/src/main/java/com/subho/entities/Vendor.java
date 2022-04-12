package com.subho.entities;
import java.beans.*;
import lombok.Getter;
import lombok.Setter;

public class Vendor {

	@Getter
	@Setter
	private String id;
	@Getter
	@Setter
	private String firstName;
	@Getter
	@Setter
	private String lastName;
	@Getter
	@Setter
	private String companyName;
	@Getter
	@Setter
	private String website;
	@Getter
	@Setter
	private String email;
	@Getter
	@Setter
	private String vstatus;
	@Getter
	@Setter
	private String gstNumber;
	
	public Vendor() {
		
	}
	public Vendor(String id, String firstName, String lastName, String companyName, String website, String email,
			String vstatus, String gstNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.website = website;
		this.email = email;
		this.vstatus = vstatus;
		this.gstNumber = gstNumber;
	}
	
	
	
}
