module com.linhvu.hotelmgmt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires org.controlsfx.controls;
    requires mysql.connector.j;

    opens com.linhvu.hotelmgmt to javafx.fxml;
    exports com.linhvu.hotelmgmt;
    exports com.linhvu.pojo;
}
