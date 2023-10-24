package CampusMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {
    private static final double MAX_DORMITORY_RADIUS = 50;
    private static final int MAX_DORMITORY_LOCATION_X = (int) (800- MAX_DORMITORY_RADIUS);
    private static final int MAX_DORMITORY_LOCATION_Y = (int) (800 - MAX_DORMITORY_RADIUS);
    private static final int MAX_DORMITORY_POPULATION = 400;
    private Random random = new Random();

    // DEFININDO ATRIBUTOS DOS DORMITÓRIOS
    private int[] dormitoryPopulation = {random.nextInt(MAX_DORMITORY_POPULATION), random.nextInt(MAX_DORMITORY_POPULATION), random.nextInt(MAX_DORMITORY_POPULATION), random.nextInt(MAX_DORMITORY_POPULATION), random.nextInt(MAX_DORMITORY_POPULATION), random.nextInt(MAX_DORMITORY_POPULATION)};
    private double[] dormitoryLocationX = {random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X)};
    private double[] dormitoryLocationY = {random.nextInt(MAX_DORMITORY_LOCATION_Y), random.nextInt(MAX_DORMITORY_LOCATION_Y), random.nextInt(MAX_DORMITORY_LOCATION_Y), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X), random.nextInt(MAX_DORMITORY_LOCATION_X)};

    Circle centralPoint;
    Label centralPointLabel;

    Circle studyGroup;
    Label studyGroupLabel;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(800, 800);

        Image campusMap = new Image("https://i.imgur.com/U3j503F.jpg");

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(campusMap, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        pane.setBackground(new Background(backgroundImage));

        // CRIANDO DORMITÓRIOS
        for (int i = 0; i < dormitoryPopulation.length; i++) {
            Circle dormitoryCircle = createMovableCircle(dormitoryLocationX[i], dormitoryLocationY[i], calculateCircleRadius(dormitoryPopulation[i]));
            dormitoryCircle.setOpacity(0.5);

            Label dormitoryLabel = new Label("Dorm " + (i + 1));
            Label populationLabel = new Label(Integer.toString(dormitoryPopulation[i]));
            Slider populationSlider = createPopulationSlider(dormitoryPopulation[i]);

            VBox dormitoryBox = new VBox(5);
            dormitoryBox.setAlignment(Pos.CENTER);
            dormitoryBox.getChildren().addAll(dormitoryCircle, dormitoryLabel, populationLabel, populationSlider);
            dormitoryBox.setLayoutX(dormitoryLocationX[i] - 20);
            dormitoryBox.setLayoutY(dormitoryLocationY[i] + 10);
            pane.getChildren().add(dormitoryBox);

            if (i % 2 == 0) {
                dormitoryCircle.setFill(Color.GOLD);
            } else {
                dormitoryCircle.setFill(Color.CHOCOLATE);
            }

            dormitoryCircle.setOnMousePressed(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    dormitoryCircle.setUserData(new double[]{event.getSceneX(), event.getSceneY()});
                }
                updateCentralPoint(pane);
                updateStudyGroup(pane);
            });

            int dormitoryIndex = i;

            int finalI = i;
            dormitoryCircle.setOnMouseDragged(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    double[] previousPosition = (double[]) dormitoryCircle.getUserData();
                    double deltaX = event.getSceneX() - previousPosition[0];
                    double deltaY = event.getSceneY() - previousPosition[1];

                    dormitoryCircle.setCenterX(dormitoryCircle.getCenterX() + deltaX);
                    dormitoryCircle.setCenterY(dormitoryCircle.getCenterY() + deltaY);

                    dormitoryCircle.setUserData(new double[]{
                            event.getSceneX(), event.getSceneY()
                    });

                    dormitoryBox.setLayoutX(dormitoryCircle.getCenterX() - 20);
                    dormitoryBox.setLayoutY(dormitoryCircle.getCenterY() + 10);

                    dormitoryLocationX[finalI] = dormitoryCircle.getCenterX();
                    dormitoryLocationY[finalI] = dormitoryCircle.getCenterY();

                    updateCentralPoint(pane);
                    updateStudyGroup(pane);
                }
            });



            populationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                dormitoryPopulation[dormitoryIndex] = newValue.intValue();
                populationLabel.setText(Integer.toString(newValue.intValue()));

                double circleRadius = calculateCircleRadius(newValue.intValue());
                dormitoryCircle.setRadius(circleRadius);

                updateCentralPoint(pane);
                updateStudyGroup(pane);
            });

            // MUDAR COR DAS LETRAS
            String setStyle = "-fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 1, 0, 0)";

            dormitoryLabel.setStyle(setStyle);
            populationLabel.setStyle(setStyle);
        }
        updateCentralPoint(pane);
        updateStudyGroup(pane);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    private void updateCentralPoint(Pane pane) {
        String setStyle = "-fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 1, 0, 0)";
        double centerX = 0;
        double centerY = 0;
        double totalPopulation = 0;

        for (int i = 0; i < dormitoryPopulation.length; i++) {
            centerX += dormitoryLocationX[i] * dormitoryPopulation[i];
            centerY += dormitoryLocationY[i] * dormitoryPopulation[i];
            totalPopulation += dormitoryPopulation[i];
        }

        centerX /= totalPopulation;
        centerY /= totalPopulation;

        if (studyGroup == null) {
            studyGroup = new Circle(centerX, centerY, 10, Color.RED);
            pane.getChildren().add(studyGroup);
        } else {
            studyGroup.setCenterX(centerX);
            studyGroup.setCenterY(centerY);
        }

        if (studyGroupLabel == null) {
            studyGroupLabel = new Label("All Dorms\nDistance " + calculateDistance(centerX, centerY));
            pane.getChildren().add(studyGroupLabel);
            studyGroupLabel.setStyle(setStyle);
        } else {
            studyGroupLabel.setText("All Dorms\nDistance " + calculateDistance(centerX, centerY));
        }

        studyGroupLabel.setLayoutX(studyGroup.getCenterX() - 25);
        studyGroupLabel.setLayoutY(studyGroup.getCenterY() + 10);
    }

    private void updateStudyGroup(Pane pane) {
        String setStyle = "-fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 1, 0, 0)";
        double centerX = 0;
        double centerY = 0;
        double totalStudents = 3;

        for (int i = 1; i < 6; i+=2) {
            centerX += dormitoryLocationX[i];
            centerY += dormitoryLocationY[i];
        }

        centerX /= totalStudents;
        centerY /= totalStudents;

        if (centralPoint == null) {
            centralPoint = new Circle(centerX, centerY, 10, Color.RED);
            pane.getChildren().add(centralPoint);
        } else {
            centralPoint.setCenterX(centerX);
            centralPoint.setCenterY(centerY);
        }

        if (centralPointLabel == null) {
            centralPointLabel = new Label("Study Group\nDistance " + calculateDistance(centerX, centerY));
            pane.getChildren().add(centralPointLabel);
            centralPointLabel.setStyle(setStyle);
        } else {
            centralPointLabel.setText("Study Group\nDistance " + calculateDistance(centerX, centerY));
        }

        centralPointLabel.setLayoutX(centralPoint.getCenterX() - 25);
        centralPointLabel.setLayoutY(centralPoint.getCenterY() + 10);
    }

    public static int calculateDistance(double x, double y) {
        double deltaX = Math.abs(400 - x);
        double deltaY = Math.abs(400 - y);
        double distanceSquared = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        return (int) Math.sqrt(distanceSquared);
    }

    private Slider createPopulationSlider(int i) {
        Slider slider = new Slider(0, MAX_DORMITORY_POPULATION, i);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);
        return slider;
    }

    private double calculateCircleRadius(int i) {
        double population = (double) i / getMaxDormitoryPopulation();
        return population * MAX_DORMITORY_RADIUS;
    }

    private int getMaxDormitoryPopulation() {
        int dormitoryMaxPopulation = Integer.MIN_VALUE;
        for (int i: dormitoryPopulation) {
            dormitoryMaxPopulation = Math.max(dormitoryMaxPopulation, i);
        }
        return dormitoryMaxPopulation;
    }

    private Circle createMovableCircle(double centerX, double centerY, double radius) {
        Circle circle = new Circle(centerX, centerY, radius);
        circle.setFill(Color.BLUE);

        return circle;
    }
}
