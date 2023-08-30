package com.springbootacademy.batch8.pos.controller;

import com.springbootacademy.batch8.pos.dto.CustomerDTO;
import com.springbootacademy.batch8.pos.dto.request.CustomerUpdateDTO;
import com.springbootacademy.batch8.pos.service.CustomerService;
import com.springbootacademy.batch8.pos.service.impl.CustomerServiceIMPL;
import com.springbootacademy.batch8.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

   //1)
    @PostMapping("save-1")
    public String saveCustomer(@RequestBody  CustomerDTO customerDTO){
//        CustomerServiceIMPL customerServiceIMPL = new CustomerServiceIMPL();
//        customerServiceIMPL.saveCustomer(customerDTO);
            customerService.saveCustomer(customerDTO);
            return "saved";

    }
    @PutMapping("update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String messeage = customerService.updateCustomer(customerUpdateDTO);
        return messeage;
    }

    @GetMapping(
            path = "get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerId(@RequestParam("id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }
//    @GetMapping(
//            path = "get-all-customers"
//    )
//    public List<CustomerDTO> getAllCustomers(){
//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
//        return allCustomers;
//
//    }
    @GetMapping(
            path = "get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Successfull",allCustomers),
                HttpStatus.OK

        );

    }
    @DeleteMapping(
            path = "Delete-Customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id")int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;

    }
    @GetMapping(
            path = "get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActivestate(@PathVariable(value = "status")boolean activeState){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActivestate(activeState);
        return allCustomers;

    }



}
//localhost:8090/api/v1/customer