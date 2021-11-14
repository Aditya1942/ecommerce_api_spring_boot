package com.ecommerce_api.Controllers;

import java.util.HashMap;

import com.ecommerce_api.Dtos.UserDto;
import com.ecommerce_api.Models.JwtRequest;
import com.ecommerce_api.Models.JwtResponse;
import com.ecommerce_api.services.UserDetailServiceImpl;
import com.ecommerce_api.services.userservice;
import com.ecommerce_api.utile.JwtUtil;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "auth") // swagger security
public class UserController {
    @Autowired
    private userservice User;
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        System.out.println(user);
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>("username or password is null", HttpStatus.BAD_REQUEST);
        }
        this.userDetailsService.save(user);
        try {
            authenticate(user.getUsername(), user.getPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            String Token = new JwtResponse(token).getToken();
            HashMap<String, String> map = new HashMap<>();
            map.put("token", "Bearer " + Token);
            return ResponseEntity.ok(map);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@ParameterObject JwtRequest authenticationRequest)
            throws Exception {
        // System.out.println(authenticationRequest.getUsername());
        try {

            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);
            String Token = new JwtResponse(token).getToken();
            HashMap<String, String> map = new HashMap<>();
            map.put("token", "Bearer " + Token);
            return ResponseEntity.ok(map);
            // return new ResponseEntity<?>(Token, HttpStatus.OK);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping("/admins")
    public ResponseEntity<?> getUser() {
        try {
            return ResponseEntity.ok(User.getAdmin());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> Allusers() {
        try {
            return ResponseEntity.ok(User.getAllUsers());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }

    }

    @Parameter(name = "id", example = "1", allowEmptyValue = false, required = true, description = "User id")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(User.getUser(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }

}
