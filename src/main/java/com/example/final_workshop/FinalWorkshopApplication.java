package com.example.final_workshop;

import com.example.final_workshop.entity.Product;
import com.example.final_workshop.repository.ProductRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FinalWorkshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalWorkshopApplication.class, args);
    }

}
