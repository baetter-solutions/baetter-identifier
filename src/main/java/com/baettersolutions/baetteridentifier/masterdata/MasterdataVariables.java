package com.baettersolutions.baetteridentifier.masterdata;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "masterdata")
public class MasterdataVariables {

    @Id
    private String id;
    private int axnr;
    private String manufacturer;
    private String shortdescription;
    private String type;
    private String articlenumber;
    private String rabgroupe;
    private int manufactureridnr;
    private double ep1;
    private double listprice;
    private int status;
    private int priceunit;
    private String measureunit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAxnr() {
        return axnr;
    }

    public void setAxnr(int axnr) {
        this.axnr = axnr;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticlenumber() {
        return articlenumber;
    }

    public void setArticlenumber(String articlenumber) {
        this.articlenumber = articlenumber;
    }

    public String getRabgroupe() {
        return rabgroupe;
    }

    public void setRabgroupe(String rabgroupe) {
        this.rabgroupe = rabgroupe;
    }

    public int getManufactureridnr() {
        return manufactureridnr;
    }

    public void setManufactureridnr(int manufactureridnr) {
        this.manufactureridnr = manufactureridnr;
    }

    public double getEp1() {
        return ep1;
    }

    public void setEp1(double ep1) {
        this.ep1 = ep1;
    }

    public double getListprice() {
        return listprice;
    }

    public void setListprice(double listprice) {
        this.listprice = listprice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPriceunit() {
        return priceunit;
    }

    public void setPriceunit(int priceunit) {
        this.priceunit = priceunit;
    }

    public String getMeasureunit() {
        return measureunit;
    }

    public void setMeasureunit(String measureunit) {
        this.measureunit = measureunit;
    }
}
