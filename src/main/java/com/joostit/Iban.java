package com.joostit;

import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.Map;

import static java.util.Map.entry;

public class Iban {

    private String countryCode;
    private String bankAccountNumber;
    private String checkDigits;
    private String rawIban;

    public String getCountryCode() {
        return countryCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getCheckDigits() {
        return checkDigits;
    }

    public String getRawIban() {
        return rawIban;
    }


    public Iban(String iban){

        rawIban = iban.replace(" ", "").toUpperCase();
        countryCode = rawIban.substring(0, 2);
        checkDigits = rawIban.substring(2, 4);
        bankAccountNumber = rawIban.substring(4, rawIban.length());

        IbanData.validateCountryLength(this);
        IbanData.validateChecksum(this);
    }









}
