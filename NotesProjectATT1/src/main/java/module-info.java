module com.example.notesprojectatt1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.notesprojectatt1 to javafx.fxml;
    exports com.example.notesprojectatt1;
}