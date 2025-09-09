/**
 * Recommender Class
 * 
 * @author Matt Tzeng
 */
package edu.bhcc.Final;

import javafx.beans.property.SimpleStringProperty;

public class Recommender {
    /**
     * Set all the attribute SimpleStringProperty to display
     * in TableView.
     */
    private SimpleStringProperty recommenderName = new SimpleStringProperty();
    private SimpleStringProperty requestedDate = new SimpleStringProperty();
    private SimpleStringProperty requiredDate = new SimpleStringProperty();

    /**
     * Constructor
     * 
     * @param name
     * @param requestedDate
     * @param requiredDate
     */
    public Recommender(String name, String requestedDate, String requiredDate) {
        this.recommenderName = new SimpleStringProperty(name);
        this.requestedDate = new SimpleStringProperty(requestedDate);
        this.requiredDate = new SimpleStringProperty(requiredDate);
    }
    /**
     * 
     * @param name
     */
    public void setRecommenderName(String name) {
        recommenderName.set(name);
    }
    /**
     * 
     * @return recommenderName
     */
    public String getRecommenderName() {
        return recommenderName.get();
    }
    /**
     * 
     * @param date
     */
    public void setRequestedDate(String date) {
        requestedDate.set(date);
    }
    /**
     * 
     * @return requestedDate
     */
    public String getRequestedDate() {
        return requestedDate.get();
    }
    /**
     * 
     * @param date
     */
    public void setRequiredDate(String date) {
        requiredDate.set(date);
    }
    /**
     * 
     * @return date
     */
    public String getRequiredDate() {
        return requiredDate.get();
    }

    @Override
    public String toString(){
        return recommenderName.get() + "\n" + requestedDate.get() + "\n" + requiredDate.get();
    }
}
