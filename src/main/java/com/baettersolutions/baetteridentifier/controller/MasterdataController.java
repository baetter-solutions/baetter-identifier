package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.database.MasterdataVariables;
import com.baettersolutions.baetteridentifier.repository.MasterdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class MasterdataController {
    private final MasterdataRepository masterdataRepository;

    @Autowired
    public MasterdataController(MasterdataRepository masterdataRepository) {
        this.masterdataRepository = masterdataRepository;
    }

    @PostMapping
    public void addProduct(@RequestBody final List<MasterdataVariables> products) {
        int saveCounter = 0;
        int updateCounter = 0;
        for (MasterdataVariables product : products) {
            int axnrnewdata = product.getAxnr();
            MasterdataVariables existingProduct = masterdataRepository.findByAxnr(axnrnewdata);
            if (existingProduct != null) {
                updateProduct(product, existingProduct.getId());
                updateCounter++;
            } else {
                masterdataRepository.save(product);
                saveCounter++;

            }
        }
        int totalCount = saveCounter + updateCounter;
        if (totalCount > 0) {
            System.out.println("Total transmitted: " + totalCount);
            System.out.println(" - New: " + saveCounter);
            System.out.println(" - Updated " + updateCounter);
            FileUploadController response = new FileUploadController();
            response.buildResponse(saveCounter, updateCounter, totalCount);
        }
    }

    @PutMapping
    public void putUpdateProduct(@RequestBody final List<MasterdataVariables> productsToUpdate) {
        for (MasterdataVariables productToUpdate : productsToUpdate) {
            int axNrToUpdate = productToUpdate.getAxnr();
            MasterdataVariables existingProduct = masterdataRepository.findByAxnr(axNrToUpdate);
            if (existingProduct != null) {
                updateProduct(productToUpdate, existingProduct.getId());
            } else {
                System.err.println("Update failed");
            }
        }
    }

    private void updateProduct(MasterdataVariables updatedProduct, String productId) {
        Optional<MasterdataVariables> optionalExistingProduct = masterdataRepository.findById(productId);
        if (optionalExistingProduct.isPresent()) {
            MasterdataVariables existingProduct = optionalExistingProduct.get();
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
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed");
        }
    }


    @GetMapping
    public List<MasterdataVariables> findProducts() {
        return masterdataRepository.findAll(); //localhost:8080/products
    }

    @GetMapping("/{axnr}")
    public MasterdataVariables findProductByAxnr(@PathVariable int axnr) {
        return masterdataRepository.findByAxnr(axnr); //localhost:8080/products/120753952"
    }

    @DeleteMapping("/{axnr}")
    public void deleteProductByAxnr(@PathVariable int axnr) {
        MasterdataVariables existingProduct = masterdataRepository.findByAxnr(axnr);
        if (existingProduct != null) {
            masterdataRepository.delete(existingProduct);
        }
    }

    @DeleteMapping
    public void deleteAllProducts() {
        masterdataRepository.deleteAll();
    }


}

