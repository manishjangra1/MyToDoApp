package com.manishjangra.mytodo;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TaskModel> arrayList = new ArrayList<>();

    RecyclerTaskAdapter adapter;
    FloatingActionButton newTaskButton;

    CheckBox checkBox;
    MaterialTextView taskContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Load tasks from SharedPreferences
//        arrayList =  TaskStorage.tasksList();
//        if (arrayList == null) {
//            arrayList = new ArrayList<>();
//        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        newTaskButton = findViewById(R.id.new_task_btn);

        View itemView = getLayoutInflater().inflate(R.layout.item, null);
        checkBox = itemView.findViewById(R.id.checkbox_view);
        taskContentView = itemView.findViewById(R.id.task_content_view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        Log.d("Testing", "Recycler View Checked");


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    taskContentView.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    taskContentView.setPaintFlags(taskContentView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    taskContentView.setPaintFlags(taskContentView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    taskContentView.setTextColor(getResources().getColor(android.R.color.black));
                    taskContentView.setPaintFlags(taskContentView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

//                    taskContentView.setPaintFlags(taskContentView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });


        newTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.task_dialog);
                dialog.setCancelable(false);

                EditText editTaskText = dialog.findViewById(R.id.add_edit_task);
                MaterialButton actionButton = dialog.findViewById(R.id.action_btn);


                actionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taskAdding = "";
                        if (!editTaskText.getText().toString().isEmpty()) {
                            taskAdding = editTaskText.getText().toString();

                            arrayList.add(new TaskModel(taskAdding));
                            adapter.notifyItemInserted(arrayList.size() - 1);

                            recyclerView.smoothScrollToPosition(arrayList.size() - 1);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Write Task First!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerTaskAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Save tasks to SharedPreferences
//        TaskStorage.saveTasks(this, arrayList);
//    }

}