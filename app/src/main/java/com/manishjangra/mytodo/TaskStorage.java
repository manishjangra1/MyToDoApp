package com.manishjangra.mytodo;

import static java.lang.Character.getType;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TaskStorage extends MainActivity {

    String task;
    boolean isChecked;
    boolean isStriked;
//    public static final String PREFS_NAME = "task_prefs";
//    public static final String TASK_LIST_KEY = "task_list";
//
//    public static void saveTasks(Context context, ArrayList<TaskModel> tasksList){
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(tasksList);
//        editor.putString(TASK_LIST_KEY, json);
//        editor.apply();
//    }
//    public static ArrayList<TaskModel> loadTasks(Context context){
//        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString(TASK_LIST_KEY, null);
//        Type type = new TypeToken<ArrayList<TaskModel>>() {}.getType();
//        return gson.fromJson(json, type);
//    }

//
//    public static void saveTasks(Context context, ArrayList<TaskModel> arrayList){
//        SharedPreferences prefs = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        Gson gson = new Gson();
//        String json = gson.toJson(arrayList);
//        editor.putString("task_list", json);
//        editor.putBoolean("task_striked", false);
//        editor.putBoolean("task_checked", false);
//        editor.apply();
//    }
//
//    public static ArrayList<TaskModel> loadTasks(Context context){
//        SharedPreferences prefs = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = prefs.getString("task_list", null);
//        Boolean striked = prefs.getBoolean("task_striked", false);
//        Boolean checked = prefs.getBoolean("task_checked", false);
//        Type type = new TypeToken<ArrayList<TaskModel>>() {}.getType();
//        return gson.fromJson(json, type);
//    }

//    public void saveTasks(String task, boolean isChecked, boolean isStriked){
//        this.task = task;
//        this.isChecked = isChecked;
//        this.isStriked = isStriked;
//
//        SharedPreferences sharedPrefs = getSharedPreferences("task_prefs",Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("task",this.task);
//        editor.putBoolean("checked", this.isChecked);
//        editor.putBoolean("striked", this.isStriked);
//        editor.apply();
//    }
//
//    public ArrayList<TaskModel> tasksList(){
//        SharedPreferences sharedPrefs = getSharedPreferences("task_prefs",Context.MODE_PRIVATE);
//        String taskName = sharedPrefs.getString("task","");
//        boolean isChecked = sharedPrefs.getBoolean("checked",false);
//        boolean isStriked = sharedPrefs.getBoolean("striked", false);
//
//        arrayList.add(new TaskModel(taskName, isChecked, isStriked));
//        return arrayList;
//
//    }
}
