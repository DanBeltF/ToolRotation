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
    
	public void rotate() {		
		Point Av=this.getPoint1();
		Point Bv=this.getPoint2();
		float delta=new Float(Math.abs(Av.getY()-Bv.getY()));		
		this.setPoint1(new Point(Av.getY(),Av.getX()+delta));
		this.setPoint2(new Point(Bv.getY(),Bv.getX()+delta));
	}    
}
