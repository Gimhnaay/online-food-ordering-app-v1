package org.example.service.impl;

import org.example.dto.RestaurantDto;
import org.example.model.Address;
import org.example.model.Restaurant;
import org.example.model.User;
import org.example.repository.AddressRepository;
import org.example.repository.RestaurantRepository;
import org.example.repository.UserRepository;
import org.example.requests.CreateRestaurantRequest;
import org.example.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


    @Service
    public class RestaurantServiceImpl implements RestaurantService {

        @Autowired
        private RestaurantRepository restaurantRepository;
        @Autowired
        private AddressRepository addressRepository;

        @Autowired
        private UserRepository userRepository;

        public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
            Address address= addressRepository.save(req.getAddress());
            System.out.println("UserEntity: " + user);

            Restaurant restaurant = new Restaurant();
            restaurant.setAddress(address);
            restaurant.setContactInformation(req.getContactInformation());
            restaurant.setCuisineType(req.getCuisineType());
            restaurant.setDescription(req.getDescription());
            restaurant.setName(req.getName());
            restaurant.setOpeningHours(req.getOpeningHours());
            restaurant.setRegistrationDate(LocalDateTime.now());
            restaurant.setOwner(user);
            //not sure start///////////////////////////
            restaurant.setImages(req.getImages());
            //stop////////////////////////////////

            return restaurantRepository.save(restaurant);
        }

        @Override
        public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
            Restaurant restaurant= findRestaurantByID(restaurantId);
            if (restaurant.getCuisineType()!=null){
                restaurant.setCuisineType(updatedRestaurant.getCuisineType());
            }
            if (restaurant.getDescription()!=null){
                restaurant.setDescription(updatedRestaurant.getDescription());
            }
            if (restaurant.getName()!=null){
                restaurant.setName(updatedRestaurant.getName());
            }
            return restaurantRepository.save(restaurant);
        }

        @Override
        public void deleteRestaurant(Long restaurantId) throws Exception {
            Restaurant restaurant= findRestaurantByID(restaurantId);
            restaurantRepository.delete(restaurant);
        }

        @Override
        public List<Restaurant> getAllRestaurant() {
            return restaurantRepository.findAll();
        }

        @Override
        public List<Restaurant> searchRestaurant(String keyword) {
            return restaurantRepository.findBySearchQuery(keyword);
        }

        @Override
        public Restaurant findRestaurantByID(Long id) throws Exception {
            Optional<Restaurant> opt = restaurantRepository.findById(id);
            if (opt.isEmpty()){
                throw new Exception("restaurant not found with id "+id);
            }
            return opt.get();
        }

        @Override
        public Restaurant getRestaurantByUserId(Long userId) throws Exception {
            Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
            if (restaurant==null){
                throw new Exception("restaurant not found with owner id "+userId);
            }
            return restaurant;
        }

        @Override
        public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
            Restaurant restaurant = findRestaurantByID(restaurantId);
            RestaurantDto dto = new RestaurantDto();
            dto.setDescription(restaurant.getDescription());
            dto.setImages(restaurant.getImages());
            dto.setTitle(restaurant.getName());
            dto.setId(restaurantId);


            boolean isFavorites = false;
            List<RestaurantDto> favorites = user.getFavorites();
            for (RestaurantDto favourite: favorites){
                if (favourite.getId().equals(restaurantId)){
                    isFavorites=true;
                    break;
                }
            }
            if (isFavorites){
                favorites.removeIf(favorite->favorite.getId().equals(restaurantId));

            }else {
                favorites.add(dto);
            }

            userRepository.save(user);
            return dto;
        }

        @Override
        public Restaurant updateRestaurantStatus(Long id) throws Exception {
            Restaurant restaurant = findRestaurantByID(id);
            restaurant.setOpen(!restaurant.isOpen());

            return restaurantRepository.save(restaurant);
        }



}
