package com.example.shreyas.pocketlistify.UtilityClasses;
/**
 * Created by shreyas on 8/10/2017.
 */

public class TaskItem {
    private int t_id;
    private String t_name = null;
    private String t_details = null;
    private String t_priority = null;
    private String t_dueDate = null;
    private boolean delete = false;

    public void setId(int id){
        this.t_id = id;
    }

    public int getId(){
        return t_id;
    }

    public String getName(){
        return t_name;
    }

    public void setName(String name) {
        this.t_name = name;
    }

    public String getDetails(){
        return t_details;
    }

    public void setDetails(String details) {
        this.t_details = details;
    }

    public String getPriority(){
        return t_priority;
    }

    public void setPriority(String priority) {
        this.t_priority = priority;
    }

    public String getDate(){
        return t_dueDate;
    }

    public void setDate(String date) {
        this.t_dueDate = date;
    }

    public void setForDeletion() {
        delete=true;
    }

    public boolean getForDeletion()
    {
        return delete;
    }

    public void resetToRetain() {
        delete=false;
    }

}
