package eci.pdsw.pattern.command;

import java.util.List;
import java.util.ArrayList;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;


public class Duplicate implements Command {
	private IController c;
	private List<Shape> nuevas=new ArrayList<>(); 
	
	public Duplicate(IController c,List<Shape> nuevas) {
		this.c=c;
		this.nuevas=nuevas;	
	}

	@Override
	public void redo() {
		c.duplicateShapes();		
	}

	@Override
	public void undo() {
		List<Shape> tod=c.getShapes();
		for (Shape s:nuevas) {			
			c.deleteShape(tod.indexOf(s));		
		}		
	}

}
