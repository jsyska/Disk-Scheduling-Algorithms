package controllers;

import algorithms.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    int[] data;
    public static int[] FCFSSequence;
    public static int[] SSTFSequence;
    public static int[] SCANSequence;
    public static int[] CSCANSequence;
    public static int[] EDFSequence;
    public static int[] FDSCANSequence;
    public static int FCFSSeek;
    public static int SSTFSeek;
    public static int SCANSeek;
    public static int CSCANSeek;
    public static int EDFSeek;
    public static int FDSCANSeek;

    public static int[] deadlineEDF;
    public static int[] deadlineFDScan;

    @FXML
    private TextField numberOfProcesses;

    @FXML
    private TextField range;

    @FXML
    private TextField headPosition;

    @FXML
    private Button simulateBtn;

    @FXML
    private TextField diskSize;

    @FXML
    private ComboBox<String> scanDirection;

    @FXML
    private TextField deadlines;

    @FXML
    void simulate(ActionEvent event) {
        generateData();
        int[] FCFSData=data;
        int[] SSTFData=data;
        int[] SCANData=data;
        int[] CSCANData=data;
        int[] EDFData = data;
        int[] FDSCANData=data;
        String direction = scanDirection.getValue();
        int dSize = Integer.valueOf(diskSize.getText());
        int head = Integer.valueOf(headPosition.getText());
        int numberOfDeadlines = Integer.valueOf(deadlines.getText());


        FCFS.simulate(FCFSData,head);
        FCFSSequence = FCFS.getSequence();
        FCFSSeek=FCFS.getSeek_count();

        EDF.simulate(EDFData,head,numberOfDeadlines);
        EDFSequence = EDF.getSequence();
        EDFSeek=EDF.getSeek_count();
        deadlineEDF=EDF.getArrDeadlines();

        SSTF.simulate(SSTFData,head);
        SSTFSequence = SSTF.getSequence();
        SSTFSeek=SSTF.getSeek_count();


        SCAN.simulate(SCANData,head,direction,dSize);
        SCANSequence = SCAN.getSequence();
        SCANSeek=SCAN.getSeek_count();


        FDSCAN.simulate(FDSCANData,head,direction,dSize,numberOfDeadlines);
        FDSCANSequence = FDSCAN.getSequence();
        FDSCANSeek = FDSCAN.getSeek_count();
        deadlineFDScan = FDSCAN.getArrDeadlines();


        CSCAN.simulate(CSCANData,head,direction,dSize);
        CSCANSequence = CSCAN.getSequence();
        CSCANSeek=CSCAN.getSeek_count();


        changeScene(event);

    }

    @FXML
    void showButton(ActionEvent event) {
        if(Integer.valueOf(numberOfProcesses.getText()) != null
                && Integer.valueOf(range.getText()) != null
                && Integer.valueOf(headPosition.getText()) !=null
                && Integer.valueOf(diskSize.getText()) !=null
                && Integer.valueOf(deadlines.getText()) !=null
                && (scanDirection.getValue()=="left" || scanDirection.getValue() =="right"))
        {
            simulateBtn.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        simulateBtn.setVisible(false);
        scanDirection.getItems().addAll("left","right");

    }

    public void changeScene(ActionEvent event){
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("../utils/charts.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);
            //get the stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generateData(){
        data = new int[Integer.valueOf(numberOfProcesses.getText())];
        for(int i = 0; i< data.length; i++){
            data[i] = getRandomNumberInRange(0, Integer.valueOf(range.getText()));
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int[] getFCFSSequence() {
        return FCFSSequence;
    }

    public static int[] getSSTFSequence() {
        return SSTFSequence;
    }

    public static int[] getSCANSequence() {
        return SCANSequence;
    }

    public static int[] getCSCANSequence() {
        return CSCANSequence;
    }

    public static int[] getEDFSequence() {
        return EDFSequence;
    }

    public static int[] getFDSCANSequence() {
        return FDSCANSequence;
    }

    public static int getFCFSSeek() {
        return FCFSSeek;
    }

    public static int getSSTFSeek() {
        return SSTFSeek;
    }

    public static int getSCANSeek() {
        return SCANSeek;
    }

    public static int getCSCANSeek() {
        return CSCANSeek;
    }

    public static int getEDFSeek() {
        return EDFSeek;
    }

    public static int getFDSCANSeek() {
        return FDSCANSeek;
    }

    public static int[] getDeadlineEDF() {
        return deadlineEDF;
    }

    public static int[] getDeadlineFDScan() {
        return deadlineFDScan;
    }
}
