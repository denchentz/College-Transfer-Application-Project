/**
 * College Class
 * 
 * @author Matt Tzeng
 * @version 2
 */

package edu.bhcc.Final;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class College {
    /**
     * Set collegeName, applicationDate, essayCheck, transcriptCheck
     * SimpleStringProperty to display in TableView, 
     * and set default value for each attribute.
     */
    private SimpleStringProperty collegeName = new SimpleStringProperty("");
    private String address = "";
    private SimpleStringProperty applicationDate = new SimpleStringProperty("");
    private double cost = 0.0;
    private String appliedSite = "";
    private ObservableList<Recommender> recommenderList = FXCollections.observableArrayList();
    private String emailAddress = "";
    private String acceptanceDate = "";
    private SimpleStringProperty essayCheck = new SimpleStringProperty("");
    private SimpleStringProperty transcriptCheck= new SimpleStringProperty("");

    /**
     * 
     * Default Constructor
     */
    public College(){}
    /**
     * 
     * @param name
     */
    public void setCollegeName(String name) {
        collegeName.set(name);
    }
    /**
     * 
     * @return collegeName
     */
    public String getCollegeName() {
        return collegeName.get();
    }
    /**
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * 
     * @param applicationDate
     */
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = new SimpleStringProperty(applicationDate);
    }
    /**
     * 
     * @return applicationDate
     */
    public String getApplicationDate() {
        return applicationDate.get();
    }
    /**
     * 
     * @param cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    /**
     * 
     * @return cost
     */
    public double getCost() {
        return cost;
    }
    /**
     * 
     * @param appliedSite
     */
    public void setAppliedSite(String appliedSite) {
        this.appliedSite = appliedSite;
    }
    /**
     * 
     * @return appliedSite
     */
    public String getAppliedSite() {
        return appliedSite;
    }
    /**
     * 
     * @param recommenderList
     */
    public void setRecommenderList(ObservableList<Recommender> recommenderList) {
        this.recommenderList = recommenderList;
    }
    /**
     * 
     * @return recommenderList
     */
    public ObservableList<Recommender> getRecommenderList() {
        return recommenderList;
    }
    /**
     * 
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * 
     * @return emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * 
     * @param acceptanceDate
     */
    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }
    /**
     * 
     * @return acceptanceDate
     */
    public String getAcceptanceDate() {
        return acceptanceDate;
    }
    /**
     * 
     * @param essayCheck
     */
    public void setEssayCheck(String essayCheck) {
        this.essayCheck = new SimpleStringProperty(essayCheck);
    }
    /**
     * 
     * @return essayCheck
     */
    public String getEssayCheck() {
        return essayCheck.get();
    }
    /**
     * 
     * @param transcriptCheck
     */
    public void setTranscriptCheck(String transcriptCheck) {
        this.transcriptCheck = new SimpleStringProperty(transcriptCheck);
    }
    /**
     * 
     * @return transcriptCheck
     */
    public String getTranscriptCheck() {
        return transcriptCheck.get();
    }

    @Override
    public String toString() {
        String text = collegeName.get() + "\n" +
                      address + "\n" +
                      applicationDate.get() + "\n" +
                      cost + "\n" +
                      appliedSite + "\n" + 
                      emailAddress + "\n" +
                      acceptanceDate + "\n" +
                      essayCheck.get() + "\n" +
                      transcriptCheck.get() + "\n" +
                      recommenderList.size() + "\n";
        if (recommenderList.size() != 0) {
            for (Recommender recommender : recommenderList) {
                text += recommender + "\n";
            }
        }
        return text;
    }

}
