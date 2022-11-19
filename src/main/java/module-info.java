module com.linhvu.hotelmgmt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.linhvu.hotelmgmt to javafx.fxml;
    exports com.linhvu.hotelmgmt;
}
