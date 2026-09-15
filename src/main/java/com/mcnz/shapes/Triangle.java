package com.mcnz.shapes;

public class Triangle extends Shape 
{
    public int calcArea() 
    {
        return (int)(super.calcArea() * 0.5);
    }
}
