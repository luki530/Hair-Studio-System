package pl.edu.agh.userInterface.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.objects.services.Services;
import pl.edu.agh.objects.services.implemetations.*;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowServicesPageController implements Initializable {

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
    private String name = "Show Services Page";

    @FXML
    private Button returnToHomePageButton;

    @FXML
    private Accordion menServicesAccordion;

    @FXML
    private Accordion womenServicesAccordion;

    @FXML
    private TitledPane colorTitledPaneMen;

    @FXML
    private AnchorPane colorMenAnchorPane;

    @FXML
    private TitledPane cutAndStyleTitledPaneMen;

    @FXML
    private AnchorPane cutAndStyleMenAnchorPane;

    @FXML
    private TitledPane extrasTitledPaneMen;

    @FXML
    private AnchorPane extrasMenAnchorPane;

    @FXML
    private TitledPane valuePackagesTitledPaneMen;

    @FXML
    private AnchorPane valuePackagesMenAnchorPane;

    @FXML
    private TitledPane colorTitledPaneWomen;

    @FXML
    private AnchorPane colorWomenAnchorPane;

    @FXML
    private TitledPane cutAndStyleTitledPaneWomen;

    @FXML
    private AnchorPane cutAndStyleWomenAnchorPane;

    @FXML
    private TitledPane extrasTitledPaneWomen;

    @FXML
    private TitledPane textureAndCurlTitledPaneWomen;

    @FXML
    private AnchorPane textureAndCurlWomenAnchorPane;

    @FXML
    private Text tacText;

    @FXML
    private TitledPane valuePackagesTitledPaneWomen = new TitledPane();

    public ShowServicesPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private void setDesrciptions() {
        ScrollPane scroll = new ScrollPane();
        TextArea textArea;

        colorTitledPaneMen.setContent(scroll);
        textArea = new TextArea(colorMen.getDescription());
        textArea.setEditable(false);
        colorTitledPaneMen.setContent(textArea);

        cutAndStyleTitledPaneMen.setContent(scroll);
        textArea = new TextArea(cutAndStyleMen.getDescription());
        textArea.setEditable(false);
        cutAndStyleTitledPaneMen.setContent(textArea);

        extrasTitledPaneMen.setContent(scroll);
        textArea = new TextArea(extrasMen.getDescription());
        textArea.setEditable(false);
        extrasTitledPaneMen.setContent(textArea);

        valuePackagesTitledPaneMen.setContent(scroll);
        textArea = new TextArea(valuePackagesMen.getDescription());
        textArea.setEditable(false);
        valuePackagesTitledPaneMen.setContent(textArea);

        colorTitledPaneWomen.setContent(scroll);
        textArea = new TextArea(colorWomen.getDescription());
        textArea.setEditable(false);
        colorTitledPaneWomen.setContent(textArea);

        cutAndStyleTitledPaneWomen.setContent(scroll);
        textArea = new TextArea(cutAndStyleWomen.getDescription());
        textArea.setEditable(false);
        cutAndStyleTitledPaneWomen.setContent(textArea);

        extrasTitledPaneWomen.setContent(scroll);
        textArea = new TextArea(extrasWomen.getDescription());
        textArea.setEditable(false);
        extrasTitledPaneWomen.setContent(textArea);

        textureAndCurlTitledPaneWomen.setContent(scroll);
        textArea = new TextArea(textureAndCurlWomen.getDescription());
        textArea.setEditable(false);
        textureAndCurlTitledPaneWomen.setContent(textArea);

        valuePackagesTitledPaneWomen.setContent(scroll);
        textArea = new TextArea(valuePackagesWomen.getDescription());
        textArea.setEditable(false);
        valuePackagesTitledPaneWomen.setContent(textArea);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDesrciptions();
    }
}