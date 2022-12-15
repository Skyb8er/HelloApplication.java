module com.example.qwerty {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.qwerty to javafx.fxml;
    exports com.example.qwerty;
}