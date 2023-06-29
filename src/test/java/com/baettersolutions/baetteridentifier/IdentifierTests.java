package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.controller.Identifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IdentifierTests {

    @Test
    @DisplayName("Test to find the correct AX-Number over the manufacturer number")
    public void simpleTestWithManufactureArtikelnumber(){
        Identifier test = new Identifier();
        assertEquals(120543242, test.identifyByManufacturArticlenumber("7000042539"));
    }
}
