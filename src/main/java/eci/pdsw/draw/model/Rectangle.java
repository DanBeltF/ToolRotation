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
public class Rectangle extends Shape {
     
	// OJO !
    @Override
    public ElementType getElementType() {
        return ElementType.Rectangle;
    }
        
    private Rectangle(Point p1, Point p2) {
        super(p1,p2);        
    }
    
    static public Rectangle newRectangle(Point p1,Point p2) {
        return new Rectangle(p1,p2);
    }

    @Override
    public Shape cloneShape() {
        return new Rectangle(this.getPoint1(), this.getPoint2());
    }

   
	public float[] getEsq() {		
		return new float[]{getPoint1().getX(),Math.max(getPoint1().getY(), getPoint2().getY())};
	}
    
  
    public void rotate(){
    	float[] esq=getEsq();		
    	float difx=Math.abs(getPoint1().getX()-getPoint2().getX());
    	float dify=Math.abs(getPoint1().getY()-getPoint2().getY());
		setPoint1(new Point(esq[0],esq[1]));
		setPoint2(new Point(getPoint1().getX()+dify,getPoint1().getY()+difx));
    }
	
}
