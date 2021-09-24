package com.example.illuminationmodify;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class userLocationActivity extends AppCompatActivity

{

    Characteristics illumination = new Characteristics();
    UserLocation location= new UserLocation();


    Button btnQueryUserLocation;
    Button btnModifyUserLocation;
    Button btnMaintainUserLocation;
    Button btnSpecificLocation;

    EditText modifyValueUserLocation;
    EditText maintainValueUserLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);



        btnQueryUserLocation = findViewById(R.id.btnqueryuserlocation);
        btnModifyUserLocation = findViewById(R.id.btnModifyuserlocation);
        btnMaintainUserLocation = findViewById(R.id.btnMaintainuserLocation);
        btnSpecificLocation = findViewById(R.id.btnSpecificLocation);



        modifyValueUserLocation = findViewById(R.id.modifyvalueuserlocation);
        maintainValueUserLocation = findViewById(R.id.maintainvalueuserLocation);


        btnQueryUserLocation.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                location.getUserLocation();

                String queriedValue= illumination.query(location);  // ****** Calling of query method with USER LOCATION ******

                TextView tview = findViewById(R.id.queryvalue);
                tview.setText("Current illumination level is  = " + queriedValue + "unit");

            }
        });
        btnModifyUserLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                location.getUserLocation();

                String modifyValue = modifyValueUserLocation.getText().toString();

                illumination.modify(location,modifyValue);  // ****** Calling of modify method with USER LOCATION ******

            }
        });
        btnMaintainUserLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                location.getUserLocation();

                String maintainValue = maintainValueUserLocation.getText().toString();

                illumination.maintain(location,maintainValue);  // ****** Calling of maintain method with USER LOCATION ******
            }
        });





    }

}
