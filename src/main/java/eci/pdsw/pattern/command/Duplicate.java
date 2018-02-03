package eci.pdsw.pattern.command;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.draw.model.ShapeFactory;

public class Duplicate implements Command {
	private IController c;
	private ShapeFactory shapeFactory=new ShapeFactory();
	private List<Shape> nuevas=new ArrayList<>();
	
	
	public Duplicate(IController c){
		this.c=c;				
	} 

	@Override
	public void redo() {
		List<Point> newShapesFirstPoints=new LinkedList<>();
        List<Point> newShapesSecondPoints=new LinkedList<>();         
        int displacementDelta=10+new Random(System.currentTimeMillis()).nextInt(50);        
        List<ElementType> elem=new ArrayList<>();
        for (Shape s:c.getShapes()){
            newShapesFirstPoints.add(new Point(s.getPoint1().getX(),s.getPoint1().getY()+displacementDelta));
            newShapesSecondPoints.add(new Point(s.getPoint2().getX(),s.getPoint2().getY()+displacementDelta));
            elem.add(s.getElementType());
        }
        Iterator<Point> it1=newShapesFirstPoints.iterator();
        Iterator<Point> it2=newShapesSecondPoints.iterator();
        Iterator<ElementType> ty=elem.iterator();
        
        while (it1.hasNext() && it2.hasNext() && ty.hasNext()){          	
            Shape sh=shapeFactory.createShape(ty.next(),it1.next(), it2.next());
            c.getShapes().add(sh);
            nuevas.add(sh);        
            c.notifyObservers();
        }      
		
	}

	@Override
	public void undo() {
		for (Shape s:nuevas){
			c.getShapes().remove(s);
		}
		c.notifyObservers();
	}


}
