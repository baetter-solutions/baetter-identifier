package com.baettersolutions.baetteridentifier.database;

import org.bson.types.Decimal128;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Masterdata {
    private int axnr;
    private String manufacturer;
    private String shortdescription;
    private String type;
    private String articlenumber;
    private String rabgroupe;
    private String manufactureridnr;
    private Decimal128 ep1;
    private Decimal128 listprice;
    private int status;
    private int priceunit;
    private String measureunit;
    private String url;

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

    public String getManufactureridnr() {
        return manufactureridnr;
    }

    public void setManufactureridnr(String manufactureridnr) {
        this.manufactureridnr = manufactureridnr;
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

    public Decimal128 getEp1() {
        return ep1;
    }

    public void setEp1(Decimal128 ep1) {
        this.ep1 = ep1;
    }

    public Decimal128 getListprice() {
        return listprice;
    }

    public void setListprice(Decimal128 listprice) {
        this.listprice = listprice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
