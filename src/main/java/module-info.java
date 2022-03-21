module ru.geekbrains.vvprivalov.networkstorageclient {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens ru.geekbrains.vvprivalov.networkstorageclient to javafx.fxml;
    exports ru.geekbrains.vvprivalov.networkstorageclient;
}