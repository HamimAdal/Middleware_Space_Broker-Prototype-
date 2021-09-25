package com.example.illuminationmodify;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


interface floorPlanInterface
{
    String returnSpaceImage( );

}

interface LocationInterface
{

    String getSpecificLocation(View view, MotionEvent event);
    void getUserLocation();

}





class floorPlan implements floorPlanInterface
{


    public static String requestSpaceImage ;


    // **********************************
    // The following method is for retrieving space image from raspberry pi (Space Broker)
    // **********************************

    public String returnSpaceImage()
    {


        requestSpaceImage = "4" + ":" + "NULLVALUE" ;

        spaceImage SpaceImage = new spaceImage();
        SpaceImage.execute(requestSpaceImage);

        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)

        {
            e.printStackTrace();
        }

        String queriedSpaceImage= SpaceImage.k1;

        return queriedSpaceImage;

    }


}

class Location implements LocationInterface
{

    String X,Y;

    // *******************************************
    // The following method is for providing Location coordinates from the user by touching pixel on the Space Image
    // *******************************************


    public String getSpecificLocation(View view, MotionEvent event)
    {
        int x,y;
        float eventX = event.getX();
        float eventY = event.getY();
        float[] eventXY = new float[] {eventX, eventY};

        Matrix invertMatrix = new Matrix();
        ((ImageView)view).getImageMatrix().invert(invertMatrix);

        invertMatrix.mapPoints(eventXY);
        x = Integer.valueOf((int)eventXY[0]);
        y = Integer.valueOf((int)eventXY[1]);
        Drawable imgDrawable = ((ImageView)view).getDrawable();
        Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();


        double bitwidth = bitmap.getWidth();
        double x1 = (x / bitwidth) * 480;
        x = (int) x1;

        double bitheight = bitmap.getHeight();
        double y1 = ((bitmap.getHeight()- y) / bitheight) * 360;
        y = (int) y1;

        X = String.valueOf(x);
        Y = String.valueOf(y);

        String coordinate = x + ":" + y;
        return coordinate;


    }

    // *******************************************
    // The following method is for providing user location. As the user location is retrieved from the raspberry-pi's end, we are sending null values here.
    // *******************************************
    public void getUserLocation()
    {

        X = "NULL";
        Y = "NULL";

    }

}









