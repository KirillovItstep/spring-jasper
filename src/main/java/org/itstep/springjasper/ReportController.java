package org.itstep.springjasper;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ReportService reportService;

    @GetMapping(value={"/","index"})
    public String index(){
        return "redirect:/index.html";
    }

    @GetMapping(value="/customers/report", params={})
    public String generateReport(){
        System.out.println("ok");
        List<Customer> customers = customerService.findAll();
        try {
            reportService.exportReport(customers, "customers.jrxml", "customers.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/customers.html";
    }

    @GetMapping(value="/customers/report",params={"sort"})
    public String generateReportOrderby(@RequestParam("sort") String sort){
        List<Customer> customers = customerService.findByOrderBy(sort);
        try {
            reportService.exportReport(customers,"report1.jrxml", "report1.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report1.html";
    }

    @GetMapping(value="/customers/report2",params={"sort"})
    public String generateReportOrderby2(@RequestParam("sort") String sort){
        List<Customer> customers = customerService.findByOrderBy(sort);
        try {
            reportService.exportReport(customers,"report2.jrxml", "report2.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report2.html";
    }
}
