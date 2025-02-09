package org.example.controller;

import org.example.model.Restaurant;
import org.example.model.User;
import org.example.requests.CreateRestaurantRequest;
import org.example.response.MessageResponse;
import org.example.service.RestaurantService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @CrossOrigin("*")
    @RequestMapping("/api/admin/restaurant")
    public class AdminRestaurantController {

        @Autowired
        private RestaurantService restaurantService;

        @Autowired
        private UserService userService;

        @PostMapping()
        public ResponseEntity<Restaurant> createRestaurant(
                @RequestBody CreateRestaurantRequest req,
                @RequestHeader("Authorization")String jwt
        ) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant= restaurantService.createRestaurant(req,user);
            System.out.println("UserEntity: " + user);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Restaurant> updateRestaurant(
                @RequestBody CreateRestaurantRequest req,
                @RequestHeader("Authorization")String jwt,
                @PathVariable Long id
        ) throws Exception {
            User user = userService.findUserByJwtToken(jwt);
            Restaurant restaurant= restaurantService.updateRestaurant(id,req);
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<MessageResponse> deleteRestaurant(
                @RequestHeader("Authorization")String jwt,
                @PathVariable Long id
        ) throws Exception {

            restaurantService.deleteRestaurant(id);
            MessageResponse res = new MessageResponse();
            res.setMessage("Restaurant delete successfully");
            return new ResponseEntity<>(res,HttpStatus.OK);
        }

        @PutMapping("/{id}/status")
        public ResponseEntity<Restaurant> updateRestaurantStatus (
                @RequestHeader("Authorization")String jwt,
                @PathVariable Long id
        ) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant= restaurantService.updateRestaurantStatus(id);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }

        @GetMapping("/user")
        public ResponseEntity<Restaurant> findRestaurantByUserId (
                @RequestHeader("Authorization")String jwt
        ) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant= restaurantService.getRestaurantByUserId(user.getId());
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        }


}



