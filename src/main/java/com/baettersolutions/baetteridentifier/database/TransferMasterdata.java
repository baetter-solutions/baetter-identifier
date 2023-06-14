package com.baettersolutions.baetteridentifier.database;

import com.baettersolutions.baetteridentifier.controller.MasterdataController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.bson.types.Decimal128;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransferMasterdata {
    public void transferToDatabase(String jsonFilePath) {
        List<Masterdata> products = readJsonFile(jsonFilePath);

        MasterdataController masterdataController = new MasterdataController();
        System.out.println("Transfer wird initialisiert");
        masterdataController.addProduct(products);
        for (Masterdata product : products) {
            System.out.println(product);
        }
        System.out.println("Transfer abgeschlossen!");
    }

    private List<Masterdata> readJsonFile(String jsonFilePath) {
        List<Masterdata> products = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));

            if (jsonNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) jsonNode;
                for (JsonNode node : arrayNode) {
                    int axnr = node.get("axnr").asInt();
                    String manufacturer = node.get("manufacturer").asText();
                    String shortdescription = node.get("shortdescription").asText();
                    String type = node.get("type").asText();
                    String articlenumber= node.get("articlenumber").asText();
                    String rabgroupe= node.get("rabgroupe").asText();
                    int manufactureridnr= node.get("manufactureridnr").asInt();
                    BigDecimal ep1 = node.get("ep1").decimalValue();
                    BigDecimal listprice = node.get("listprice").decimalValue();
                    int status = node.get("status").asInt();
                    int priceunit = node.get("priceunit").asInt();
                    String measureunit = node.get("measureunit").asText();

                    Masterdata product = new Masterdata();
                    product.setAxnr(axnr);
                    product.setManufacturer(manufacturer);
                    product.setShortdescription(shortdescription);
                    product.setType(type);
                    product.setArticlenumber(articlenumber);
                    product.setRabgroupe(rabgroupe);
                    product.setManufactureridnr(manufactureridnr);
                    product.setEp1(ep1);
                    product.setListprice(listprice);
                    product.setStatus(status);
                    product.setPriceunit(priceunit);
                    product.setMeasureunit(measureunit);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
