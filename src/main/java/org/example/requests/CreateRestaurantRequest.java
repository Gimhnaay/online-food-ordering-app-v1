package org.example.requests;

import lombok.Data;
import org.example.model.Address;
import org.example.model.ContactInformation;

import java.util.List;


    @Data
    public class CreateRestaurantRequest {
        private Long id;
        private String name;
        private String description;
        private String cuisineType;
        private Address address;
        private ContactInformation contactInformation;
        private String openingHours;
        private List<String> images;


}
