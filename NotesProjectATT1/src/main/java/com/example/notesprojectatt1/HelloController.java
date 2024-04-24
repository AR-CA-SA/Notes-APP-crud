package com.example.notesprojectatt1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import java.io.IOException;
//I forgot to change the name of the controller but this controller will be in charged of the main scene
public class HelloController {


    @FXML
    protected VBox Body;

    @FXML
    protected ScrollPane scrollpane;

    @FXML
    protected FlowPane noteContainer;

    public HelloController(){
        scrollpane = new ScrollPane(Body);

       noteContainer = new FlowPane();

       scrollpane.setContent(noteContainer);
       scrollpane.setFitToHeight(true);
    }


    //Function to create a newNote (This function creates a note without taking any external files, this will be a brand new note with a default text)
    @FXML
    protected void NewNote(){

        TextArea Note = new TextArea("Write your note here"  );
        Note.setId("NoteBox");
        Button Delete = new Button("DELETE");
        Delete.setId("DeleteButt");
        Delete.setOnAction(new Xdelete());
        VBox container = new VBox(Note,Delete);
        container.setId("Parent1");
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);

        noteContainer.getChildren().add(container);
        System.out.println(noteContainer.getChildren());

    }

    @FXML

    //Method to open a new window(Upload file window)
    protected void NewSceneFileR() throws IOException{


        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(HelloController.class.getResource("Uploader.fxml"));

            Uploader uploader = new Uploader(this);
            fxmlLoader.setController(uploader);

            Scene scene = new Scene(fxmlLoader.load(), 600,500);
            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
            Stage stage = new Stage();
            stage.setTitle("Notes");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("failed to open new window");
            e.printStackTrace();
        }
    }
    //Method to create a new note that is coming from a file(this method is not being in this controller but it is being called in the Uploader controller)
    //Same functionality as the NewNote Method but instead of starting with a default note it will take a string that contains text from a user file
    @FXML
    protected void NewNoteFromFile(String note){
        TextArea Note = new TextArea(note);
        Note.setId("NoteBox");
        Button Delete = new Button("DELETE");
        Delete.setId("DeleteButt");
        Delete.setOnAction(new Xdelete());

        VBox container = new VBox(Note,Delete);
        container.setId("Parent1");
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);
        noteContainer.getChildren().add(container);
        System.out.println(note);

    }

    //// This event handler deletes an individual note by removing its corresponding VBox from the note container.
    private class Xdelete implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            Button deleteB = (Button) event.getSource();
            VBox vboxToDelete = (VBox) deleteB.getParent();
            noteContainer.getChildren().remove(vboxToDelete);

        }

    }







}