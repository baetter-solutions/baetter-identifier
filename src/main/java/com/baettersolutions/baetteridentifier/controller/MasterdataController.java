package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.database.Masterdata;
import com.baettersolutions.baetteridentifier.repository.MasterdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class MasterdataController {
    @Autowired
    private MasterdataRepository masterdataRepository;

    @PostMapping
    public void addProduct(@RequestBody final List<Masterdata> products) {
        for (Masterdata product : products) {
            Masterdata existingProduct = masterdataRepository.findByAxnr(product.getAxnr());
            if (existingProduct != null) {
                existingProduct.setManufacturer(product.getManufacturer());
                existingProduct.setShortdescription(product.getShortdescription());

                masterdataRepository.save(existingProduct);
            } else {
                masterdataRepository.save(product);
            }
        }
    }

    @GetMapping
    public List<Masterdata> findProducts() {
        return masterdataRepository.findAll();
    }

    @GetMapping("/{axnr}")
    public Masterdata findProductByAxnr(@PathVariable int axnr) {
        return masterdataRepository.findByAxnr(axnr);
    }

    @DeleteMapping("/{axnr}")
    public void deleteProductByAxnr(@PathVariable int axnr) {
        Masterdata existingProduct = masterdataRepository.findByAxnr(axnr);
        if (existingProduct != null) {
            masterdataRepository.delete(existingProduct);
        }
    }

}
