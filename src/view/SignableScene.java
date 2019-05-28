package view;

import data.Entrance;
import data.Registration;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.InputValidation;
import presenter.IMVPContract;
import presenter.SignPresenter;

import java.util.Optional;

public class SignableScene extends BorderPane implements IMVPContract.ISignScene{

    @FXML
    private MenuItem signInButton;
    @FXML
    private MenuItem signUpButton;

    private SignInScene inContent;
    private SignUpScene upContent;

    private SignPresenter presenter;

    protected void registerMenuAction(){
        presenter=new SignPresenter();
        presenter.attachView(this);
        presenter.viewIsReady();
        signInButton.setOnAction(event -> {
            Dialog<Entrance> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            SignInScene content=new SignInScene();
            content.getNameText().textProperty().addListener((observable, oldValue, newValue) -> content.setNameError(new InputValidation().validName(newValue)));
            content.getPasswordText().textProperty().addListener((observable, oldValue, newValue) -> content.setPasswordError(new InputValidation().validPassword(newValue)));
            dialog.getDialogPane().setContent(content);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Entrance(content.getNameText().getText(), content.getPasswordText().getText());
                }
                return null;
            });
            Optional<Entrance> result = dialog.showAndWait();
            result.ifPresent(usernamePassword -> presenter.enter(result.get()));
        });
        signUpButton.setOnAction(event -> {
            Dialog<Registration> dialog = new Dialog<>();
            dialog.setTitle("Registration");
            ButtonType loginButtonType = new ButtonType("Sign up", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            SignUpScene content=new SignUpScene();
            content.getNameText().textProperty().addListener((observable, oldValue, newValue) -> content.setNameError(new InputValidation().validName(newValue)));
            content.getPasswordText().textProperty().addListener((observable, oldValue, newValue) -> content.setPasswordError(new InputValidation().validPassword(newValue)));
            content.getConfirmText().textProperty().addListener((observable, oldValue, newValue) -> content.setConfirmError(new InputValidation().validConfirm(content.getPasswordText().getText(), newValue)));
            content.getAdditionalText().textProperty().addListener((observable, oldValue, newValue) -> {
                boolean valid = new InputValidation().validManager(newValue);
                content.setAdditionalError(valid);
                if (valid){
                    //content.getAdditionalText().setVisible(false);
                    content.getIsManagerCheck().setSelected(false);
                }
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
            result.ifPresent(usernamePassword -> presenter.register(result.get()));
        });
    }

    @Override
    public void showError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        VBox dialogPaneContent = new VBox();
        Label label = new Label("Stack Trace:");
        TextArea textArea = new TextArea();
        textArea.setText(error);
        dialogPaneContent.getChildren().addAll(label, textArea);
        alert.getDialogPane().setContent(dialogPaneContent);
        alert.showAndWait();
    }

}
