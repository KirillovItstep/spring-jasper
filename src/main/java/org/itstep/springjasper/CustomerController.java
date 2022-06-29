package org.itstep.springjasper;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

//@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value="/customers", produces="application/json")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }


}
