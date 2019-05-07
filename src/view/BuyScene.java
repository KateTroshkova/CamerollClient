package view;

import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BuyScene extends BorderPane {

    @FXML
    private Label ticketInfo;
    private Session data;

    public BuyScene(Session session){
        this.data=session;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("buy_screen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();
    }

    @FXML
    private void initialize() {
    }

}
