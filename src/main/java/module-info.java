module com.example.rocnikovaprace {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.rocnikovaprace to javafx.fxml;

    exports com.example.rocnikovaprace;
    exports ProjektSachy;
}