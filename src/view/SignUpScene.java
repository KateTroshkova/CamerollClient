package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SignUpScene extends BorderPane{
    @FXML
    TextArea nameText;
    @FXML
    TextArea passwordText;
    @FXML
    TextArea confirmText;
    @FXML
    CheckBox isManagerCheck;
    @FXML
    TextArea additionalText;
    @FXML
    Label additionalErrorText;
    @FXML
    Label confirmErrorText;
    @FXML
    Label passwordErrorText;
    @FXML
    Label nameErrorText;

    public SignUpScene(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign_up_dialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        isManagerCheck.setOnAction(event -> {
            additionalText.setVisible(isManagerCheck.isSelected());
        });
    }

    public TextArea getNameText() {
        return nameText;
    }

    public TextArea getPasswordText() {
        return passwordText;
    }

    public TextArea getConfirmText() {
        return confirmText;
    }

    public TextArea getAdditionalText() {
        return additionalText;
    }

    public CheckBox getIsManagerCheck() {
        return isManagerCheck;
    }

    public void setNameError(boolean error){
        nameErrorText.setVisible(error);
    }

    public void setPasswordError(boolean error){
        passwordErrorText.setVisible(error);
    }

    public void setConfirmError(boolean error){
        confirmErrorText.setVisible(error);
    }

    public void setAdditionalError(boolean error){
        additionalErrorText.setVisible(error);
    }
}
