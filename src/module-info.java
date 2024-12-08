module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;
    requires jdk.jshell;
/*    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.databind;*/
    requires javafx.graphics;
    opens gui to javafx.fxml;
    exports gui;
}