package eci.pdsw.pattern.command;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;

public class Rotate implements Command {
	private IController c;
	private Shape s;
	
	
	public Rotate(IController c,Shape s){
		this.c=c;
		this.s=s;
	}

	@Override
	public void redo() {
		s.rotate();
        c.notifyObservers();  		
	}

	@Override
	public void undo() {
		s.invRotate();
		c.notifyObservers();		
	}

}
