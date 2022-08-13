package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.service.FileReaderServie;

@Component
public class fileReaderConfiguration implements CommandLineRunner {

    @Autowired
    private FileReaderServie fileReaderServie;

    @Override
    public void run(String... args) throws Exception {
        fileReaderServie.init();

    }

}
