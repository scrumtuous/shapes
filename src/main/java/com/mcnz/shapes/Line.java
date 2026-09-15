package com.mcnz.shapes;

public class Line extends Shape
{

    Point start;
    Point end;
    
    public double getLength() {
        double aSquared = Math.pow((start.x - end.x),2);
        double bSquared = Math.pow((start.y - end.y),2);

        double length = Math.sqrt(aSquared + bSquared);

        return length; 
    }

}
