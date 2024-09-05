package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Address {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String streetAddress;
        private String city;
        private String province;
        private String postalCode;
        private String country;


}
