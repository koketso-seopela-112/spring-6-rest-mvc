package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder()
                .customerName("Koketso")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(1)
                .id(UUID.randomUUID())
                .build();
        Customer customer2 = Customer.builder()
                .customerName("Shaun")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(2)
                .id(UUID.randomUUID())
                .build();
        Customer customer3 = Customer.builder()
                .customerName("Sbusiso")
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .version(3)
                .id(UUID.randomUUID())
                .build();

        customerMap.put(customer1.getId(),customer1);
        customerMap.put(customer2.getId(),customer2);
        customerMap.put(customer3.getId(),customer3);
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> listCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer newCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .lastModifiedDate(customer.getLastModifiedDate())
                .createdDate(customer.getCreatedDate())
                .build();

        customerMap.put(savedCustomer.getId(),savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, Customer customer) {
        Customer existingCustomer = customerMap.get(customerId);

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setVersion(customer.getVersion());
        existingCustomer.setCreatedDate(customer.getCreatedDate());
        existingCustomer.setLastModifiedDate(customer.getLastModifiedDate());

        customerMap.put(existingCustomer.getId(),existingCustomer);

    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, Customer customer) {
        Customer existing = customerMap.get(customerId);

        if(StringUtils.hasText(customer.getCustomerName())){
            existing.setCustomerName(customer.getCustomerName());
        }

        if(customer.getVersion() != null){
            existing.setVersion(customer.getVersion());
        }

        if(customer.getLastModifiedDate() != null){
            existing.setLastModifiedDate(customer.getLastModifiedDate());
        }
        if(customer.getCreatedDate() != null){
            existing.setCreatedDate(customer.getCreatedDate());
        }

        customerMap.replace(customerId,existing);
    }
}
