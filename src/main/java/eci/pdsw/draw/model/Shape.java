/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.model;

/**
 *
 * @author fchaves
 */
public abstract class Shape {
    private Point p1;

    private Point p2; 

    protected Shape(Point p1,Point p2) {
       this.p1 = p1;
       this.p2 = p2;     
       
    }
    
    abstract public ElementType getElementType();
    
    public Point getPoint1() {
    	return p1;
    }
    
    abstract public Shape cloneShape();    
    
    public Point getPoint2() {
        return p2;
    }
    
    public void setPoint1(Point p1) {
        this.p1 = p1;
    }

    public void setPoint2(Point p2) {
        this.p2 = p2;
    }
    
	public void set2points(float[] Arot,float[] Brot){
		setPoint1(new Point(Arot[0],Arot[1]));
		setPoint2(new Point(Brot[0],Brot[1]));	
	}
	
    
    public static float[] rotarMenosNoventaGrados(float x,float y,float ex,float ey){
    	//x-ex=x NO TOCAR !!!
    	//y-ey=y
    	return new float[]{-(y-ey)+ex,x-ex+ey};
    }
    
    public static float[] rotarNoventaGrados(float x,float y,float ex,float ey){     	
    	return new float[]{(y-ey)+ex,-(x-ex)+ey};
    }   
   
    
    
    public abstract void rotate();
    
    public abstract void invRotate();
}
