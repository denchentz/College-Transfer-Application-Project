package edu.bhcc.Final;

public class RecommenderTable {
    //private ObservableList<Recommender> recommenderList =
    //FXCollections.observableArrayList(college.getRecommenderList());
    /**        
     * collegeTable.setRowFactory(tv -> {
            TableRow<College> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (! row.isEmpty()) ) {
                    College rowData = row.getItem();
                    collegeDetails(rowData);
                }
            });
            return row ;
        }); */

                /*
        collegeTable.getSelectionModel().selectedItemProperty().addListener((
            observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if(collegeTable.getSelectionModel().getSelectedItem() != null) {
                selectedCollege = collegeTable.getSelectionModel().getSelectedItem();
            }
        });
    
        Button btEdit = new Button("Edit");
        btEdit.setPrefSize(100, 50);
        btEdit.setOnAction(e -> {if (selectedCollege != null) editCollege(selectedCollege);});
    
        Button btEdit = new Button("Edit");
        btEdit.setPrefSize(100, 20);
        if (selectedRecommender != null) {
            btEdit.setOnAction(e -> editRecommender(selectedRecommender));


        }

                HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(5, 10, 10, 5));
        buttonBox.getChildren().addAll(btNew, btEdit, btDel);
        buttonBox.setAlignment(Pos.CENTER);


        //College toString
        public String toString() {
        String text = collegeName + "\n" +
                      address + "\n" +
                      "Date of Application: " + applicationDate + "\n" +
                      "Cost: " + cost + "\n" +
                      "Applied App or Site: " + appliedSite + "\n" +
                      "Recommenders:\n";
        if (recommenderList.size() != 0) {
            for (Recommender recommender : recommenderList) {
                text += "\t" + recommender + "\n";
            }
        }
        text += "Email Address: " + emailAddress + "\n" +
                "Date of Expected Acceptance Letter: " + acceptanceDate + "\n";
        return text;
    }


     
    public void collegeDetails(College college){
        Stage stage = new Stage();
        stage.setTitle("Delete College");

        Label label = new Label(college.toString());
        label.setFont(new Font("Arial", 20));


        Button btEdit = new Button("Edit");
        btEdit.setPrefSize(100, 20);
        btEdit.setOnAction(e -> editCollege(college));
        




        Button btClose = new Button("Close");
        btClose.setPrefSize(100, 20);
        btClose.setOnAction(e -> stage.close());
        btClose.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(5, 10, 10, 5));
        buttonBox.getChildren().addAll(btEdit, btDel, btClose);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 600, 800);
        stage.setScene(scene);
        stage.show();
    }


    */
}
