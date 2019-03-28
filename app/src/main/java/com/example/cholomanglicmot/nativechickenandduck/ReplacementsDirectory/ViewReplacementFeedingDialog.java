package com.example.cholomanglicmot.nativechickenandduck.ReplacementsDirectory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

public class ViewReplacementFeedingDialog extends DialogFragment {

    DatabaseHelper myDb;
    TextView textView, offered, refused, remarks,date;

    Button save;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_view_brooder_feeding, container, false);
        final Integer brooder_ID = getArguments().getInt("Replacement Inventory ID");
        final String brooder_tag = getArguments().getString("Replacement Tag");
        final Integer brooder_feeding_id = getArguments().getInt("Replacement Feeding ID");


        myDb = new DatabaseHelper(getContext());



        textView = view.findViewById(R.id.textView);
        offered = view.findViewById(R.id.offered); //galing sa brooder table
        refused = view.findViewById(R.id.refused);//galing sa brooder table
        remarks = view.findViewById(R.id.remarks);//galing sa brooder table
        date = view.findViewById(R.id.date);//galing sa brooder table

        save = view.findViewById(R.id.save);

        textView.setText(brooder_tag);
/*REPLACEMENT_FEEDING_AMOUNT_OFFERED,REPLACEMENT_FEEDING_AMOUNT_REFUSED,REPLACEMENT_FEEDING_REMARKS,REPLACEMENT_FEEDING_DATE_COLLECTED*/

        Cursor cursor = myDb.getAllDataFromReplacementFeedingRecordsWhereFeedingID(brooder_feeding_id);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            Integer offered_int = cursor.getInt(0);
            Integer refused_int = cursor.getInt(1);

            offered.setText(offered_int.toString());
            refused.setText(refused_int.toString());
            remarks.setText(cursor.getString(2));
            date.setText(cursor.getString(3));


        }else{
            Toast.makeText(getContext(), "NO", Toast.LENGTH_SHORT).show();
        }



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();


            }
        });


        return view;
    }
}