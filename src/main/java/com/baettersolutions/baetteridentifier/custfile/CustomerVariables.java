package com.baettersolutions.baetteridentifier.custfile;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "customer")
public class CustomerVariables {

    @Id
    private String id;
    private int custnr;
    private String customername;
    private String filepathwithname;
    @CreatedDate
    private Date uploadDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustnr() {
        return custnr;
    }

    public void setCustnr(int custnr) {
        this.custnr = custnr;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getFilepathwithname() {
        return filepathwithname;
    }

    public void setFilepathwithname(String filepathwithname) {
        this.filepathwithname = filepathwithname;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
