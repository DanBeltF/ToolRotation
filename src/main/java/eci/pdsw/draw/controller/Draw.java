package eci.pdsw.draw.controller;

import java.awt.*;
import eci.pdsw.draw.gui.shapes.*;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.draw.model.ShapeFactory;

public class Draw implements Comando{
	IController c;
	eci.pdsw.draw.model.Point p1;
	eci.pdsw.draw.model.Point p2;
	int index;
	ElementType tipo;
	public Draw(IController c,int index){
		this.c=c;
		this.index=index;
		java.util.List<Shape> ls=c.getShapes();
		this.p1=ls.get(index).getPoint1();
		this.p2=ls.get(index).getPoint2();
		tipo=ls.get(index).getElementType();
	}

	
	
	@Override
	public void undo() {
		c.deleteShape(index);		
	}

	@Override //Ejecutar
	public void redo() {
		c.setSelectedElementType(tipo);
		c.addShape(p1, p2);		
	}



	@Override
	public int getIndex() {		
		return index;
	}
	

}
