package view;

import data.Entrance;
import data.Registration;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class SignableScene extends BorderPane {

    @FXML
    private MenuItem signInButton;
    @FXML
    private MenuItem signUpButton;

    protected void registerMenuAction(){
        signInButton.setOnAction(event -> {
            Dialog<Entrance> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            SignInScene content=new SignInScene();
            content.getNameText().textProperty().addListener((observable, oldValue, newValue) -> {
                content.setNameError(true);
            });
            content.getPasswordText().textProperty().addListener((observable, oldValue, newValue) -> {
                content.setPasswordError(true);
            });
            dialog.getDialogPane().setContent(content);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Entrance(content.getNameText().getText(), content.getPasswordText().getText());
                }
                return null;
            });
            Optional<Entrance> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                System.out.println("Username=" + usernamePassword.getName() + ", Password=" + usernamePassword.getPassword());
            });
        });
        signUpButton.setOnAction(event -> {
            Dialog<Registration> dialog = new Dialog<>();
            dialog.setTitle("Registration");
            ButtonType loginButtonType = new ButtonType("Sign up", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            SignUpScene content=new SignUpScene();
            content.getNameText().textProperty().addListener((observable, oldValue, newValue) -> {
                content.setNameError(true);
            });
            content.getPasswordText().textProperty().addListener((observable, oldValue, newValue) -> {
                content.setPasswordError(true);
            });
            dialog.getDialogPane().setContent(content);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Registration(content.getNameText().getText(),
                            content.getPasswordText().getText(),
                            content.getConfirmText().getText(),
                            content.getAdditionalText().getText(),
                            content.getIsManagerCheck().isSelected());
                }
                return null;
            });
            Optional<Registration> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> {
                System.out.println("Username=" + usernamePassword.getName() + ", Password=" + usernamePassword.getPassword());
            });
        });
    }
}
