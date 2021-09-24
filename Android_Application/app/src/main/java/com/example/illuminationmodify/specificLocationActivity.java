package com.example.illuminationmodify;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class specificLocationActivity extends Activity

{

    Characteristics illumination = new Characteristics();

    floorPlan floorPlanImage= new floorPlan();
    SpecificLocation location= new SpecificLocation();


    Button btnSpace;
    Button btnQuery;
    Button btnModify;
    Button btnMaintainWithLocation;

    EditText modifyWithLocationValue;
    EditText maintainWithLocationValue;


    public String x,y;
    TextView  coordinateXY, imgSize;
    ImageView spaceImage;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_location);



        spaceImage = findViewById(R.id.spaceImage);
        coordinateXY = findViewById(R.id.invertedxy);
        imgSize = findViewById(R.id.size);

        btnSpace = findViewById(R.id.btnspace);
        btnQuery = findViewById(R.id.btnQuery);
        btnModify = findViewById(R.id.btnModify);
        btnMaintainWithLocation = findViewById(R.id.btnMaintainwithlocation);


        modifyWithLocationValue = findViewById(R.id.modify);
        maintainWithLocationValue =  findViewById(R.id.maintainwithlocation);

        btnSpace.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                String queriedSpaceImage= floorPlanImage.returnSpaceImage();

                ImageView image = findViewById(R.id.spaceImage);
                byte[] imageBytes = Base64.decode(queriedSpaceImage, Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                image.setImageBitmap(decodedImage);


            }

        });


        spaceImage.setOnTouchListener(imgSourceOnTouchListener);

        btnQuery.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {




                String queriedValue = illumination.query(location);  // ****** Calling of query method with SPECIFIC LOCATION ******

                TextView tview = findViewById(R.id.queryvalue);
                tview.setText("Current illumination level is  = " + queriedValue + "unit");

            }
        });
        btnModify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {




                String modifyValue = modifyWithLocationValue.getText().toString();

                illumination.modify(location, modifyValue);

            }
        });

        btnMaintainWithLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {




                String maintainValue = maintainWithLocationValue.getText().toString();

                illumination.maintain(location,maintainValue);  // ****** Calling of maintain method with SPECIFIC LOCATION ******

            }
        });


    }

    OnTouchListener imgSourceOnTouchListener
            = new OnTouchListener()
    {

        @Override
        public boolean onTouch(View view, MotionEvent event)
        {

            String coordinate = location.getSpecificLocation(view,  event);

            String array[] = coordinate.split(":");

            x = array[0];
            y = array[1];

            coordinateXY.setText(
                    "X Co-ordinate of touched position: "
                            + x + "\n" + "Y Co-ordinate of touched position: "
                            + y);

            imgSize.setText(
                    "\nTotal size: "
                            +"Width: "  + String.valueOf(480) + ", "
                            +"Height: "  + String.valueOf(360));

            return true;

        }
    };

}

