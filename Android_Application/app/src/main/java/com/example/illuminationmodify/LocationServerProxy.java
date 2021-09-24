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

interface SpecificLocationInterface
{

    String getSpecificLocation(View view, MotionEvent event);

}

interface UserLocationInterface
{

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


        requestSpaceImage = "7" + ":" + "NULLVALUE" ;

        spaceImage SpaceImage = new spaceImage();
        SpaceImage.execute(requestSpaceImage);

        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)

        {
            e.printStackTrace();
        }

        String queriedSpaceImage= SpaceImage.ko1;

        return queriedSpaceImage;

    }


    // *******************************************
    // The following method is for providing Location coordinates from the user by touching pixel on the Space Image
    // *******************************************




}

class SpecificLocation implements SpecificLocationInterface
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




}

class UserLocation implements UserLocationInterface
{

    String X,Y;



    public void getUserLocation()
    {

        X = "NULL";
        Y = "NULL";

    }


}







