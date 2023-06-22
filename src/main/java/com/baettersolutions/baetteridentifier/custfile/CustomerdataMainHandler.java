package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.database.ConvertFromExcel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CustomerdataMainHandler {
    private static String uploadedFilepathFromUser;
    int custSheetnumber;
    int custHeadline;

    public static String getUploadedFilepathFromUser() {
        return uploadedFilepathFromUser;
    }

    public void setUploadedFilepathFromUser(String uploadedFilepathFromUser) {
        this.uploadedFilepathFromUser = uploadedFilepathFromUser;
    }

    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser) {
        System.out.println("File located at: " + filepathToExcelfileFromUser);
        uploadedFilepathFromUser = filepathToExcelfileFromUser;
        ConvertFromExcel xslxToWorkitem = new ConvertFromExcel();
        XSSFWorkbook userbook = xslxToWorkitem.generateWorksheet(uploadedFilepathFromUser).getWorkbook();
        // Todo: die Datei soll an das Frontend dann gehen, popup fenster, in welcher man das Tabellenblatt abfragt. ev.einbau eine kontrolle mit blattname.
    }

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

}