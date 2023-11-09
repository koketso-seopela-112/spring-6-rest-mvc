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

    @PatchMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomerPatchById(@PathVariable UUID customerId,@RequestBody Customer customer){
        customerService.patchCustomerById(customerId,customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "Updating customer with ID - " + customer.getId() );

        return new ResponseEntity(headers,HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable("customerId") UUID customerId){
        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable UUID customerId,@RequestBody Customer customer){
        customerService.updateCustomerById(customerId,customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "Updating customer with ID - " + customer.getId() );

        return new ResponseEntity(headers,HttpStatus.NO_CONTENT);
    }
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
