package com.baettersolutions.baetteridentifier.custfile;

public class CustomerFileHandler {
    public int custSheetnumber;
    public int custHeadline;
    public int columnWithNumberToIdentify;
    public int getCustSheetnumber() {
        return custSheetnumber;
    }

    public void setCustSheetnumber(int custSheetnumber) {
        this.custSheetnumber = custSheetnumber;
    }

    public int getCustHeadline() {
        return custHeadline;
    }

    public void setCustHeadline(int custHeadline) {
        this.custHeadline = custHeadline;
    }

    public int getColumnWithNumberToIdentify() {
        return columnWithNumberToIdentify;
    }

    public void setColumnWithNumberToIdentify(int columnWithNumberToIdentify) {
        this.columnWithNumberToIdentify = columnWithNumberToIdentify;
    }
}
