package com.ge.predix.solsvc.training.alarmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages=("com.ge.predix.solsvc.training.alarmservice.entity"))
@EnableJpaRepositories("com.ge.predix.solsvc.training.alarmservice.repository")
public class PredixHospitalAlarmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PredixHospitalAlarmServiceApplication.class, args);
    }
}
