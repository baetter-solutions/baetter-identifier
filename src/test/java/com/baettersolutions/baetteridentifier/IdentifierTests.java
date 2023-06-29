package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.controller.Identifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IdentifierTests {

    @Test
    @DisplayName("Test (1) to find the correct AX-Number over the manufacturer number")
    public void simpleTestWithManufacturerArticlenumber() {
        Identifier test1 = new Identifier();
        assertEquals("120543242", test1.identifyByManufacturArticlenumber("7000042539"));
    }

    @Test
    @DisplayName("In the case that test1 failed, check over manufacturer type")
    public void simpleTestWithManufacturerType() {
        Identifier test2 = new Identifier();
        assertEquals("120334280", test2.identifyByManufacturType("D02GG40V25"));
    }
}
