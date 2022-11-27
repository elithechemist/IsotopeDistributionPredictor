module com.example.chemicalformulasfx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.chemicalformulasfx to javafx.fxml;
    exports com.example.chemicalformulasfx;
}