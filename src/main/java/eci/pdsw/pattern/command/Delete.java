package eci.pdsw.pattern.command;



import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;

public class Delete extends Draw{
	
	public Delete(IController c, Shape s, int idx) {
		super(c, s, idx);
		// TODO Auto-generated constructor stub
	}
	@Override	
	public void undo(){
		super.redo();		
	}
	@Override
	public void redo(){
		super.undo();		
	}

}
