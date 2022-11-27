package com.developmenttools.chemicalformulasfx;

import java.util.Scanner;

// This class is simply a diagnostic tool for determining the atomic symbol
// from a given atomic number
public class AtomicNumberToSymbol {
    public static void main(String[] args) {
        String symbolString;
        System.out.println("Enter atomic number: ");
        Scanner scnr = new Scanner(System.in);
        Integer atomicNumber = scnr.nextInt();

        switch (atomicNumber) {
            case 1:
                symbolString = "H";
                break;
            case 2:
                symbolString = "He";
                break;
            case 3:
                symbolString = "Li";
                break;
            case 4:
                symbolString = "Be";
                break;
            case 5:
                symbolString = "B";
                break;
            case 6:
                symbolString = "C";
                break;
            case 7:
                symbolString = "N";
                break;
            case 8:
                symbolString = "O";
                break;
            case 9:
                symbolString = "F";
                break;
            case 10:
                symbolString = "Ne";
                break;
            default:
                symbolString = "NOT_INCLUDED";
        }

        System.out.println(symbolString);
    }
}
