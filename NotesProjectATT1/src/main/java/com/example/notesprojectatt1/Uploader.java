package com.example.notesprojectatt1;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.input.DragEvent;


import java.io.*;

import java.util.List;
import java.util.Scanner;



//Reference for the drag and drop file functionality
//StackOverflow = https://stackoverflow.com/questions/32534113/javafx-drag-and-drop-a-file-into-a-program
//Java Oracle DragAndDrop = https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm#BABEIIGE


public class Uploader{

    String note="";

    @FXML
    protected TextArea READFILE;
    @FXML
    Button saveNoteFromFile = new Button();
    @FXML
    Label dropped = new Label("");
    @FXML
    VBox dragTArget = new VBox();



    protected final HelloController helloController;
    public Uploader(HelloController helloController) {
        this.helloController = helloController;
    }

    //event to that will create a new note in the main scene
    protected class CreateNOteA implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event){
            note = READFILE.getText();
            System.out.println(note);
            helloController.NewNoteFromFile(note);

        }
    }

    public void initialize(){
        saveNoteFromFile.setOnAction(new CreateNOteA());
        dragTArget.getChildren().addAll(dropped);
        //event when the file is dragged over the targeted section
        dragTArget.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != dragTArget
                        && event.getDragboard().hasFiles()) {

                    event.acceptTransferModes(TransferMode.ANY);
                }
                //consume event to prevent it from being handled further
                event.consume();
            }
        });//event when the file is being dropped in the targeted section
        dragTArget.setOnDragDropped(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();

                if (db.hasFiles()) {
                    dropped.setText(db.getFiles().toString());
                    //create list to save the path from the file
                    List<File> files = db.getFiles();
                    //System.out.println(db.getFiles() + "its working");
                    //System.out.println(files);
                    for(File file: files){
                        Filereader(file);
                    }
                }
                event.consume();
            }
        });
    }

    //Method to read files using a scanner
    public void Filereader(File file) {
        try{
            Scanner scanner = new Scanner(file);
            StringBuilder fileContent = new StringBuilder();
            while(scanner.hasNext()){
                String name = scanner.nextLine();
                System.out.println(name);
                fileContent.append(name).append("\n");

            }
            READFILE.setText(fileContent.toString());
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
