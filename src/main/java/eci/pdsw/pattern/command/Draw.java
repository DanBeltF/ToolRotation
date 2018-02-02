package eci.pdsw.pattern.command;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;

public class Draw implements Command{
	private IController c;
	private Shape s;
	
	public Draw(IController c, Shape s) {
		this.c=c;
		this.s=s;
	}

	@Override
	public void redo() {
		c.addShape(s);
		
	}

	@Override
	public void undo() {
		c.deleteShape(c.getShapes().indexOf(s));		
	}

}
