package com.example.cholomanglicmot.nativechickenandduck;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BreederFamilyRecords extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private ImageButton create_breeder_familyRecord;

    LinkedHashMap<String, List<String>> Project_category;
    List<String> Project_list;
    ExpandableListView Exp_list;
    ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeder_family_records);
        Exp_list = findViewById(R.id.exp_list);
        Project_category = DataProvider.getInfo();
        Project_list =  new ArrayList<String>(Project_category.keySet());
        adapter = new ProjectAdapter(this, Project_category, Project_list);
        Exp_list.setAdapter(adapter);
        create_breeder_familyRecord = findViewById(R.id.open_dialog);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.closed);
        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Breeder");

        create_breeder_familyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_add_family_records = new Intent(BreederFamilyRecords.this, BreederFamilyRecordsAdd.class);
                startActivity(intent_add_family_records);
            }
        });

        Exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String string = Project_category.get(Project_list.get(groupPosition)).get(childPosition);
                switch (string){
                    case "Generation":
                        Intent intent_breeder_generation = new Intent(BreederFamilyRecords.this, BreederGeneration.class);
                        startActivity(intent_breeder_generation);
                        break;

                    case "Family Records":
                        finish();
                        startActivity(getIntent());
                        break;
                    case "Daily Records":
                        Intent intent_breeder_daily_records = new Intent(BreederFamilyRecords.this, BreederDailyRecords.class);
                        startActivity(intent_breeder_daily_records);
                        break;
                    case "Hatchery Records":
                        Intent intent_breeder_hatchery_records = new Intent(BreederFamilyRecords.this, BreederHatcheryRecords.class);
                        startActivity(intent_breeder_hatchery_records);
                        break;
                    case "Egg Quality Records":
                        Intent intent_breeder_egg_quality_records = new Intent(BreederFamilyRecords.this, BreederEggQualityRecords.class);
                        startActivity(intent_breeder_egg_quality_records);
                        break;
                    case "Add Replacement Stocks":
                        Intent intent_replacement_individual_record_add = new Intent(BreederFamilyRecords.this, ReplacementIndividualRecordAdd.class);
                        startActivity(intent_replacement_individual_record_add);
                        break;
                    case "Phenotypic and Morphometric":
                        Intent intent_replacement_phenotypic_morphometric = new Intent(BreederFamilyRecords.this, ReplacementPhenotypicMorphometric.class);
                        startActivity(intent_replacement_phenotypic_morphometric);
                        break;
                    case "Feeding Record":
                        Intent intent_replacement_feeding_record = new Intent(BreederFamilyRecords.this, ReplacementFeedingRecord.class);
                        startActivity(intent_replacement_feeding_record);
                        break;
                    case "Growth Performance":
                        Intent intent_brooder_growth_performance = new Intent(BreederFamilyRecords.this, BroodersGrowthPerformance.class);
                        startActivity(intent_brooder_growth_performance);
                        break;
                    case "Feeding Records":
                        Intent intent_brooder_feeding_records = new Intent(BreederFamilyRecords.this, BrooderFeedingRecords.class);
                        startActivity(intent_brooder_feeding_records);
                        break;
                }
                return false;
            }


        });

        Exp_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String string2 = Project_list.get(groupPosition);

                switch(string2){
                    case "Dashboard":
                        Intent intent_main = new Intent(BreederFamilyRecords.this, MainActivity.class);
                        startActivity(intent_main);
                        break;

                    case "Create Pens":
                        Intent intent_create_pen = new Intent(BreederFamilyRecords.this, CreatePen.class);
                        startActivity(intent_create_pen);
                        break;

                    case "Mortality, Culling, and Sales":
                        break;

                    case "Reports":
                        break;

                    case "Farm Settings":

                        break;
                }
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
