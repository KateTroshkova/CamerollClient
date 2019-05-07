package view;

import data.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SignInScene extends BorderPane{

    @FXML
    private TextArea nameText;
    @FXML
    private TextArea passwordText;
    @FXML
    private Label nameErrorText;
    @FXML
    private Label passwordErrorText;

    public SignInScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_in_dialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setNameError(boolean error){
        nameErrorText.setVisible(error);
    }

    public void setPasswordError(boolean error){
        passwordErrorText.setVisible(error);
    }

    public TextArea getNameText() {
        return nameText;
    }

    public TextArea getPasswordText() {
        return passwordText;
    }
}
