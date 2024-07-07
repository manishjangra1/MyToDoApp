package com.manishjangra.mytodo;

public class TaskModel {

    boolean isChecked;
    String task;
    boolean isStriked;

    public TaskModel(){

    }

    public TaskModel(String task, boolean isChecked, boolean isStriked){
        this.task = task;
        this.isChecked = isChecked;
        this.isStriked = isStriked;
    }

    public TaskModel(String task){

        this.task = task;
    }
    public void setTask(String task){
        this.task = task;
    }

    public String getTask(){
        return task;
    }


    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked(){

        return isChecked;
    }


    public boolean isStriked(){
        return isStriked;
    }

    public void setStriked(boolean isStriked){
        this.isStriked = isStriked;
    }
}
