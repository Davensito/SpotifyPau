module com.example.codigo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.codigo to javafx.fxml;
    exports com.example.codigo;

    exports com.example.codigo.clases;
    opens com.example.codigo.clases to javafx.fxml;

}