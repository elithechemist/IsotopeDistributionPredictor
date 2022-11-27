import java.util.Arrays;
import java.util.List;

public class SimpleChemicalFormula extends ChemicalFormula {

    public SimpleChemicalFormula(String chemicalFormulaString) {
        elementsArray = toArray(chemicalFormulaString, elementsArray);
    }

    @Override
    protected Integer[] toArray(String chemicalFormulaString, Integer[] elementArray) {
        return simpleToArray(chemicalFormulaString, elementArray);
    }
    private Integer[] simpleToArray(String simpleChemicalFormulaString, Integer[] elementArray) {
        String tempElementString = "";
        int index = 0;
        while(index != simpleChemicalFormulaString.length()) {
            do {
                tempElementString += simpleChemicalFormulaString.charAt(index);
                ++index;
                if(index == simpleChemicalFormulaString.length())
                    break;
            } while (!Character.isUpperCase(simpleChemicalFormulaString.charAt(index)));
            // System.out.println(tempElementString);
            Integer[] tempArray = subStringToArray(tempElementString);
            elementArray[tempArray[0]] += tempArray[1];
            tempElementString = "";
        }
        return elementArray;
    }
    private Integer[] subStringToArray(String tempString){
        // Returns an integer array with (1) the index of the atom and (2) the number of atoms
        String[] splitString = tempString.split("(?=\\d)", 2);  // Regular expression splits letter and words of string
        // System.out.println(splitString[0]);
        // System.out.println(splitString[1]);
        List<String> symbolsList = Arrays.asList(ATOMIC_SYMBOLS);
        int indexOf = symbolsList.indexOf(splitString[0]);
        return new Integer[]{indexOf, (splitString.length == 1)? 1 : Integer.valueOf(splitString[1])};
    }
    }