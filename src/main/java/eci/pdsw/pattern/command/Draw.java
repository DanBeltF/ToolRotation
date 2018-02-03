package eci.pdsw.pattern.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;

public class Draw implements Command{
	private IController c;
	private Shape s;
	private int idx;
	
	public Draw(IController c, Shape s,int idx) {
		this.c=c;
		this.s=s;
		this.idx=idx;
	}

	@Override
	public void redo() {
		if (idx<0){
			c.getShapes().add(s); 	
		}else{
			c.getShapes().add(idx, s);
		}	
		c.notifyObservers();     	
	}	
	
	@Override
	public void undo() {
		c.getShapes().remove(s);	
		c.notifyObservers();     	
	}

}
