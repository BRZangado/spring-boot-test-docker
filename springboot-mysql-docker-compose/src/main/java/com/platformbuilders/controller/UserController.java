package com.platformbuilders.controller;

import com.platformbuilders.model.User;
import com.platformbuilders.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(UserController.MAPPING_BASE_URL)
@Api(value = "USER API Endpoints", produces = "application/json")
public class UserController {

    public static final String MAPPING_BASE_URL = "/api/v1/users";

    @Autowired
    private UserService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Users",
            httpMethod = "GET")
    public ResponseEntity<List<User>> index(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "id") String sortBy) {

        List<User> list = service.getUsers(page, size, sortBy);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.add("count", String.valueOf(service.getTotal()));

        return ResponseEntity.ok().headers(headers).body(list);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new user",
            httpMethod = "POST",
            response = User.class)
    public User send(@RequestBody User user) {
        return service.saveUser(user);
    }
}
