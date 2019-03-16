package com.example.cholomanglicmot.nativechickenandduck.FamilyDirectory;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cholomanglicmot.nativechickenandduck.DatabaseHelper;
import com.example.cholomanglicmot.nativechickenandduck.R;

import java.util.List;

public class CreateFamilyDialog extends DialogFragment {

    private static final String TAG = "CreateFamilyDialog";
    private EditText family_number;
    private Spinner generation_spinner, line_spinner;
    private Button mActionOk;
    DatabaseHelper myDb;
    StringBuffer buffer = new StringBuffer();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_create_family, container, false);
        mActionOk = view.findViewById(R.id.action_ok);
        family_number = view.findViewById(R.id.brooder_feeding_offered);
        generation_spinner = (Spinner) view.findViewById(R.id.generation_spinner);
        line_spinner = view.findViewById(R.id.line_spinner);
        // find the radiobutton by returned id
       // spinner.setOnItemSelectedListener(this);
        loadSpinnerDataForGeneration();

        //String selected_generation = generation_spinner.getSelectedItem().toString();
        //Toast.makeText(getContext(),selected_generation + "yo", Toast.LENGTH_LONG).show();
        generation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_generation = generation_spinner.getSelectedItem().toString();
                loadSpinnerDataForLine(selected_generation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

      
        myDb = new DatabaseHelper(getContext());


        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!family_number.getText().toString().isEmpty()){
                    Cursor cursor_line = myDb.getDataFromLineWhereLineNumber(line_spinner.getSelectedItem().toString());
                    cursor_line.moveToFirst();
                    if(cursor_line.getCount() != 0){
                        Integer line_id = cursor_line.getInt(0);
                        String family = new String();
                        switch (family_number.getText().toString().length()){
                            case 1:
                                family = String.format("%04d" , Integer.parseInt(family_number.getText().toString()));
                                break;
                            case 2:
                                family = String.format("%04d" , Integer.parseInt(family_number.getText().toString()));
                                break;
                            case 3:
                                family = String.format("%04d" ,Integer.parseInt(family_number.getText().toString()));
                                break;
                            case 4:
                                family = String.format("%04d" , Integer.parseInt(family_number.getText().toString()));
                                break;
                            default:
                                break;

                        }
                        boolean isInserted = myDb.insertDataFamily(family,line_id);
                       /* Cursor cursor1 = myDb.getDataFromLineWhereID(line_id);
                        cursor1.moveToFirst();
                        buffer.append(cursor1.getString(1)+"\n");
                        showMessage("YO", buffer.toString());*/

                        if(isInserted == true){
                            Toast.makeText(getActivity(),"Family added to database", Toast.LENGTH_SHORT).show();
                            Intent intent_family = new Intent(getActivity(), CreateFamilies.class);
                            startActivity(intent_family);
                            getDialog().dismiss();
                        }else{
                            Toast.makeText(getActivity(),"Family not added to database", Toast.LENGTH_SHORT).show();
                        }
                        getDialog().dismiss();
                    }

                }else{
                    Toast.makeText(getActivity(), "Please fill any empty fields", Toast.LENGTH_SHORT).show();
                }



            }

        });

        return view;
    }
    private void loadSpinnerDataForGeneration() {
        // database handler
        DatabaseHelper db = new DatabaseHelper(getContext());

        // Spinner Drop down elements
        List<String> generations = db.getAllDataFromGenerationasList();
      //  List<String> lines = db.getAllDataFromLineasList();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, generations);
        /*ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);*/


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        generation_spinner.setAdapter(dataAdapter);
       // line_spinner.setAdapter(dataAdapter2);


    }

    public void loadSpinnerDataForLine(String selected_generation){
        DatabaseHelper db = new DatabaseHelper(getContext());
        List<String> lines = db.getAllDataFromLineasList(selected_generation);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, lines);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        line_spinner.setAdapter(dataAdapter2);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}