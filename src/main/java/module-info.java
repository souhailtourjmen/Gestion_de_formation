module com.example.gestion_de_formation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.gestion_de_formation to javafx.fxml;
    exports com.example.gestion_de_formation;
    exports com.example.gestion_de_formation.modules.Organisation;
    opens com.example.gestion_de_formation.modules.Organisation to javafx.fxml;
    exports com.example.gestion_de_formation.controler;
    opens com.example.gestion_de_formation.controler to javafx.fxml;
    exports com.example.gestion_de_formation.modules.Sign_in;
    opens com.example.gestion_de_formation.modules.Sign_in to javafx.fxml;
}