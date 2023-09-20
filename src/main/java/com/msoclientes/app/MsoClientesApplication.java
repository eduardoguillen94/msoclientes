package com.msoclientes.app;

import java.io.File;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class MsoClientesApplication implements CommandLineRunner {

	@Value("${data.clientes}")
	private String clientesData;

	public static void main(String[] args) {
		SpringApplication.run(MsoClientesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {

			File myObj = new File(clientesData);

			if (!myObj.exists()) {
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
					objectMapper.writeValue(myObj, new ArrayList<>());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
