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
public class Line extends Shape {
    
    @Override
    public ElementType getElementType() {
        return ElementType.Line;
    }

    private Line(Point p1, Point p2) {
        super(p1,p2);        
    }
    
    static public Line newLine(Point p1,Point p2) {
        return new Line(p1,p2);
    }

    @Override
    public Shape cloneShape() {
        return new Line(this.getPoint1(), this.getPoint2());
    }


	public float[] getEsqInf() {		
		return new float[]{Math.min(getPoint1().getX(), getPoint2().getX())				
				,Math.max(getPoint1().getY(), getPoint2().getY())};
	}	
	
	public float[] getEsqSup() {		
		return new float[]{Math.min(getPoint1().getX(), getPoint2().getX())				
				,Math.min(getPoint1().getY(), getPoint2().getY())};
	}	

	public void rotate(char sig){
		float[] esq;		
		switch(sig){
			case '-':
				esq=getEsqInf();				
				set2points(rotarMenosNoventaGrados(getPoint1().getX(),getPoint1().getY(),esq[0],esq[1]),
				rotarMenosNoventaGrados(getPoint2().getX(),getPoint2().getY(),esq[0],esq[1]));				
				break;
			case '+':
				esq=getEsqSup();						
				set2points(rotarNoventaGrados(getPoint1().getX(),getPoint1().getY(),esq[0],esq[1]),
				rotarNoventaGrados(getPoint2().getX(),getPoint2().getY(),esq[0],esq[1]));				
				break;			
		}	
	}
	
	@Override
	public void rotate() {		
		rotate('-');
	}

	@Override
	public void invRotate() {
		rotate('+');		
	}    
    
}
