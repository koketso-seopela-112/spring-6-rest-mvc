package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping
    public List<Customer> listCustomers(){
        return  customerService.listCustomers();
    }
    @PostMapping
    public ResponseEntity<Customer> handlePost(@RequestBody Customer customer){
        customerService.newCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location","/api/v1/customer" + customer.getId());
        return new  ResponseEntity(headers, HttpStatus.CREATED);
    }
    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID customerId){
        return customerService.getCustomerById(customerId);
    }
}
