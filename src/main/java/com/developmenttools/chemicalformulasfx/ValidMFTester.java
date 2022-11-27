package com.test.chemicalformulasfx;

import com.chemicalformulas.chemicalformulasfx.SimpleChemicalFormula;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// This class is designed only for program testing purposes. Do not run this as the main file.
// This class tests a file to determine if the molecular formulas contained are valid and parsable
public class ValidMFTester {
    public static void main(String[] args) throws IOException {
        // Example of the ArrayList class
        // This array list takes a file input and
        ArrayList<String> arrayOfMFs = new ArrayList<>();
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter file directory: ");
        String directory = scnr.nextLine();
        System.out.println(directory);
        File file = new File(directory);
        Scanner filescnr = new Scanner(file);
        while(filescnr.hasNext()) {
            arrayOfMFs.add(filescnr.nextLine());
        }
        for(String mfString : arrayOfMFs) {
            SimpleChemicalFormula chemForm = new SimpleChemicalFormula(mfString);
            if(chemForm.getElementsArray()[0] == -1)
            System.out.println("Bad MF");
            else
                System.out.println("Good MF");
        }
    }
}
