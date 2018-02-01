package eci.pdsw.draw.controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Shape;

public class Duplicate implements Comando {
	IController c;
	List<Shape> shapes = new ArrayList<>();
	int part;

	public Duplicate(IController c,int part){
		this.c=c;	
		this.part=part;
		List<Shape> shapesAll=c.getShapes();
		for (int i=part;i<shapesAll.size();i++){
			this.shapes.add(shapesAll.get(i));
		}
	}

	
	@Override
	public void undo() {
		for (int i=part;i<c.getShapes().size();i++){
			c.deleteShape(i);
		}		
	}

	@Override
	public void redo() {
		for (Shape s:shapes){
			c.addShape(s.getPoint1(),s.getPoint2());
		}		        
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

}
