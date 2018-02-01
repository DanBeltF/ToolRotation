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
       if (p1.getX()<0 || p1.getY()<0 || p2.getY()<0 || p2.getX()<0)
    	   //System.out.println("Puntos negativos !!!")
    	   ;
       
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
    
    public static float[] rotarNoventaGrados(float x,float y,float ex,float ey){
    	//x-ex=x NO TOCAR !!!
    	//y-ey=y
    	return new float[]{-(y-ey)+ex,x-ex+ey};
    }
    
	public void rotate() {		
		float[] esq=new float[]{Math.min(getPoint1().getX(), getPoint2().getX())				
		,Math.max(getPoint1().getY(), getPoint2().getY())};
		float[] Arot=rotarNoventaGrados(getPoint1().getX(),getPoint1().getY(),esq[0],esq[1]);
		float[] Brot=rotarNoventaGrados(getPoint2().getX(),getPoint2().getY(),esq[0],esq[1]);		
		setPoint1(new Point(Arot[0],Arot[1]));
		setPoint2(new Point(Brot[0],Brot[1]));
	}    
}
