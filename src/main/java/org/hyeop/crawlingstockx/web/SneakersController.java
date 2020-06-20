package org.hyeop.crawlingstockx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SneakersController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
