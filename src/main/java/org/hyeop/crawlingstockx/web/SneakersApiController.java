package org.hyeop.crawlingstockx.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hyeop.crawlingstockx.service.SneakersService;
import org.hyeop.crawlingstockx.web.dto.SneakersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SneakersApiController {

    @Autowired
    SneakersService sneakersService;

    @GetMapping("/api/index")
    public String getSneakersList() throws JsonProcessingException {
        List<SneakersDto> list = sneakersService.printSneakers("");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }

    @PostMapping("/api/update")
    public List<Long> updateSneakersList() throws JsonProcessingException {
        return sneakersService.updateSneakers("");
    }

    @GetMapping("/api/search/{keyword}")
    public String searchSneakersList(@PathVariable("keyword") String keyword) throws JsonProcessingException {
        List<SneakersDto> list = sneakersService.printSneakers(keyword);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }
}
