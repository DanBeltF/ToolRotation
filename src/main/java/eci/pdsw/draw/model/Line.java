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

	@Override
	public float[] getEsq() {		
		return new float[]{Math.min(getPoint1().getX(), getPoint2().getX())				
				,Math.max(getPoint1().getY(), getPoint2().getY())};
	}
	public void rotate() {		
		float[] esq=getEsq();
		float[] Arot=rotarNoventaGrados(getPoint1().getX(),getPoint1().getY(),esq[0],esq[1]);
		float[] Brot=rotarNoventaGrados(getPoint2().getX(),getPoint2().getY(),esq[0],esq[1]);		
		setPoint1(new Point(Arot[0],Arot[1]));
		setPoint2(new Point(Brot[0],Brot[1]));
	}    
    
}
