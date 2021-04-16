package controllers;

import algorithms.EDF;
import algorithms.FCFS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartsController implements Initializable {

    public static int[] data = {1,2,3,4};

    @FXML
    private LineChart<?, ?> fcfsGrap;

    @FXML
    private Button back;

    @FXML
    private LineChart<?, ?> sstfGrap;

    @FXML
    private LineChart<?, ?> scanGrap;

    @FXML
    private LineChart<?, ?> cscanGrap;

    @FXML
    private LineChart<?, ?> edfGrap;

    @FXML
    private LineChart<?, ?> fdscanGrap;

    @FXML
    private Label fcfsSeek;

    @FXML
    private Label sstfSeek;

    @FXML
    private Label scanSeek;

    @FXML
    private Label cscanSeek;

    @FXML
    private Label edfSeek;

    @FXML
    private Label fdscanSeek;

    @FXML
    private Label deadlineEDF;

    @FXML
    private Label deadlineFDSCAN;


    @FXML
    void goBack(ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../utils/sample.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //get the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int[] FCFSData = Controller.getFCFSSequence();
        int[] SSTFData = Controller.getSSTFSequence();
        int[] SCANData = Controller.getSCANSequence();
        int[] CSCANData = Controller.getCSCANSequence();
        int[] FDSCANData = Controller.getFDSCANSequence();
        int[] EDFData = Controller.getEDFSequence();
        XYChart.Series set1 = new XYChart.Series<>();
        for(int i = 0; i< FCFSData.length;i++){
            set1.getData().add(new XYChart.Data(String.valueOf(i),FCFSData[i]));
        }
        fcfsGrap.getData().addAll(set1);
        fcfsSeek.setText("Execution time: " + Controller.getFCFSSeek());

        XYChart.Series set2 = new XYChart.Series<>();
        for(int i = 0; i< SSTFData.length;i++){
            set2.getData().add(new XYChart.Data(String.valueOf(i),SSTFData[i]));
        }
        sstfGrap.getData().addAll(set2);
        sstfSeek.setText("Execution time: " + Controller.getSSTFSeek());

        XYChart.Series set3 = new XYChart.Series<>();
        for(int i = 0; i< SCANData.length;i++){
            set3.getData().add(new XYChart.Data(String.valueOf(i),SCANData[i]));
        }
        scanGrap.getData().addAll(set3);
        scanSeek.setText("Execution time: " + Controller.getSCANSeek());

        XYChart.Series set4 = new XYChart.Series<>();
        for(int i = 0; i< CSCANData.length;i++){
            set4.getData().add(new XYChart.Data(String.valueOf(i),CSCANData[i]));
        }
        cscanGrap.getData().addAll(set4);
        cscanSeek.setText("Execution time: " + Controller.getCSCANSeek());

        XYChart.Series set5 = new XYChart.Series<>();
        for(int i = 0; i< EDFData.length; i++){
            set5.getData().add(new XYChart.Data(String.valueOf(i),EDFData[i]));
        }
        edfGrap.getData().addAll(set5);
        edfSeek.setText("Execution time: " + Controller.getEDFSeek());
        int[] dEdf= Controller.getDeadlineEDF();

        if(dEdf.length>10)
            deadlineEDF.setVisible(false);
        else {
            deadlineEDF.setText(printArr(dEdf));
            deadlineEDF.setVisible(true);
        }

        XYChart.Series set6 = new XYChart.Series<>();
        for(int i = 0; i< FDSCANData.length;i++){
            set6.getData().add(new XYChart.Data(String.valueOf(i),FDSCANData[i]));
        }
        fdscanGrap.getData().addAll(set6);
        fdscanSeek.setText("Execution time: " + Controller.getFDSCANSeek());
        int[] dFDSCAN =Controller.getDeadlineFDScan();
        if(dFDSCAN.length>10)
            deadlineFDSCAN.setVisible(false);
        else{
            deadlineFDSCAN.setText(printArr(dFDSCAN));
            deadlineFDSCAN.setVisible(true);
        }
    }


    private String printArr(int[]arr){
        String result ="";
        for(int i =0;i<arr.length-1;i++){
            result+= String.valueOf(arr[i]) + ", ";
        }
        result+= arr[arr.length-1];
        return "Deadlines: " + result;
    }

}
