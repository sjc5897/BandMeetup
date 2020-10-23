package com.bandmeetup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
/**
 * This is the main class used to start the application
 * Language: Java 13
 * Framework: Spring
 * Author: Stephen Cook <sjc5897@rit.edu>
 * Created: 10/16/20
 * Edited: 10/21/20
 */


@SpringBootApplication
public class BandmeetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(BandmeetupApplication.class, args);
    }

}
