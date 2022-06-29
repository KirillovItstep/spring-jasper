package org.itstep.springjasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    CustomerService customerService;

    public String exportReport(List<Customer> customers, String template, String report, String reportFormat) throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("classpath:reports/"+template);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Alex");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                    "C:\\Users\\vadim\\IdeaProjects\\spring-jasper\\src\\main\\resources\\public\\"+report);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String sdate = dateFormat.format(cal.getTime());
        return "report has been generated: "+sdate;
    }
}
