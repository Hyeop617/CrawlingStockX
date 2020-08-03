package org.hyeop.crawlingstockx.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyeop.crawlingstockx.service.SneakersService;
import org.hyeop.crawlingstockx.web.dto.SneakersDto;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SneakersController {

    @Autowired
    SneakersService sneakersService;

    @GetMapping("/")
    public String index(Model model) throws JsonProcessingException {
//        List<SneakersDto> sne = sneakersService.printSneakers();
//        model.addAttribute("sneakersList", sne);
//        model.addAttribute("hello", "Hello Thymeleaf!");
//
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sne));
//        return "index";

        return "index.html";
    }
}
