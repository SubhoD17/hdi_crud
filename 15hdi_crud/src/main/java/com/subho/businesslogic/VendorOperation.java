package com.subho.businesslogic;

import java.beans.Statement;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;
import com.subho.entities.Vendor;

import argo.jdom.*;
import argo.saj.InvalidSyntaxException;

@Component
public class VendorOperation {

	public Connection conn;
	public Statement stmt;
	public ResultSet res;
	String url;
	String user;
	String password;

//	PostConstruct
//Extarct the VCAP SERVICES value to create a secure connection to the HANA DB (HDI Container)

	@PostConstruct
	public void startConnection() {
		this.url = "";
		this.user = "";
		this.password = "";

		String vcap_service = System.getenv("VCAP_SERVICES");
		System.out.println(vcap_service);
		if (vcap_service != null && vcap_service.length() > 0) {
//			Argo  VCAP perse
			try {
				JsonNode root = new JdomParser().parse(vcap_service);
				JsonNode serviceRoot = root.getNode("hanatrial");
				JsonNode cred = serviceRoot.getNode(0).getNode("credentials");

				this.url = cred.getStringValue("url");
				this.user = cred.getStringValue("user");
				this.password = cred.getStringValue("password");

			} catch (InvalidSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in VCAP_SERVICES");
			}

//			Create DB Connection

			try {
				conn = DriverManager.getConnection(this.url, this.user, this.password);
				stmt = (Statement) conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Database Connection failed");
			}

		}
	}

	public List<Vendor> getAllVendors() {
		List<Vendor> vendorList = new ArrayList<Vendor>();

		try {
			res = ((java.sql.Statement) stmt).executeQuery("SELECT  top 100 * from vendor");
			
			while(res.next()) {
				vendorList.add(new Vendor(
						res.getString("ID"),
						res.getString("FIRSTNAME"),
						res.getString("LASTNAME"),
						res.getString("COMPANYNAME"),
						res.getString("WEBSITE"),
						res.getString("EMAIL"),
						res.getString("VSTATUS"),
						res.getString("GSTNUMBER")
						) );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		vendorList.add(new Vendor("demo", "demo", "demo", "demo", "demo", "demo", "demo", "demo"));
		return vendorList;
	}
//	Kill the connection to the HDB
	@PreDestroy
	public void endConnection() throws SQLException {
		
			res.close();
			((ResultSet) stmt).close();
			conn.close();
		
	}
	
}
