package pl.edu.agh.userInterface.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.objects.services.Services;
import pl.edu.agh.objects.services.implemetations.*;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class PriceListPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private Services colorMen = new ColorMen();
    private Services colorWomen = new ColorWomen();
    private Services cutAndStyleMen = new CutAndStyleMen();
    private Services cutAndStyleWomen = new CutAndStyleWomen();
    private Services extrasMen = new ExtrasMen();
    private Services extrasWomen = new ExtrasWomen();
    private Services textureAndCurlWomen = new TextureAndCurlWomen();
    private Services valuePackagesWomen = new ValuePackagesWomen();
    private Services valuePackagesMen = new ValuePackagesMen();
    private String name = "Price List Page";

    @FXML
    private AnchorPane priceListAnchorPane;

    @FXML
    private Button returnToHomePageButton;

    @FXML
    private GridPane serviceGridPane;

    @FXML
    private TextField colorMenServiceTextField;

    @FXML
    private TextField colorMenPriceTextField;

    @FXML
    private TextField colorMenDurationTextField;

    @FXML
    private TextField colorWomenServiceTextField;

    @FXML
    private TextField colorWomenPriceTextField;

    @FXML
    private TextField colorWomenDurationTextField;

    @FXML
    private TextField cutAndStyleMenServiceTextField;

    @FXML
    private TextField cutAndStyleMenPriceTextField;

    @FXML
    private TextField cutAndStyleMenDurationTextField;

    @FXML
    private TextField cutAndStyleWomenServiceTextField;

    @FXML
    private TextField cutAndStyleWomenPriceTextField;

    @FXML
    private TextField cutAndStyleWomenDurationTextField;

    @FXML
    private TextField extrasMenServiceTextField;

    @FXML
    private TextField extrasMenPriceTextField;

    @FXML
    private TextField extrasMenDurationTextField;

    @FXML
    private TextField extrasWomenServiceTextField;

    @FXML
    private TextField extrasWomenPriceTextField;

    @FXML
    private TextField extrasWomenDurationTextField;

    @FXML
    private TextField textureAndCurlWomenServiceTextField;

    @FXML
    private TextField textureAndCurlWomenPriceTextField;

    @FXML
    private TextField textureAndCurlWomenDurationTextField;

    @FXML
    private TextField valuePackagesMenServiceTextField;

    @FXML
    private TextField valuePackagesMenPriceTextField;

    @FXML
    private TextField valuePackagesMenDurationTextField;

    @FXML
    private TextField valuePackagesWomenServiceTextField;

    @FXML
    private TextField valuePackagesWomenPriceTextField;

    @FXML
    private TextField valuePackagesWomenDurationTextField;

    public PriceListPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private void setService() {

        colorMenServiceTextField.setText(colorMen.getName());
        colorMenServiceTextField.setEditable(false);

        colorWomenServiceTextField.setText(colorWomen.getName());
        colorWomenServiceTextField.setEditable(false);

        cutAndStyleMenServiceTextField.setText(cutAndStyleMen.getName());
        cutAndStyleMenServiceTextField.setEditable(false);

        cutAndStyleWomenServiceTextField.setText(cutAndStyleWomen.getName());
        cutAndStyleWomenServiceTextField.setEditable(false);

        extrasMenServiceTextField.setText(extrasMen.getName());
        extrasMenServiceTextField.setEditable(false);

        extrasWomenServiceTextField.setText(extrasWomen.getName());
        extrasWomenServiceTextField.setEditable(false);

        textureAndCurlWomenServiceTextField.setText(textureAndCurlWomen.getName());
        textureAndCurlWomenServiceTextField.setEditable(false);

        valuePackagesMenServiceTextField.setText(valuePackagesMen.getName());
        valuePackagesMenServiceTextField.setEditable(false);

        valuePackagesWomenServiceTextField.setText(valuePackagesWomen.getName());
        valuePackagesWomenServiceTextField.setEditable(false);
    }

    private void setPrice() {
        colorMenPriceTextField.setText(colorMen.getCost() + " $ ");
        colorMenPriceTextField.setEditable(false);

        colorWomenPriceTextField.setText(colorWomen.getCost() + " $ ");
        colorWomenPriceTextField.setEditable(false);

        cutAndStyleMenPriceTextField.setText(cutAndStyleMen.getCost() + " $ ");
        cutAndStyleMenPriceTextField.setEditable(false);

        cutAndStyleWomenPriceTextField.setText(cutAndStyleWomen.getCost() + " $ ");
        cutAndStyleWomenPriceTextField.setEditable(false);

        extrasMenPriceTextField.setText(extrasMen.getCost() + " $ ");
        extrasMenPriceTextField.setEditable(false);

        extrasWomenPriceTextField.setText(extrasWomen.getCost() + " $ ");
        extrasWomenPriceTextField.setEditable(false);

        textureAndCurlWomenPriceTextField.setText(textureAndCurlWomen.getCost() + " $ ");
        textureAndCurlWomenPriceTextField.setEditable(false);

        valuePackagesMenPriceTextField.setText(valuePackagesMen.getCost() + " $ ");
        valuePackagesMenPriceTextField.setEditable(false);

        valuePackagesWomenPriceTextField.setText(valuePackagesWomen.getCost() + " $ ");
        valuePackagesWomenPriceTextField.setEditable(false);
    }

    private void setDuration() {
        colorMenDurationTextField.setText(colorMen.getMinutes() + " min ");
        colorMenDurationTextField.setEditable(false);

        colorWomenDurationTextField.setText(colorWomen.getMinutes() + " min ");
        colorWomenDurationTextField.setEditable(false);

        cutAndStyleMenDurationTextField.setText(cutAndStyleMen.getMinutes() + " min ");
        cutAndStyleMenDurationTextField.setEditable(false);

        cutAndStyleWomenDurationTextField.setText(cutAndStyleWomen.getMinutes() + " min ");
        cutAndStyleWomenDurationTextField.setEditable(false);

        extrasMenDurationTextField.setText(extrasMen.getMinutes() + " min ");
        extrasMenDurationTextField.setEditable(false);

        extrasWomenDurationTextField.setText(extrasWomen.getMinutes() + " min ");
        extrasWomenDurationTextField.setEditable(false);

        textureAndCurlWomenDurationTextField.setText(textureAndCurlWomen.getMinutes() + " min ");
        textureAndCurlWomenDurationTextField.setEditable(false);

        valuePackagesMenDurationTextField.setText(valuePackagesMen.getMinutes() + " min ");
        valuePackagesMenDurationTextField.setEditable(false);

        valuePackagesWomenDurationTextField.setText(valuePackagesWomen.getMinutes() + " min ");
        valuePackagesWomenDurationTextField.setEditable(false);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setService();
        setPrice();
        setDuration();
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");

    }
}
