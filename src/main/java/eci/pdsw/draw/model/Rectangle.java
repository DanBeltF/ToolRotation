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

   
	public float[] getEsqInf() {		
		return new float[]{getPoint1().getX(),Math.max(getPoint1().getY(), getPoint2().getY())};
	}
	public float[] getEsqSup() {		
		return new float[]{getPoint1().getX(),Math.min(getPoint1().getY(), getPoint2().getY())};
	}
    
	public void rotate(char sig){
		float[] esq;	
		float difx=Math.abs(getPoint1().getX()-getPoint2().getX());
    	float dify=Math.abs(getPoint1().getY()-getPoint2().getY());
		switch(sig){
		case '-':
			esq=getEsqInf();
			setPoint1(new Point(esq[0],esq[1]));
			setPoint2(new Point(getPoint1().getX()+dify,getPoint1().getY()+difx));
			break;
		case '+':
			esq=getEsqSup();
			setPoint1(new Point(esq[0],esq[1]-difx));
			setPoint2(new Point(esq[0]+dify,esq[1]));
			break;
		}	
	}
	
	public void rotate() {		
		rotate('-');
	}

	@Override
	public void invRotate() {
		rotate('+');		
	}     
	
}
