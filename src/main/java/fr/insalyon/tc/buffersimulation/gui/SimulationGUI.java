package fr.insalyon.tc.buffersimulation.gui;

import fr.insalyon.tc.buffersimulation.Simulation;
import fr.insalyon.tc.buffersimulation.SimulationState;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by pvienne on 30/11/2016.
 */
public class SimulationGUI extends Application {

    @Override public void start(Stage stage) {

        Simulation simulation = new Simulation(null, 10, 1000, 100, 30);
        simulation.run();
        stage.setTitle("Occupation du buffer");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Occupation du buffer");

        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");

        final ArrayList<SimulationState> states = simulation.getStates();
        states.forEach(simulationState -> series.getData().add(new XYChart.Data(simulationState.getTime(), simulationState.getBufferFill())));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}