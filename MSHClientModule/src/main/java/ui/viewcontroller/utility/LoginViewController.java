package ui.viewcontroller.utility;

import bl.blfactory.BLFactoryImpl;
import blservice.userblservice.UserBLService;
import component.commontextfield.CommonTextField;
import javafx.fxml.FXML;

/**
 * Created by Kray on 2016/11/27.
 */
public class LoginViewController {

    @FXML
    private CommonTextField accountText;

    @FXML
    private CommonTextField passwordText;

    private UtilityViewController utilityViewController;

    public void setUtilityViewController(UtilityViewController utilityViewController) {
        this.utilityViewController = utilityViewController;
    }

    @FXML
    public void clickLoginButton() {
        utilityViewController.login(accountText.getText(), passwordText.getText());
    }

    @FXML
    public void clickSignUpButton() {
        utilityViewController.back();
        utilityViewController.showSignUp();
    }

    @FXML
    public void clickTouristButton() {
        //TODO
        utilityViewController.showClientView();
    }
}
