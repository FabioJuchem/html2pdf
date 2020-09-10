package com.poc.converter;

import com.poc.converter.service.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Html2Pdf {

    private static Converter converter;

    @Autowired
    public Html2Pdf(Converter converter) {
        this.converter = converter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Html2Pdf.class, args);
    }
}
