package com.example.illuminationmodify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// ****** query,modify and maintain method with USER LOCATION ******

public class MainActivity extends AppCompatActivity

{

    Button btnSpecificLocation;
    Button btnUserLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUserLocation = findViewById(R.id.btnUserLocation);
        btnSpecificLocation = findViewById(R.id.btnSpecificLocation);

        btnUserLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // ****** Going to the second page which deal with query,modify,maintain method with User Location ******

                Intent intent = new Intent(MainActivity.this, userLocationActivity.class);
                startActivity(intent);

            }
        });

        btnSpecificLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // ****** Going to the second page which deal with query,modify,maintain method with Specific Location ******

                Intent intent = new Intent(MainActivity.this, specificLocationActivity.class);
                startActivity(intent);

            }
        });

    }

}
