package eci.pdsw.draw.controller;

public class Delete extends Draw{

	public Delete(IController c, int index) {
		super(c, index);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void undo() {
		super.redo();		
	}

	@Override //Ejecutar
	public void redo() {
		super.undo();
	}

}
