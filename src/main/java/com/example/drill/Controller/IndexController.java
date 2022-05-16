package com.example.drill.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("favicon.ico")
    @ResponseBody
    public void returnFavicon(){}

    @GetMapping
    public String view(Model model){
        return "login";
    }
//git1234567891234567
}
