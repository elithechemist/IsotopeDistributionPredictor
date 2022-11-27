package com.fx.chemicalformulasfx;

import com.chemicalformulas.chemicalformulasfx.ProteinChemicalFormula;
import com.chemicalformulas.chemicalformulasfx.SimpleChemicalFormula;
import com.massspec.chemicalformulasfx.MassSpec;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    private TextField textInput = new TextField();
    private RadioButton rbProtein = new RadioButton("Protein");
    private RadioButton rbNormal = new RadioButton("Normal");
    private Button generateButton = new Button("Generate Data");
    private Label molecularWeight = new Label("");
    private Label exactMass = new Label("");
    private double[][] abundanceArray;
    private boolean hasValidMF = false;
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String, Number> barChart = new BarChart<String, Number> (xAxis, yAxis);
    private GridPane pane = new GridPane();
    private XYChart.Series series1 = new XYChart.Series();

    protected GridPane getPane() {
        // VBox for input MF
        VBox input = new VBox();
        input.setSpacing(5);
        Label inputLabel = new Label("Enter the Molecular Formula:");
        input.getChildren().addAll(inputLabel, textInput);

        // Hbox for radio buttons to select *normal or *protein MF
        rbProtein.setSelected(false);
        rbNormal.setSelected(true);
        HBox mfModeButtons = new HBox();
        mfModeButtons.setSpacing(20);
        //rbNormal.setGraphic(new ImageView("https://freesvg.org/img/Benzene-ring.png"));
        mfModeButtons.getChildren().addAll(rbNormal, rbProtein);

        // Create Label
        Label mfModeLabel = new Label("Select Molecular Formula Mode:");

        // Button color
        generateButton.setStyle("-fx-color: green");

        // VBox for mF buttons and label
        VBox mfMode = new VBox();
        mfMode.setSpacing(5);
        mfMode.getChildren().addAll(mfModeLabel, mfModeButtons);

        // VBox for complete input section
        VBox inputSection = new VBox();
        inputSection.setSpacing(20);
        inputSection.setStyle("-fx-border-color: black");
        inputSection.setPadding(new Insets(5, 5, 5, 5));
        inputSection.getChildren().addAll(input, mfMode, generateButton);

        // VBox for output section
        Label headerLabel = new Label("Chemical Information");
        headerLabel.setUnderline(true);
        headerLabel.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
        VBox outputSection = new VBox();
        outputSection.setSpacing(5);
        outputSection.setStyle("-fx-border-color: black");
        outputSection.setPadding(new Insets(5, 5, 5, 5));
        outputSection.getChildren().addAll(headerLabel, molecularWeight, exactMass);

        // Create a bar chart
        xAxis.setLabel("Monoisotopic Mass");
        yAxis.setLabel("Percent Relative Abundance");
        barChart.setStyle("-fx-border-color: black");
        VBox barChartBox = new VBox();
        barChartBox.getChildren().add(barChart);

        pane.add(barChartBox, 0, 1, 2, 2);

        // Create GridPane
        pane.setPadding(new Insets(20, 20, 50, 50));
        pane.add(inputSection, 0, 0);
        pane.add(outputSection, 1, 0);
        pane.setVgap(5);
        pane.setHgap(5);

        // Handle button pressing
        rbNormal.setOnAction(e -> rbProtein.setSelected(false));
        rbProtein.setOnAction(e -> rbNormal.setSelected(false));
        generateButton.setOnAction(e -> generateMF());
        return pane;
    }

    private void generateMF() {
        barChart.getData().clear();
        series1 = new XYChart.Series();
        if(rbNormal.isSelected()) {
            SimpleChemicalFormula chemForm = new SimpleChemicalFormula(textInput.getText());
            if(chemForm.molecularWeightString().equals("Not a valid molecular formula")) {
                molecularWeight.setText("Not a valid molecular formula");
            }
            else {
                molecularWeight.setText("Molecular Weight: " + chemForm.molecularWeightString());
                MassSpec massSpec = new MassSpec(chemForm);
                this.abundanceArray = massSpec.massSpecArray();

                for(int i = 0; i < 10; i++) {
                    String tempString = String.valueOf((int)abundanceArray[i][0]);
                    series1.getData().add(new XYChart.Data(tempString, 100 * abundanceArray[i][1]));
                }
                series1.setName(chemForm.toString());
                barChart.getData().add(series1);
            }
        }
        else {
            ProteinChemicalFormula chemForm = new ProteinChemicalFormula(textInput.getText());
            if(chemForm.molecularWeightString().equals("Not a valid molecular formula")) {
                molecularWeight.setText("Not a valid protein molecular formula!\n\nAsp-Gly-Phe and D-G-F both represent\nthe tripeptide aspartylglycylphenylalan-\nine. Dashes are required.");
            }
            else {
                molecularWeight.setText("Molecular Weight: " + chemForm.molecularWeightString());
                MassSpec massSpec = new MassSpec(chemForm);
                this.abundanceArray = massSpec.massSpecArray();

                for(int i = 0; i < 10; i++) {
                    String tempString = String.valueOf((int)abundanceArray[i][0]);
                    series1.getData().add(new XYChart.Data(tempString, 100 * abundanceArray[i][1]));
                }
                series1.setName(chemForm.toString());
                barChart.getData().add(series1);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(getPane(), 600, 500);
        primaryStage.setTitle("Molecular Formula Analyser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
