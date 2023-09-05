package com.joostit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        IbanChecker checker = new IbanChecker();
        checker.isIbanValid("NL40RABO2486932380");

    }

}