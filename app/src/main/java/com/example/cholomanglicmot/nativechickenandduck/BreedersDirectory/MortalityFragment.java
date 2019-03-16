package com.example.cholomanglicmot.nativechickenandduck.BreedersDirectory;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MortalityFragment extends Fragment {
    private Button account_submit_button;
    private TextView profile_id;
    private EditText date_died, male_death, female_death, remarks ;
    Spinner reason;
    DatabaseHelper myDb;
    Calendar calendar, calendar2;

    public MortalityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mort, container, false);
        myDb = new DatabaseHelper(getContext());

        date_died = view.findViewById(R.id.date_died);
        male_death = view.findViewById(R.id.male_death);
        female_death = view.findViewById(R.id.female_death);
        remarks = view.findViewById(R.id.remarks);
        reason = view.findViewById(R.id.spinner_reason);


        date_died.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        selectedMonth++;
                        date_died.setText(selectedDay + "/" + selectedMonth + "/" + selectedYear);
                        calendar.set(selectedYear,selectedMonth,selectedDay);
                    }
                }, year, month, day);
                mDatePicker.show();

            }
        });
        account_submit_button = view.findViewById(R.id.action_ok);
        account_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!date_died.getText().toString().isEmpty() && !male_death.getText().toString().isEmpty() && !female_death.getText().toString().isEmpty() && !reason.getSelectedItem().toString().isEmpty()){
                    boolean isInserted = myDb.insertDataMortalityAndSales(date_died.getText().toString(),null,null,null, "breeder", "died", null,Integer.parseInt(male_death.getText().toString()), Integer.parseInt(female_death.getText().toString()),Integer.parseInt(male_death.getText().toString())+Integer.parseInt(female_death.getText().toString()),reason.getSelectedItem().toString(), remarks.getText().toString(), null );
                    if(isInserted == true){
                        Toast.makeText(getActivity(),"Database updated", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();

                    }else{
                        Toast.makeText(getActivity(),"Error", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getActivity(),"Please fill empty fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }


}