package com.manishjangra.mytodo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Paint;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {
    Context context;
    ArrayList<TaskModel> arrayList;
    CheckBox checkBox;

    public RecyclerTaskAdapter(Context context, ArrayList<TaskModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModel currentItem = arrayList.get(position);
        holder.taskContentView.setText(arrayList.get(position).task);


        holder.checkBox.setOnCheckedChangeListener(null); // Clear previous listener
        holder.checkBox.setChecked(currentItem.isChecked());

        // Define the OnCheckedChangeListener as a variable
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentItem.setChecked(isChecked);
                if (isChecked) {
                    currentItem.setChecked(true);
                    holder.taskContentView.setTextColor(holder.itemView.getResources().getColor(android.R.color.black));
                    holder.taskContentView.setPaintFlags(holder.taskContentView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    Dialog uncheckDialog = new Dialog(context);
                    uncheckDialog.setContentView(R.layout.uncheck_dialog);
                    uncheckDialog.setCancelable(false);
                    MaterialButton yesButton = uncheckDialog.findViewById(R.id.uncheck_yes_btn);
                    MaterialButton noButton = uncheckDialog.findViewById(R.id.uncheck_no_btn);
                    yesButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentItem.setChecked(false);
                            holder.taskContentView.setTextColor(holder.itemView.getResources().getColor(android.R.color.white));
                            holder.taskContentView.setPaintFlags(holder.taskContentView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                            uncheckDialog.dismiss();
                        }
                    });
                    noButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            holder.checkBox.setOnCheckedChangeListener(null);
                            holder.checkBox.setChecked(true);
                            uncheckDialog.dismiss();
                        }
                    });
                    uncheckDialog.show();
                }
            }

        });


        holder.llm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(context);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.task_dialog);

                EditText edtName = dialog.findViewById(R.id.add_edit_task);
                MaterialButton btnAction = dialog.findViewById(R.id.action_btn);

                MaterialTextView txtTitle = dialog.findViewById(R.id.dialog_title);

                btnAction.setText("Save");
                txtTitle.setText("Edit Task");

                edtName.setText(arrayList.get(position).task);


                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String taskEditing = "";
                        if (!edtName.getText().toString().equals("")) {
                            taskEditing = edtName.getText().toString();
                        } else {
                            Toast.makeText(context, "Write Task First!", Toast.LENGTH_SHORT).show();
                        }

                        arrayList.set(position, new TaskModel(taskEditing));
                        notifyItemChanged(position);

                        dialog.dismiss();

                    }

                });

                dialog.show();

            }
        });

        holder.llm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Dialog deleteDialog = new Dialog(context);
                deleteDialog.setCancelable(false);
                deleteDialog.setContentView(R.layout.uncheck_dialog);

                MaterialTextView titleDeleteText = deleteDialog.findViewById(R.id.uncheck_dialog_title);
                MaterialTextView msgDeleteText = deleteDialog.findViewById(R.id.uncheck_text);
                MaterialButton yesButton = deleteDialog.findViewById(R.id.uncheck_yes_btn);
                MaterialButton noButton = deleteDialog.findViewById(R.id.uncheck_no_btn);
                titleDeleteText.setText("Delete Task");
                msgDeleteText.setText("Do you want to delete this Task");

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayList.remove(position);
                        notifyItemRemoved(position);
                        deleteDialog.dismiss();
                    }
                });

                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                    }
                });
                deleteDialog.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llm;
        MaterialTextView taskContentView;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            taskContentView = itemView.findViewById(R.id.task_content_view);
            llm = itemView.findViewById(R.id.item_layout);
            checkBox = itemView.findViewById(R.id.checkbox_view);
        }
    }
}
