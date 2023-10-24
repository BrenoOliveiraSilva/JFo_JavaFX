module com.jfo_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.jfo_javafx to javafx.fxml;
    exports com.jfo_javafx;
    exports CampusMap;
    opens CampusMap to javafx.fxml;
}