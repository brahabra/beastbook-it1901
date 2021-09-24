package fxui;

import core.ReadWrite;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import core.Workout;
import core.Exercise;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.beans.property.SimpleStringProperty;

public class CreateController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<Exercise> workout_table;

    @FXML
    private Text exceptionFeedback;

    @FXML
    public TableColumn<Exercise, String> exerciseName;

    @FXML
    public TableColumn<Exercise, String> repGoal;

    @FXML
    public TableColumn<Exercise, String> weight;

    @FXML
    public TableColumn<Exercise, String> sets;

    @FXML
    public TableColumn<Exercise, String> restTime;

/*    @FXML
    private Button back_button;*/

    @FXML
    private TextField exerciseNameInput;

    @FXML
    private TextField repsInput;

    @FXML
    private TextField weigthInput;

    @FXML
    private TextField setsInput;

    @FXML
    private TextField restInput;

    @FXML
    private TextField titleInput;

    @FXML
    private Button createButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button addExercise;

    private Workout workout = new Workout();

    private Exercise exercise;

    public void initialize() {
        menuBar.setVisible(false);
    }


    /**
     *
     * Sets the workout table collumns. If a workout has exercises added already (typical if loaded from file), the workout 
     * table add these exercises to the collumns/rows. 
     */
    public void setTable() {
        
        exerciseName.setCellValueFactory(new PropertyValueFactory<Exercise, String>("exerciseName"));
        repGoal.setCellValueFactory(new PropertyValueFactory<Exercise, String>("repGoal"));
        weight.setCellValueFactory(new PropertyValueFactory<Exercise, String>("weight"));
        sets.setCellValueFactory(new PropertyValueFactory<Exercise, String>("sets"));
        restTime.setCellValueFactory(new PropertyValueFactory<Exercise, String>("restTime"));
        
        workout_table.getItems().setAll(workout.getExercises());
    }

    /**
     *
     * @param row the row you want to have access to / get an Exercise object from. int row 0 is the first row,  int row 1 is the second row and so on. Mainly used for test reasons
     * @return the Exercise object on the the requested row 
     */
    public Exercise getTable(int row){
        return workout_table.getItems().get(row);
    }

    /**
     *
     * @return the workout. Mainly used for test reasons
     */
    public Workout getWorkout(){
        return workout;
    }

    /**
     *
     *  Runs when the "Add exercise" button is clicked. If all the input fields are in the correct format, a  Exercise object is made with the
     *  input fields data. The exercise object is then added to the workout object and its list over exercises. The workout table is then
     *  "reloaded" with the new exercise added to the list.
     *  
     *  If the input fields are not in the correct format, the method catches the Exepction. A text with red color appears on the screen with 
     *  a message to the user saying that the exercise could not be added (because of wrong inputs). The text disappears when a exercise is added successfully. 
     */
    @FXML
    void addExercise() {
        
        createButton.setDisable(false);

        workout.setName(titleInput.getText());
        try{
            exercise = new Exercise(exerciseNameInput.getText(), 
            Integer.valueOf(repsInput.getText()),
             Double.valueOf(weigthInput.getText()), 
             Integer.valueOf(setsInput.getText()), 
             Integer.valueOf(restInput.getText()));

            exerciseName.setCellValueFactory(c -> new SimpleStringProperty(new String(exercise.getExerciseName())));
            repGoal.setCellValueFactory(c -> new SimpleStringProperty(new String(String.valueOf(exercise.getRepGoal()))));
            weight.setCellValueFactory(c -> new SimpleStringProperty(new String(String.valueOf(exercise.getWeight()))));
            sets.setCellValueFactory(c -> new SimpleStringProperty(new String(String.valueOf(exercise.getSets()))));
            restTime.setCellValueFactory(c -> new SimpleStringProperty(new String(String.valueOf(exercise.getRestTime()))));

            workout.addExercise(exercise);
            workout_table.getItems().add(exercise);   
            exceptionFeedback.setText("");

        }

        //TODO (release 2.0) Make more spesific feedback
        catch (Exception e) {
            exceptionFeedback.setText("Could not add exercise. Wrong input format");
        }
    }

    /**
     * Loads a workout using title input in GUI
     * If no title input is given, an error message is displayed in GUI
     * If no file found with given title, an error message is displayed in GUI
     *
     * @param event When Load Workout button is clicked in GUI, loadWorkout() is fired
     */
    @FXML
    void loadWorkout(ActionEvent event) {
        if (titleInput.getText().equals("") || titleInput.getText() == null) {
            exceptionFeedback.setText("Missing Title!");
            return;
        }
        String filename = titleInput.getText();
        try {
            workout = new Workout();
            workout.loadWorkout(filename);
            setTable();
            exceptionFeedback.setText("");
        } catch (Exception e) {
            System.err.println(e);
            exceptionFeedback.setText("Workout not found!");
        }

    }

    /**
     * Creates a workout and saves it as a file with input given in GUI
     * If no title input is given, an error message is displayed in GUI
     * If an error occurs in saveWorkout, an error message is displayed in GUI
     *
     * @param event When Create Workout button is clicked in GUI, createWorkout() is fired
     */
    @FXML
    void createWorkout(ActionEvent event) {
        if(titleInput.getText() == null || titleInput.getText().equals("")){
            System.err.println("Input title is empty, please enter name to workout");
            exceptionFeedback.setText("Missing Title!");
        }
        else {
            try {
                workout.saveWorkout();
                exceptionFeedback.setText("");
            } catch (Exception e) {
                System.err.println(e);
                exceptionFeedback.setText("Save Workout failed!");
            }
        }
    }

}