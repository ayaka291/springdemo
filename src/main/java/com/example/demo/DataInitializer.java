package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Brand;
import com.example.demo.repositories.BrandRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void run(String... args) {
        if (brandRepository.count() == 0) {
            brandRepository.save(new Brand("ロフト"));
            brandRepository.save(new Brand("東急ハンズ"));
            brandRepository.save(new Brand("スリーコインズ"));
            brandRepository.save(new Brand("PLAZA"));
            brandRepository.save(new Brand("無印良品"));
            brandRepository.save(new Brand("Francfranc"));
            brandRepository.save(new Brand("ケユカ"));
        }
    }
    
}
