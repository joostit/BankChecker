package com.joostit;

import java.math.BigInteger;
import java.util.Map;

import static java.util.Map.entry;

public class IbanData {

    private static final BigInteger checkSumDivider = BigInteger.valueOf(97);


    public static final Map<String, Integer> CountryLengths = Map.ofEntries(
            entry("NL", 18),
            entry("DE", 22),
            entry("GB", 22),
            entry("BE", 16),
            entry("FR", 27)
    );


    public static void validateCountryLength(Iban iban){

        if(!IbanData.CountryLengths.containsKey(iban.getCountryCode())){
            throw new IllegalArgumentException("Invalid Country Code.");
        }

        int desiredLength = IbanData.CountryLengths.get(iban.getCountryCode());
        if(iban.getRawIban().length() != desiredLength){
            throw new IllegalArgumentException("Invalid IBAN Length for country code " + iban.getCountryCode() + ". Expected " + desiredLength + ", but found " + iban.getRawIban().length() );
        }
    }


    public static void validateChecksum(Iban iban){

        String numberString = createNumberString(iban.getRawIban());

        BigInteger bigint = new BigInteger(numberString);
        int remainder = bigint.remainder(checkSumDivider).intValue();

        if(remainder != 1){
            throw new IllegalArgumentException("Invalid IBAN checksum");
        }

    }

    private static String createNumberString(String rawIban){

        String numberString = "";

        String firstFour = rawIban.substring(0,4);
        String lastChars = rawIban.substring(4,rawIban.length());

        String raw = lastChars + firstFour;

        for(int i = 0; i < raw.length(); i++){
            Character inChar = raw.charAt(i);

            if(Character.isDigit(inChar)){
                numberString += inChar;
            }
            else{
                Integer letterVal = (int)inChar - 65;   // 64 is ASCII A (upper case)
                letterVal += 10;
                numberString += letterVal.toString();
            }
        }

        return numberString;
    }

}
