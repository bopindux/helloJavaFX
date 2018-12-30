package de.bopindux.helloJavaFx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App extends Application{

    private static Logger log = LogManager.getLogger(App.class);
    private final TextArea logText = new TextArea();

    public App(){
        TextAreaAppender.setTextArea(logText);
    }

    public LineChart createLinePlot(){
        NumberAxis xAxis = new NumberAxis("x/PI",0, 2, 1/8f);
        NumberAxis yAxis = new NumberAxis("y",-1,1,0.2);
        LineChart chart = new LineChart(xAxis,yAxis);
        XYChart.Series seriesSin = new XYChart.Series();
        seriesSin.setName("sin(x)");
        for (double x=0f; x<=2; x=x+1/16f){
            seriesSin.getData().add(new XYChart.Data(x,Math.sin(x*Math.PI)));
        }
        chart.getData().add(seriesSin);
        XYChart.Series seriesCos = new XYChart.Series();
        seriesCos.setName("cos(x)");
        for (double x=0f; x<=2; x=x+1/16f){
            seriesCos.getData().add(new XYChart.Data(x,Math.cos(x*Math.PI)));
        }
        chart.getData().add(seriesCos);
        return chart;
    }

    @Override
    public void start(Stage stage){

        log.trace("App.start");
        BorderPane border = new BorderPane();

        HBox topHBox = new HBox();
        topHBox.setPadding(new Insets(15, 12, 15, 12));
        topHBox.setSpacing(10);
        topHBox.setStyle("-fx-background-color: #4080A0;");
        topHBox.setAlignment(Pos.CENTER);

        Label label = new Label("Hello JavaFX World!");
        Button buttonClick = new Button(">> Click <<");
        buttonClick.setOnAction(e -> log.info("Hello JavaFX 8"));
        Button buttonClose = new Button("Close");
        buttonClose.setOnAction(e -> stage.close());
        topHBox.getChildren().addAll(label,buttonClick,buttonClose);
        border.setTop(topHBox);

        logText.setStyle("-fx-control-inner-background: #204060;");
        border.setBottom(logText);

        LineChart chart = createLinePlot();
        border.setCenter(chart);

        stage.setScene(new Scene(border));
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setTitle("JavaFX 8 app");

        stage.show();
    }

}