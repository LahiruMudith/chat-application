module org.example.chatapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.chatapplication.controller to javafx.fxml;
    opens org.example.chatapplication to javafx.fxml;
    exports org.example.chatapplication;
    exports org.example.chatapplication.controller to javafx.fxml;
}