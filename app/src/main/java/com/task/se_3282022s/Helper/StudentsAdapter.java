package com.task.se_3282022s.Helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.task.se_3282022s.R;
import com.task.se_3282022s.sqlite.StudentModel;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;


public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.MyViewHolder> {

    private final Context context;
    private List<StudentModel> adapterData;


    public StudentsAdapter(Context context, List<StudentModel> todoArrayList) {
        this.context = context;
        this.adapterData = todoArrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        holder.studentIdTextView.setText(adapterData.get(position).getStudentId());
        holder.nameTextView.setText(adapterData.get(position).getStudentName());
        holder.surNameTextView.setText(adapterData.get(position).getSurName());
        holder.fatherNameTextView.setText(adapterData.get(position).getFatherName());
        holder.nationalIdTextView.setText(adapterData.get(position).getNationalID());
        holder.dobTextView.setText(adapterData.get(position).getDateOfBirth());
        holder.genderTextView.setText(adapterData.get(position).getGender());
    }

    /**
     * Sets adapter data.
     *
     * @param adapterData the adapter data
     */
    public void setAdapterData(List<StudentModel> adapterData) {
        this.adapterData = adapterData;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (adapterData != null)
            return adapterData.size();
        else return 0;
    }

    /**
     * The type My view holder.
     */
    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView studentIdTextView;
        private final TextView nameTextView;
        private final TextView surNameTextView;
        private final TextView fatherNameTextView;
        private final TextView nationalIdTextView;
        private final TextView dobTextView;
        private final TextView genderTextView;

        /**
         * Instantiates a new My view holder.
         *
         * @param itemView the item view
         */
        protected MyViewHolder(View itemView) {
            super(itemView);

            studentIdTextView = itemView.findViewById(R.id.tv_student_id);
            nameTextView = itemView.findViewById(R.id.tv_name);
            surNameTextView = itemView.findViewById(R.id.tv_sur_name);
            fatherNameTextView = itemView.findViewById(R.id.tv_father_name);
            nationalIdTextView = itemView.findViewById(R.id.tv_national_id);
            dobTextView = itemView.findViewById(R.id.tv_dob);
            genderTextView = itemView.findViewById(R.id.tv_gender);

        }
    }
}
