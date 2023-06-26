module com.jfo_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.jfo_javafx to javafx.fxml;
    exports com.jfo_javafx;
    exports section_9;
    opens section_9 to javafx.fxml;
}