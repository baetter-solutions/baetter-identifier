package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.database.Masterdata;
import com.baettersolutions.baetteridentifier.repository.MasterdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class MasterdataController {
    @Autowired
    private MasterdataRepository masterdataRepository;

    @PostMapping
    public void addProduct(@RequestBody final List<Masterdata> products) {
        for (Masterdata product : products) {
            int axnrnewdata = product.getAxnr();
            Masterdata existingProduct = masterdataRepository.findByAxnr(axnrnewdata);
            if (existingProduct != null) {
                updateProduct(product, existingProduct.getId());
            } else {
                masterdataRepository.save(product);
            }
        }
    }

    @PutMapping
    public void putUpdateProduct(@RequestBody final List<Masterdata> productsToUpdate) {
        for (Masterdata productToUpdate : productsToUpdate) {
            int axNrToUpdate = productToUpdate.getAxnr();
            Masterdata existingProduct = masterdataRepository.findByAxnr(axNrToUpdate);
            if (existingProduct != null){
                updateProduct(productToUpdate, existingProduct.getId());
                System.out.println(axNrToUpdate + " wurde aktualisiert");
            }
            else {
                System.err.println("Fehler bei Aktualisierung");
            }
        }
    }

    private void updateProduct(Masterdata updatedProduct, String productId) {
        Optional<Masterdata> optionalExistingProduct = masterdataRepository.findById(productId);
        if (optionalExistingProduct.isPresent()) {
            Masterdata existingProduct = optionalExistingProduct.get();
            existingProduct.setAxnr(updatedProduct.getAxnr());
            existingProduct.setManufacturer(updatedProduct.getManufacturer());
            existingProduct.setShortdescription(updatedProduct.getShortdescription());
            existingProduct.setType(updatedProduct.getType());
            existingProduct.setArticlenumber(updatedProduct.getArticlenumber());
            existingProduct.setRabgroupe(updatedProduct.getRabgroupe());
            existingProduct.setManufactureridnr(updatedProduct.getManufactureridnr());
            existingProduct.setEp1(updatedProduct.getEp1());
            existingProduct.setListprice(updatedProduct.getListprice());
            existingProduct.setStatus(updatedProduct.getStatus());
            existingProduct.setPriceunit(updatedProduct.getPriceunit());
            existingProduct.setMeasureunit(updatedProduct.getMeasureunit());
            masterdataRepository.save(existingProduct);
        }
    }


    @GetMapping
    public List<Masterdata> findProducts() {
        System.out.println("localhost:8080/products");
        return masterdataRepository.findAll();
    }

    @GetMapping("/{axnr}")
    public Masterdata findProductByAxnr(@PathVariable int axnr) {
        System.out.println("localhost:8080/products/120753952");
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
