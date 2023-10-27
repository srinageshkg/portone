package com.dcp.portone;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// https://jsonplaceholder.typicode.com

@RestController
@RequestMapping("/ht")
public class ThController {
    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        theModel.addAttribute("mySystemDate", new java.util.Date());
        return "thtemp";
    }

    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }
    @GetMapping("/processForm")
    public String processForm() {
        return "processForm";
    }
}
