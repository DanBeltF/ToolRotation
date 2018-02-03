/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.draw.controller;

import eci.pdsw.draw.gui.shapes.Renderer;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Point;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.pattern.observer.Observable;
import eci.pdsw.pattern.observer.Observer;
import java.util.List;

/**
 *
 * @author fchaves
 */
public interface IController extends Observable {

    
    @Override
    void addObserver(Observer o); 
  

    void addShape(Point p1, Point p2);

    void addShape(Integer index, Shape shape);

    void addShapeFromScreenPoints(java.awt.Point p1, java.awt.Point p2);

    @Override
    void deleteObserver(Observer o);

    
    void rotateSelectedShape(Integer index);
    
    void deleteShape(Integer index);

    public void duplicateShapes();
    
    Renderer getRenderer();

    ElementType getSelectedElementType();

    List<Shape> getShapes();

    @Override
    void notifyObservers();

    void redo();

    void setRenderer(Renderer renderer);

    void setSelectedElementType(ElementType elementType);

    void undo();
    
}
