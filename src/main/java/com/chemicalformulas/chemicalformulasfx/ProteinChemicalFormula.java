import java.util.Arrays;

public class ProteinChemicalFormula extends ChemicalFormula {
    public static final String[] ONE_LETTER_AMINO_ACIDS_SYMBOLS = {"A", "R", "N", "D", "C",
            "E", "Q", "G", "H", "I",
            "L", "K", "M", "F", "P",
            "S", "T", "W", "Y", "V"};
    public static final String[] THREE_LETTER_AMINO_ACID_SYMBOLS = {"Ala", "Arg", "Asn", "Asp", "Cys",
            "Glu", "Gln", "Gly", "His", "Ile",
            "Leu", "Lys", "Met", "Phe", "Pro",
            "Ser", "Thr", "Trp", "Tyr", "Val"};
    /**
     * Gives the molecular formula of the corresponding amino acids in the form
     * {H, C, N, O, S}
     */
    public static final Integer[][] AMINO_ACID_ELEMENT_ARRAYS = {{7, 3, 1, 2, 0}, {14, 6, 4, 2, 0}, {8, 4, 2, 3, 0}, {7, 4, 1, 4, 0}, {7, 3, 1, 2, 1},
            {9, 5, 1, 4, 0}, {10, 5, 2, 3, 0}, {5, 2, 1, 2, 0}, {9, 5, 3, 2, 0}, {13, 6, 1, 2, 0},
            {13, 6, 1, 2, 0}, {14, 6, 2, 2, 0}, {11, 5, 1, 2, 1}, {11, 9, 1, 2, 0}, {9, 5, 1, 2, 0},
            {7, 3, 1, 3, 0}, {9, 4, 1, 3, 0}, {12, 11, 2, 2, 0}, {11, 9, 1, 3, 0}, {11, 5, 1, 2, 0}};

    public ProteinChemicalFormula(String chemicalFormulaString) {
        elementsArray = toArray(chemicalFormulaString, elementsArray);
    }
    public ProteinChemicalFormula() {
        this("");
    }

    protected Integer[] toArray(String cleanString, Integer[] elementsArray) {
        String tempString;
        int i = 0;
        int numPeptideBonds;
        while (i < cleanString.length()) {
            tempString = "";
            while (i < cleanString.length() && cleanString.charAt(i) != '-') {
                tempString += cleanString.charAt(i);
                i++;
                if(i < cleanString.length() && cleanString.charAt(i) == '-') {
                    elementsArray = subtractWaterElementsArray(elementsArray);
                }
            }
            elementsArray = addAminoAcidElementsArray(tempString, elementsArray);
            i++;
        }

        return elementsArray;
    }

    private Integer[] addAminoAcidElementsArray(String tempString, Integer[] elementsArray) {
        //System.out.println("Adding " + tempString + "...");
        int tempInt;
        if(tempString.length() == 1)
            tempInt = Arrays.asList(ONE_LETTER_AMINO_ACIDS_SYMBOLS).indexOf(tempString);
        else
            tempInt = Arrays.asList(THREE_LETTER_AMINO_ACID_SYMBOLS).indexOf(tempString);
        elementsArray[0] += AMINO_ACID_ELEMENT_ARRAYS[tempInt][0];
        elementsArray[5] += AMINO_ACID_ELEMENT_ARRAYS[tempInt][1];
        elementsArray[6] += AMINO_ACID_ELEMENT_ARRAYS[tempInt][2];
        elementsArray[7] += AMINO_ACID_ELEMENT_ARRAYS[tempInt][3];
        elementsArray[15] += AMINO_ACID_ELEMENT_ARRAYS[tempInt][4];
        return elementsArray;
    }

    private Integer[] subtractWaterElementsArray(Integer[] elementsArray) {
        elementsArray[0] -= 2;
        elementsArray[7] -= 1;
        //System.out.println("Removing a water...");
        return elementsArray;
    }
}
