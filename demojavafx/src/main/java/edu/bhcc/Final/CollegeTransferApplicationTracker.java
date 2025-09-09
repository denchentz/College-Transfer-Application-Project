/**
 * Main Program
 * 
 * @author Matt Tzeng
 * @version 2 
 */

package edu.bhcc.Final;


import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class CollegeTransferApplicationTracker extends Application {
    /**
     * List to contain College objects and display to collegeTable
     */
    private ObservableList<College> collegeList = FXCollections.observableArrayList();

    /**
     * Open the primary stage for the program. The primary stage contains the TableView of
     * colleges, displaying collegeName, applicationDate, essayCheck,
     * transcriptCheck in 4 TableColumns, and button New, Import, and Save.
     * 
     */
    public void start(Stage primaryStage) {

        Label label = new Label("College Tracker Table");
        label.setFont(new Font("Arial", 20));
        /**
         * Create and define the TableView and its TableColumns.
         */
        TableView<College> collegeTable = new TableView<>();
        TableColumn<College, String> collegeCol = new TableColumn<>("College Name");
        collegeCol.setMinWidth(300);
        collegeCol.setCellValueFactory(new PropertyValueFactory<>("collegeName"));
        TableColumn<College, String> applyDateCol = new TableColumn<>("Date of Application");
        applyDateCol.setMinWidth(200);
        applyDateCol.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));
        TableColumn<College, String> essayCol = new TableColumn<>("Essay");
        essayCol.setMinWidth(150);
        essayCol.setCellValueFactory(new PropertyValueFactory<>("essayCheck"));
        TableColumn<College, String> transcriptCol = new TableColumn<>("Transcript");
        transcriptCol.setMinWidth(150);
        transcriptCol.setCellValueFactory(new PropertyValueFactory<>("transcriptCheck"));

        /**
         * Fill the table with objects from collegeList and add the tableColumns
         * into the table.
         */
        collegeTable.setItems(collegeList);
        collegeTable.getColumns().addAll(collegeCol, applyDateCol, essayCol, transcriptCol);

        /**
         * Enable mouse clicked ActionEvent on the clicked college row from the
         * table. Clicked on the table row to open window for editing the
         * college of the row.
         */ 
        collegeTable.setOnMouseClicked(e -> {
            if(collegeTable.getSelectionModel().getSelectedItem() != null) {
                College selectedCollege = collegeTable.getSelectionModel().getSelectedItem();
                editCollege(selectedCollege);
            }
        });

        /**
         * Button New: Clicked to open a window for creating new college
         * 
         */
        Button btNewCollege = new Button("New");
        btNewCollege.setPrefSize(100, 50);
        btNewCollege.setOnAction(e -> newCollege());
        /**
         * Button Import: Clicked to read file and add the file's college to collegeList
         * 
         */
        Button btImport = new Button("Import");
        btImport.setPrefSize(100, 50);
        btImport.setOnAction(e -> load());
        /**
         * Button Save: Clicked to save colleges in collegeList to a file
         * 
         */
        Button btSave = new Button("Save");
        btSave.setPrefSize(100, 50);
        btSave.setOnAction(e -> save());

        /**
         * HBox that contains the buttons New, Import, and Save in primary stage
         * 
         */
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(5, 10, 10, 5));
        buttonBox.getChildren().addAll(btNewCollege, btImport, btSave);
        buttonBox.setAlignment(Pos.CENTER);

        /**
         * VBox tableMenu that contains label, table, and buttonBox
         * 
         */
        VBox tableMenu = new VBox();
        tableMenu.setSpacing(5);
        tableMenu.setPadding(new Insets(10, 10, 10, 10));
        tableMenu.getChildren().addAll(label, collegeTable, buttonBox);
        tableMenu.setAlignment(Pos.CENTER);

        /**
         * Display tableMenu on scene
         * 
         */
        Scene scene = new Scene(tableMenu, 900, 600);
        primaryStage.setTitle("College Transfer Application Tracker");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    /**
     * Button New in primary stage calls this method. Open a new window for adding new college.
     * 
     */
    public void newCollege(){
        College college = new College();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Scene scene = new Scene(gridPane, 700, 600);
        Stage stage = new Stage();
        stage.setTitle("Add New College");
        stage.setScene(scene);

        /**
         * The TextFields for the user to input the value for the attributes of the college
         */
        TextField collegeNameField = new TextField(); // college name
        TextField addressField = new TextField(); // address
        TextField applicationDateField = new TextField(); // date of application
        TextField costField = new TextField(); // cost
        TextField appliedSiteField = new TextField(); // where you applied (common app, or other sites)
        TextField emailAddressField = new TextField(); // email address
        TextField acceptanceDateField = new TextField(); // date of expected acceptance letter

        /**
         * HBox with RadioButton to ask the user whether the essay is written.
         * 
         */
        HBox hasEssayBox = new HBox(20);
        RadioButton rbYes1 = new RadioButton("Yes"); 
        RadioButton rbNo1 = new RadioButton("No"); 
        hasEssayBox.getChildren().addAll(rbYes1, rbNo1);
        ToggleGroup group1 = new ToggleGroup(); 
        rbYes1.setToggleGroup(group1); 
        rbNo1.setToggleGroup(group1); 

        /**
         * HBox with RadioButton to ask the user whether the essay is written.
         * 
         */
        HBox hasTranscriptBox = new HBox(20);
        RadioButton rbYes2 = new RadioButton("Yes"); 
        RadioButton rbNo2 = new RadioButton("No"); 
        hasTranscriptBox.getChildren().addAll(rbYes2, rbNo2);
        ToggleGroup group2 = new ToggleGroup(); 
        rbYes2.setToggleGroup(group2); 
        rbNo2.setToggleGroup(group2);

        /**
         * Button Recommender to open the window for recommender table that display
         * the recommenders of the college
         */ 
        Button btRecommender = new Button("Recommender");
        btRecommender.setOnAction(e -> tableRecommender(college));
        
        /**
         * Button Add to get the value in the textfield and radioButton HBoxes,
         * and create the college
         */
        Button btAdd = new Button("Add");
        btAdd.setOnAction(e -> {
            college.setCollegeName(collegeNameField.getText());
            college.setAddress(addressField.getText());
            college.setApplicationDate(applicationDateField.getText());
            college.setCost(Double.parseDouble(costField.getText()));
            college.setAppliedSite(appliedSiteField.getText());
            college.setEmailAddress(emailAddressField.getText());
            college.setAcceptanceDate(acceptanceDateField.getText());
            String essay;
            if (rbYes1.isSelected()) {
                essay = "Checked";
            }
            else {
                essay = "Not Ready";
            }
            college.setEssayCheck(essay);
            String transcript;
            if (rbYes2.isSelected()) {
                transcript = "Checked";
            }
            else {
                transcript = "Not Ready";
            }
            college.setTranscriptCheck(transcript);
            collegeList.add(college);
            stage.close();
        });
        
        /**
         * Button Cancel to close the window
         * 
         */
        Button btCancel = new Button("Cancel");
        btCancel.setPrefSize(100, 20);
        btCancel.setOnAction(e -> stage.close());

        /**
         * HBox that contains the buttons Add, and Cancel
         * 
         */
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.getChildren().addAll(btAdd, btCancel);

        /**
         * Place the textFields, button Recommender, and button HBoxes in a gridPane
         * 
         */
        gridPane.add(new Label("College Name:"), 0, 0);
        gridPane.add(collegeNameField, 1, 0);
        gridPane.add(new Label("Address:"), 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(new Label("Date of Application:"), 0, 2);
        gridPane.add(applicationDateField, 1, 2);
        gridPane.add(new Label("Cost:"), 0, 3);
        gridPane.add(costField, 1, 3);
        gridPane.add(new Label("Applied App or Site:"), 0, 4);
        gridPane.add(appliedSiteField, 1, 4);
        gridPane.add(new Label("Recommenders:"), 0, 5);
        gridPane.add(btRecommender, 1, 5);
        gridPane.add(new Label("Email Address:"), 0, 6);
        gridPane.add(emailAddressField, 1, 6);
        gridPane.add(new Label("Date of Excepted Acceptance Letter:"), 0, 7);
        gridPane.add(acceptanceDateField, 1, 7);
        gridPane.add(new Label("Have you written your essay?"), 0, 8);
        gridPane.add(hasEssayBox, 1, 8);
        gridPane.add(new Label("Have you submitted your transcript?"), 0, 9);
        gridPane.add(hasTranscriptBox, 1, 9);
        gridPane.add(buttonBox, 1, 10);
        
        gridPane.setAlignment(Pos.CENTER);
        stage.show(); 
    }

    /**
     * Clicked on the notNull row of the table in primary stage to calls this method. 
     * Open the window for editing attribute of the college.
     * 
     * @param college  selectedCollege from the table in primary stage
     */
    public void editCollege(College college){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Scene scene = new Scene(gridPane, 700, 600);
        Stage stage = new Stage();
        stage.setTitle("Edit College");
        stage.setScene(scene);

        /**
         * The TextFields for the user to change the value for the attributes of
         * the college, the original value would be set in TextFields before the
         * user do any changes 
         */
        TextField collegeNameField = new TextField();
        collegeNameField.setText(college.getCollegeName());
        TextField addressField = new TextField();
        addressField.setText(college.getAddress());
        TextField applicationDateField = new TextField();
        applicationDateField.setText(college.getApplicationDate());
        TextField costField = new TextField();
        costField.setText(Double.toString(college.getCost()));
        TextField appliedSiteField = new TextField();
        appliedSiteField.setText(college.getAppliedSite());
        TextField emailAddressField = new TextField();
        emailAddressField.setText(college.getEmailAddress());
        TextField acceptanceDateField = new TextField();
        acceptanceDateField.setText(college.getAcceptanceDate());

        /**
         * HBox with RadioButton to ask the user whether the essay is written.
         * 
         */
        HBox hasEssayBox = new HBox(20);
        RadioButton rbYes1 = new RadioButton("Yes"); 
        RadioButton rbNo1 = new RadioButton("No"); 
        hasEssayBox.getChildren().addAll(rbYes1, rbNo1);
        ToggleGroup group1 = new ToggleGroup(); 
        rbYes1.setToggleGroup(group1); 
        rbNo1.setToggleGroup(group1); 

        /**
         * HBox with RadioButton to ask the user whether the transcript is submitted
         * 
         */
        HBox hasTranscriptBox = new HBox(20);
        RadioButton rbYes2 = new RadioButton("Yes"); 
        RadioButton rbNo2 = new RadioButton("No"); 
        hasTranscriptBox.getChildren().addAll(rbYes2, rbNo2);
        ToggleGroup group2 = new ToggleGroup(); 
        rbYes2.setToggleGroup(group2); 
        rbNo2.setToggleGroup(group2);

        /**
         * Button Recommender to open the window for recommender table that display
         * the recommenders of the college
         */ 
        Button btRecommender = new Button("Recommender");
        btRecommender.setOnAction(e -> tableRecommender(college));
        
        /**
         * Button Update to get the value in the textfield and radioButton HBoxes,
         * and set the new value for this college
         */
        Button btUpdate = new Button("Update");
        btUpdate.setPrefSize(100, 20);
        btUpdate.setOnAction(e -> {
            college.setCollegeName(collegeNameField.getText());
            college.setAddress(addressField.getText());
            college.setApplicationDate(applicationDateField.getText());
            college.setCost(Double.parseDouble(costField.getText()));
            college.setAppliedSite(appliedSiteField.getText());
            college.setEmailAddress(emailAddressField.getText());
            college.setAcceptanceDate(acceptanceDateField.getText());
            String essay;
            if (rbYes1.isSelected()) {
                essay = "Checked";
            }
            else {
                essay = "Not Ready";
            }
            college.setEssayCheck(essay);
            String transcript;
            if (rbYes2.isSelected()) {
                transcript = "Checked";
            }
            else {
                transcript = "Not Ready";
            }
            college.setTranscriptCheck(transcript);
            collegeList.remove(college);
            collegeList.add(college);
            stage.close();
        });

        /**
         * Button Delete to remove this college from collegeList. A confirmed
         * window would pop up and ask the user whether be sure to delete the college.
         */
        Button btDel = new Button("Delete");
        btDel.setPrefSize(100, 20);
        btDel.setOnAction(e -> deleteCollege(college));

        /**
         * Button Cancel to close the window
         * 
         */
        Button btCancel = new Button("Cancel");
        btCancel.setPrefSize(100, 20);
        btCancel.setOnAction(e -> stage.close());

        /**
         * HBox that contains the buttons Update, Delete and Cancel
         * 
         */
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.getChildren().addAll(btUpdate,btDel, btCancel);

        /**
         * Place the textFields, button Recommender, and button HBoxes in a gridPane
         * 
         */
        gridPane.add(new Label("College Name:"), 0, 0);
        gridPane.add(collegeNameField, 1, 0);
        gridPane.add(new Label("Address:"), 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(new Label("Date of Application:"), 0, 2);
        gridPane.add(applicationDateField, 1, 2);
        gridPane.add(new Label("Cost:"), 0, 3);
        gridPane.add(costField, 1, 3);
        gridPane.add(new Label("Applied App or Site:"), 0, 4);
        gridPane.add(appliedSiteField, 1, 4);
        gridPane.add(new Label("Recommenders:"), 0, 5);
        gridPane.add(btRecommender, 1, 5);
        gridPane.add(new Label("Email Address:"), 0, 6);
        gridPane.add(emailAddressField, 1, 6);
        gridPane.add(new Label("Date of Excepted Acceptance Letter:"), 0, 7);
        gridPane.add(acceptanceDateField, 1, 7);
        gridPane.add(new Label("Have you written your essay?"), 0, 8);
        gridPane.add(hasEssayBox, 1, 8);
        gridPane.add(new Label("Have you submitted your transcript?"), 0, 9);
        gridPane.add(hasTranscriptBox, 1, 9);
        gridPane.add(buttonBox, 1, 10);
        
        gridPane.setAlignment(Pos.CENTER);
        stage.show(); 
    }

    /**
     * Button Delete in editCollege() window calls this method. A confirmed
     * window that pops up and ask the user whether be sure to delete the college.
     * 
     * @param college  selectedCollege from the table in primary stage and pass
     *                 from editCollege(College college)
     */
    public void deleteCollege(College college){
        Stage stage = new Stage();
        stage.setTitle("Delete College");

        Label label = new Label("Are you sure to delete this College?");

        /**
         * Button Yes to confirm deletion
         * 
         */
        Button btYes = new Button("Yes");
        btYes.setPrefSize(100, 20);
        btYes.setOnAction(e -> {
            collegeList.remove(college);
            stage.close();
        });

        /**
         * Button No to refuse the deletion
         * 
         */
        Button btNo = new Button("Cancel");
        btNo.setPrefSize(100, 20);
        btNo.setOnAction(e -> stage.close());

        /**
         * HBox that contains the buttons Yes and No
         * 
         */
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.getChildren().addAll(btYes, btNo);

        /**
         * Vbox that contains label, and buttonBox
         * 
         */
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        /**
         * Display vbox on scene
         *  
         */
        Scene scene = new Scene(vbox, 300, 75);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The method calls by button Recommender in newCollege() or
     * editCollege(College college). A window pops up and show a tableView of
     * recommenders and button Add. 
     * 
     * @param college the new created college from newCollege() 
     *                OR selectedCollege from the table in primary stage and
     *                pass from editCollege(College college)
     */
    public void tableRecommender(College college){        
        Label label = new Label("Recommender Table");
        label.setFont(new Font("Arial", 20));
        /**
         * Create and define the TableView and its TableColumns.
         */
        TableView<Recommender> recommenderTable = new TableView<>();
        TableColumn<Recommender, String> recommenderCol = new TableColumn<>("Recommender Name");
        recommenderCol.setMinWidth(200);
        recommenderCol.setCellValueFactory(new PropertyValueFactory<>("recommenderName"));
        TableColumn<Recommender, String> requestedDateCol = new TableColumn<>("Date Recommendation Requested");
        requestedDateCol.setMinWidth(300);
        requestedDateCol.setCellValueFactory(new PropertyValueFactory<>("requestedDate"));
        TableColumn<Recommender, String> requiredDateCol = new TableColumn<>("Date Recommendation Required");
        requiredDateCol.setMinWidth(300);
        requiredDateCol.setCellValueFactory(new PropertyValueFactory<>("requiredDate"));
        
        /**
         * Fill the table with objects from recommenderList and add the tableColumns
         * into the table.
         */
        recommenderTable.setItems(college.getRecommenderList());
        recommenderTable.getColumns().addAll(recommenderCol, requestedDateCol, requiredDateCol);

        
        /**
         * Enable mouse clicked ActionEvent on the clicked college row from the
         * table. Clicked on the table row to open window for editing the
         * recommender of the row.
         */ 
        recommenderTable.setOnMouseClicked(e -> {
            if(recommenderTable.getSelectionModel().getSelectedItem() != null) {
                Recommender selectedRecommender = recommenderTable.getSelectionModel().getSelectedItem();
                editRecommender(college, selectedRecommender);
            }
        });

        /**
         * Button New: Clicked to open a window for creating new recommender
         * 
         */
        Button btNew = new Button("New");
        btNew.setPrefSize(100, 20);
        btNew.setOnAction(e -> newRecommender(college));
        /**
         * VBox tableMenu that contains label, table, and buttonBox
         * 
         */
        VBox tableMenu = new VBox();
        tableMenu.setSpacing(5);
        tableMenu.setPadding(new Insets(10, 10, 10, 10));
        tableMenu.getChildren().addAll(label, recommenderTable, btNew);
        tableMenu.setAlignment(Pos.CENTER);
        /**
         * Display tableMenu on scene
         * 
         */
        Scene scene = new Scene(tableMenu, 850, 450);
        Stage stage = new Stage();
        stage.setTitle("Recommender Table");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button New in recommender table window  calls this method. Open a new window for adding new recommender.
     * 
     */
    public void newRecommender(College college){
        Stage stage = new Stage();
        stage.setTitle("New Recommender");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        /**
         * The TextFields for the user to input the value for the attributes of the recommender
         */
        TextField nameField = new TextField(); 
        TextField requestDateField = new TextField(); 
        TextField requiredDateField = new TextField(); 

        /**
         * Button Add to get the value in the textfield and radioButton HBoxes,
         * and create the recommender
         */
        Button btAdd = new Button("Add");
        btAdd.setPrefSize(100, 20);
        btAdd.setOnAction(e -> {
            Recommender recommender = new Recommender(nameField.getText(), requestDateField.getText(), requiredDateField.getText());
            college.getRecommenderList().add(recommender);
            stage.close();
        });

        /**
         * Button Cancel to close the window
         * 
         */
        Button btCancel = new Button("Cancel");
        btCancel.setPrefSize(100, 20);
        btCancel.setOnAction(e -> stage.close());

        /**
         * HBox that contains the buttons Add, and Cancel
         * 
         */
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.getChildren().addAll(btAdd, btCancel);

        /**
         * Place the textFields, button Recommender, and button HBoxes in a gridPane
         * 
         */
        gridPane.add(new Label("Recommender Name:"), 0 ,0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Date Recommendation Requested:"), 0, 1);
        gridPane.add(requestDateField, 1, 1);
        gridPane.add(new Label("Date Recommendation Required:"), 0, 2);
        gridPane.add(requiredDateField, 1, 2);
        gridPane.add(buttonBox, 1, 3);

        gridPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane, 500, 200);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Clicked on the notNull row of Recommender table to calls this method. 
     * Open the window for editing attribute of the recommender.
     * 
     * @param college     pass college to make changes in college's recommenderList
     * @param recommender selectedRecommender from the table in recommender
     *                    table window
     * @param showingList pass recommenderList from Recommender table window to
     *                    make changes in TableView recommenderTable
     */
    public void editRecommender(College college, Recommender recommender){
        Stage stage = new Stage();
        stage.setTitle("Edit Recommender");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        /**
         * The TextFields for the user to change the value for the attributes of
         * the college, the original value would be set in TextFields before the
         * user do any changes 
         */
        TextField nameField = new TextField(); // recommender name(s)
        nameField.setText(recommender.getRecommenderName());
        TextField requestDateField = new TextField(); // date recommendation requested
        requestDateField.setText(recommender.getRequestedDate());
        TextField requiredDateField = new TextField(); // date recommendation required (room for more than one recommendation (person recommending) if necessary)
        requiredDateField.setText(recommender.getRequiredDate());

        /**
         * Button Update to get the value in the textfield,
         * and set the new value for this recommender
         */
        Button btUpdate = new Button("Update");
        btUpdate.setOnAction(e -> {
            recommender.setRecommenderName(nameField.getText());
            recommender.setRequestedDate(requestDateField.getText());
            recommender.setRequiredDate(requiredDateField.getText());
            college.getRecommenderList().remove(recommender);
            college.getRecommenderList().add(recommender);
            stage.close();
        });
        /**
         * Button Delete to remove this recommender from recommenderList. A confirmed
         * window would pop up and ask the user whether be sure to delete the recommender.
         */
        Button btDel = new Button("Delete");
        btDel.setPrefSize(100, 20);
        btDel.setOnAction(e -> deleteRecommender(college, recommender));

        /**
         * Button Close to close the window
         * 
         */
        Button btClose = new Button("Close");
        btClose.setPrefSize(100, 20);
        btClose.setOnAction(e -> stage.close());
        btClose.setAlignment(Pos.CENTER);

        /**
         * HBox that contains the buttons Update, Delete and Close
         * 
         */
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(5, 10, 10, 5));
        buttonBox.getChildren().addAll(btUpdate, btDel, btClose);
        buttonBox.setAlignment(Pos.CENTER);

        /**
         * Place the textFields, and button HBoxes in a gridPane
         * 
         */
        gridPane.add(new Label("Recommender Name:"), 0 ,0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(new Label("Date Recommendation Requested:"), 0, 1);
        gridPane.add(requestDateField, 1, 1);
        gridPane.add(new Label("Date Recommendation Required:"), 0, 2);
        gridPane.add(requiredDateField, 1, 2);
        gridPane.add(buttonBox, 1, 3);

        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 600, 300);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Button Delete in editRecommender() window calls this method. A confirmed
     * window that pops up and ask the user whether be sure to delete the recommender.
     * 
     * @param college     pass college to make changes in college's recommenderList
     * @param recommender selectedRecommender from the table in recommender
     *                    table window
     * @param showingList pass recommenderList from Recommender table window to
     *                    make changes in TableView recommenderTable
     */
    public void deleteRecommender(College college, Recommender recommender){
        Stage stage = new Stage();
        stage.setTitle("Delete Recommender");

        Label label = new Label("Are you sure to delete this recommender?");

        /**
         * Button Yes to confirm deletion
         * 
         */
        Button btYes = new Button("Yes");
        btYes.setPrefSize(100, 20);
        btYes.setOnAction(e -> {
            college.getRecommenderList().remove(recommender);
            stage.close();
        });
        
        /**
         * Button No to refuse the deletion
         * 
         */
        Button btNo = new Button("Cancel");
        btNo.setPrefSize(100, 20);
        btNo.setOnAction(e -> stage.close());

        /**
         * HBox that contains the buttons Yes and No
         * 
         */
        HBox buttonBox = new HBox(20);
        buttonBox.setPadding(new Insets(5, 5, 5, 5));
        buttonBox.getChildren().addAll(btYes, btNo);

        /**
         * Vbox that contains label, and buttonBox
         * 
         */
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        /**
         * Display vbox on scene
         *  
         */
        Scene scene = new Scene(vbox, 300, 75);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Button Save in primary stage calls this method. Write the colleges in
     * collegeList to a file.
     */
    public void save() {
        String filename = "MattTzeng.txt";
        java.io.File file = new java.io.File(filename);
        // write to file 
        try{
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            if (collegeList.size() != 0) {
                output.println(collegeList.size());
                for (int i = 0; i < collegeList.size(); i++){
                    output.print(collegeList.get(i).toString());
                }
            }
            output.close();
        }
        catch (Exception ex) {
            System.out.println("Write file fails.");
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Button Import in primary stage calls this method. Read the colleges in the
     * file and add them into collegeList. 
     */
    public void load() {
        String filename = "MattTzeng.txt";
        java.io.File file = new java.io.File(filename);

        try{
            Scanner input = new Scanner(file);
            while (input.hasNext()){
                int numCollege = Integer.parseInt(input.nextLine());
                for (int i = 0; i < numCollege; i++){
                    College college = new College();
                    college.setCollegeName(input.nextLine());
                    college.setAddress(input.nextLine());
                    college.setApplicationDate(input.nextLine());
                    college.setCost(Double.parseDouble(input.nextLine()));
                    college.setAppliedSite(input.nextLine());
                    college.setEmailAddress(input.nextLine());
                    college.setAcceptanceDate(input.nextLine());
                    college.setEssayCheck(input.nextLine());
                    college.setTranscriptCheck(input.nextLine());

                    int numRecommender = Integer.parseInt(input.nextLine());
                    for (int j = 0; j < numRecommender; j++) {
                        Recommender recommender = new Recommender(input.nextLine(), input.nextLine(), input.nextLine());
                        college.getRecommenderList().add(recommender);
                    }

                    collegeList.add(college);
                }
            }
            input.close();
        }
        catch (Exception ex) {
            System.out.println("Read file fails.");
            ex.printStackTrace(System.out);
        }
    }
    /**
     * Main method to run the program
     * 
     * @param args
     */    
    public static void main(String[] args){
        Application.launch(args);
    }
}
