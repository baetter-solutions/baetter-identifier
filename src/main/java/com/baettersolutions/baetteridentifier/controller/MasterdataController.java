package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.database.Masterdata;
import com.baettersolutions.baetteridentifier.repository.MasterdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MasterdataController {
    @Autowired
    private MasterdataRepository masterdataRepository;

    @PostMapping("/products")
    public void addProduct(@RequestBody final List<Masterdata> products){
        masterdataRepository.saveAll(products);
    }
    @GetMapping("/products")
    public List<Masterdata> findProducts(){

        return masterdataRepository.findAll();
    }

    @GetMapping("/products/{productId}")
    public Masterdata findProduct(@PathVariable final String productId){
        return masterdataRepository.findById(productId).orElseGet(Masterdata::new);
    }
}
