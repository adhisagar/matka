package com.example.matkamasthi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matkamasthi.R;
import com.example.matkamasthi.model.PanelResultShowModel;

import java.util.ArrayList;

public class PanelResultShowAdapter extends RecyclerView.Adapter<PanelResultShowAdapter.Viewholder> {

    Context context;
    ArrayList<PanelResultShowModel> resultShowModelArrayList;

    public PanelResultShowAdapter(Context context, ArrayList<PanelResultShowModel> resultShowModelArrayList) {
        this.context = context;
        this.resultShowModelArrayList = resultShowModelArrayList;
    }

    @NonNull
    @Override
    public PanelResultShowAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.panel_result_show_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanelResultShowAdapter.Viewholder holder, int position) {

        holder.panelDate.setText("New Date to New Date");
        if (!resultShowModelArrayList.get(position).getMondayFirstResult().isEmpty()) {
            holder.monText.setText(resultShowModelArrayList.get(position).getMondayFirstResult());
        } else {
            holder.monText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getTuesdayFirstResult().isEmpty()) {
            holder.tuesText.setText(resultShowModelArrayList.get(position).getTuesdayFirstResult());
        } else {
            holder.tuesText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getWednesdayFirstResult().isEmpty()) {
            holder.wedText.setText(resultShowModelArrayList.get(position).getWednesdayFirstResult());
        } else {
            holder.wedText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getThursdayFirstResult().isEmpty()) {
            holder.thuText.setText(resultShowModelArrayList.get(position).getThursdayFirstResult());
        } else {
            holder.thuText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getFidayFirstResult().isEmpty()) {
            holder.friText.setText(resultShowModelArrayList.get(position).getFidayFirstResult());
        } else {
            holder.friText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getSaturdayFirstResult().isEmpty()) {
            holder.satText.setText(resultShowModelArrayList.get(position).getSaturdayFirstResult());
        } else {
            holder.satText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getSundayFirstResult().isEmpty()) {
            holder.sunText.setText(resultShowModelArrayList.get(position).getSundayFirstResult());
        } else {
            holder.sunText.setText("**");
        }

        ///for Second Data
        if (!resultShowModelArrayList.get(position).getMondaySecondResult().isEmpty()) {
            holder.monSecondText.setText(resultShowModelArrayList.get(position).getMondaySecondResult());
        } else {
            holder.monSecondText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getTuesdaySecondResult().isEmpty()) {
            holder.tuesSecondText.setText(resultShowModelArrayList.get(position).getTuesdaySecondResult());
        } else {
            holder.tuesSecondText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getWednesdaySecondResult().isEmpty()) {
            holder.wedSecondText.setText(resultShowModelArrayList.get(position).getWednesdaySecondResult());
        } else {
            holder.wedSecondText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getThursdaySecondResult().isEmpty()) {
            holder.thuSecondText.setText(resultShowModelArrayList.get(position).getThursdaySecondResult());
        } else {
            holder.thuSecondText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getFidaySecondResult().isEmpty()) {
            holder.friSecondText.setText(resultShowModelArrayList.get(position).getFidaySecondResult());
        } else {
            holder.friSecondText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getSaturdaySecondResult().isEmpty()) {
            holder.satSecondText.setText(resultShowModelArrayList.get(position).getSaturdaySecondResult());
        } else {
            holder.satSecondText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getSundaySecondResult().isEmpty()) {
            holder.sunSecondText.setText(resultShowModelArrayList.get(position).getSundaySecondResult());
        } else {
            holder.sunSecondText.setText("**");
        }

        ///for Third Data
        if (!resultShowModelArrayList.get(position).getMondayThirdResult().isEmpty()) {
            holder.monThirdText.setText(resultShowModelArrayList.get(position).getMondayThirdResult());
        } else {
            holder.monThirdText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getTuesdayThirdResult().isEmpty()) {
            holder.tuesThirdText.setText(resultShowModelArrayList.get(position).getTuesdayThirdResult());
        } else {
            holder.tuesThirdText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getWednesdayThirdResult().isEmpty()) {
            holder.wedThirdText.setText(resultShowModelArrayList.get(position).getWednesdayThirdResult());
        } else {
            holder.wedThirdText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getThursdayThirdResult().isEmpty()) {
            holder.thuThirdText.setText(resultShowModelArrayList.get(position).getThursdayThirdResult());
        } else {
            holder.thuThirdText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getFidayThirdResult().isEmpty()) {
            holder.friThirdText.setText(resultShowModelArrayList.get(position).getFidayThirdResult());
        } else {
            holder.friThirdText.setText("**");
        }

        if (!resultShowModelArrayList.get(position).getSaturdayThirdResult().isEmpty()) {
            holder.satThirdText.setText(resultShowModelArrayList.get(position).getSaturdayThirdResult());
        } else {
            holder.satThirdText.setText("**");
        }
        if (!resultShowModelArrayList.get(position).getSundayThirdResult().isEmpty()) {
            holder.sunThirdText.setText(resultShowModelArrayList.get(position).getSundayThirdResult());
        } else {
            holder.sunThirdText.setText("**");
        }

    }


    @Override
    public int getItemCount() {
        return resultShowModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView monText, tuesText, wedText, thuText, friText, satText, sunText;
        TextView monSecondText, tuesSecondText, wedSecondText, thuSecondText, friSecondText, satSecondText, sunSecondText;
        TextView monThirdText, tuesThirdText, wedThirdText, thuThirdText, friThirdText, satThirdText, sunThirdText;
        TextView panelDate;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            monText = itemView.findViewById(R.id.monday_result_first_text);
            tuesText = itemView.findViewById(R.id.tuesday_result_first_text);
            wedText = itemView.findViewById(R.id.wednesday_result_first_text);
            thuText = itemView.findViewById(R.id.thursday_result_first_text);
            friText = itemView.findViewById(R.id.friday_result_first_text);
            satText = itemView.findViewById(R.id.saturday_result_first_text);
            sunText = itemView.findViewById(R.id.sunday_result_first_text);

            monSecondText = itemView.findViewById(R.id.monday_result_second_text);
            tuesSecondText = itemView.findViewById(R.id.tuesday_result_second_text);
            wedSecondText = itemView.findViewById(R.id.wednesday_result_second_text);
            thuSecondText = itemView.findViewById(R.id.thursday_result_second_text);
            friSecondText = itemView.findViewById(R.id.friday_result_second_text);
            satSecondText = itemView.findViewById(R.id.saturday_result_second_text);
            sunSecondText = itemView.findViewById(R.id.sunday_result_second_text);

            monThirdText = itemView.findViewById(R.id.monday_result_third_text);
            tuesThirdText = itemView.findViewById(R.id.tuesday_result_third_text);
            wedThirdText = itemView.findViewById(R.id.wednesday_result_third_text);
            thuThirdText = itemView.findViewById(R.id.thursday_result_third_text);
            friThirdText = itemView.findViewById(R.id.friday_result_third_text);
            satThirdText = itemView.findViewById(R.id.saturday_result_third_text);
            sunThirdText = itemView.findViewById(R.id.sunday_result_third_text);

            panelDate=itemView.findViewById(R.id.panel_date_result_text);
        }
    }
}
