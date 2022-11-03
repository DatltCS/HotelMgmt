module com.linhvu.hotelmgmt {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.linhvu.hotelmgmt to javafx.fxml;
    exports com.linhvu.hotelmgmt;
}
