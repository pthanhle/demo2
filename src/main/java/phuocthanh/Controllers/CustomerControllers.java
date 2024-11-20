package phuocthanh.Controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import phuocthanh.Entity.Customer;

@RestController
public class CustomerControllers {

    final private List<Customer> customers = List.of(
        Customer.builder().id("1").name("Nguyen Huu Trung").email("nguyenhuu@gmail.com").build(),
        Customer.builder().id("2").name("Tran Van A").email("tranvana@gmail.com").build(),
        Customer.builder().id("3").name("Nguyen Thi B").email("nguyenthb@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> list = this.customers;
        return ResponseEntity.ok(list);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream()
            .filter(customer -> customer.getId().equals(id))
            .toList();
        return ResponseEntity.ok(customers.get(0));
    }
}

