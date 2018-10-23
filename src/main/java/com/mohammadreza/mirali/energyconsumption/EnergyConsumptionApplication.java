package com.mohammadreza.mirali.energyconsumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com/mohammadreza/mirali/energyconsumption/domain")
@ComponentScan("com.mohammadreza.mirali.energyconsumption")
public class EnergyConsumptionApplication{

	public EnergyConsumptionApplication() {


	}

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumptionApplication.class, args);
	}
}
