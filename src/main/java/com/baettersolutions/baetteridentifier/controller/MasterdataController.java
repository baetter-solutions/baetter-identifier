package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.masterdata.MasterdataVariables;
import com.baettersolutions.baetteridentifier.repository.MasterdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class MasterdataController {
    private final MasterdataRepository masterdataRepository;
    public int saveCounter;

    public int updateCounter;

    public int totalCount;

    @Autowired
    public MasterdataController(MasterdataRepository masterdataRepository) {
        this.masterdataRepository = masterdataRepository;
    }

    @PostMapping
    public void addProduct(@RequestBody final List<MasterdataVariables> products) {
        saveCounter = 0;
        updateCounter = 0;
        totalCount = 0;
        int commentEvery = 50;
        int totalTransmissions = products.size();
        int commentCount = 0;

        for (MasterdataVariables product : products) {
            int axnrnewdata = product.getAxnr();
            MasterdataVariables existingProduct = masterdataRepository.findByAxnr(axnrnewdata);
            if (existingProduct != null) {
                updateProduct(product, existingProduct.getId());
                updateCounter++;
                commentCount++;
            } else {
                masterdataRepository.save(product);
                saveCounter++;
                commentCount++;
            }
            if (commentCount % commentEvery == 0 || commentCount==totalTransmissions/2) {
                int percent = commentCount*100/totalTransmissions;
                System.out.print("\rTransmission Position at " + commentCount + " of " + totalTransmissions + " ("+percent+"%)");
            }
        }
        totalCount = saveCounter + updateCounter;
        if (totalCount > 0) {
            System.out.println("\nTotal transmitted: " + totalCount);
            System.out.println(" - New: " + saveCounter);
            System.out.println(" - Updated " + updateCounter);
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

    @DeleteMapping("/delete/all")
    public void deleteAllProducts() {
        masterdataRepository.deleteAll();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfProducts() {
        long count = masterdataRepository.count();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/totaltransmitted")
    public ResponseEntity<Integer> getCountOfTotaltransmitted() {
        return ResponseEntity.ok(totalCount);
    }
    @GetMapping("/countofnewitems")
    public ResponseEntity<Integer> getCountOfNewItems() {
        return ResponseEntity.ok(saveCounter);
    }
    @GetMapping("/countoffupdated")
    public ResponseEntity<Integer> getCountOfUpdated() {
        return ResponseEntity.ok(updateCounter);
    }

    public int getSaveCounter() {
        return saveCounter;
    }
    public int getUpdateCounter() {
        return updateCounter;
    }
    public int getTotalCount() {
        return totalCount;
    }
}

