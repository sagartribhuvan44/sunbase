package com.example.Sunbasedata;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController("/api")  // This base path is just a suggestion, change as required
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/authenticate")
    public String authenticate(Model model) {
        String response = apiService.authenticate();
        model.addAttribute("response", response);
        return "result";
    }
    

    @PostMapping("/assignment")
    public ResponseEntity<String> createAssignment(@RequestParam String firstName, 
                                   @RequestParam String lastName, 
                                   @RequestParam Optional<String> street,
                                   @RequestParam Optional<String> address, 
                                   @RequestParam Optional<String> city,
                                   @RequestParam Optional<String> state, 
                                   @RequestParam Optional<String> email, 
                                   @RequestParam Optional<String> phone) {
        return ResponseEntity.ok(apiService.createAssignment(firstName, lastName, street.orElse(null), address.orElse(null), city.orElse(null), state.orElse(null), email.orElse(null), phone.orElse(null)));
    }
    
    @DeleteMapping("/customer/{uuid}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String uuid) {
        return apiService.deleteCustomer(uuid);
    }
    
    @PutMapping("/customer/{uuid}")
    public ResponseEntity<String> updateCustomer(@PathVariable String uuid,
                                 @RequestParam String firstName, 
                                 @RequestParam String lastName, 
                                 @RequestParam String street,
                                 @RequestParam String address, 
                                 @RequestParam String city,
                                 @RequestParam String state, 
                                 @RequestParam String email, 
                                 @RequestParam String phone) {
        return apiService.updateCustomer(uuid, firstName, lastName, street, address, city, state, email, phone);
    }

    // Example error handler for an exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Do some logging here
        return ResponseEntity.status(500).body("An error occurred: " + ex.getMessage());
    }
}
