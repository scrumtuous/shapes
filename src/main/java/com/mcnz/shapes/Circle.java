package com.mcnz.shapes;

public class Circle extends Shape {
    
    public Circle(int radius)
    {
        height = 2*radius;
        width = 2*radius;
    }

    public int calcArea() {
        return (int)(Math.PI * Math.pow(height/2, 2));
    }
}
