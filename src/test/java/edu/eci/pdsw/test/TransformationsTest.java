/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.test;

import eci.pdsw.draw.controller.Controller;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.util.Pair;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;
import org.quicktheories.generators.Lists;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.Generate.*;
import static org.quicktheories.generators.SourceDSL.*;

/**
 *
 * @author hcadavid, fchaves
 */
public class TransformationsTest {
    
    public TransformationsTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void duplicateTestSize(){
        qt().forAll(linesController()
                    .describedAs((c) -> "Controller Shapes size = " + c.getShapes().size()))
            .check((controller) -> {
                    int n = controller.getShapes().size();
                    controller.duplicateShapes();
                    int m = controller.getShapes().size();
                    
                    return m == 2 * n;
                });
    }


    @Test
    public void duplicateTestNotDuplicatedReferences() {
        qt().forAll(linesController()
                    .describedAs((c) -> "Controller Shapes size = " + c.getShapes()))
            .check((controller) -> {
                    controller.duplicateShapes();
                    List<Shape> shapes = controller.getShapes();

                    return shapes.stream()
                        .allMatch((si) -> 
                                  shapes.stream().filter((sj) -> si == sj ).count() == 1);
                });
    }
    
    @Test
    public void rotateSelectedShapeTest1(){
    	qt().forAll(linesController()
                .describedAs((c) -> "Controller Shapes size = " + c.getShapes()))
        .check((controller) -> {
                controller.duplicateShapes();
                List<Shape> shapes = controller.getShapes();

                return shapes.stream()
                    .allMatch((si) -> 
                              shapes.stream().filter((sj) -> si == sj ).count() == 1);
            });    }

    // Test cases generators

    /**
     **/
    private Gen<Controller> linesController() {
        return listsLineAsPoints().map( (ls) -> {
                    Controller guictrl=new Controller();        
                    guictrl.setSelectedElementType(ElementType.Line);
                    for(Pair<java.awt.Point,java.awt.Point> p : ls) {
                        guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());                        
                    }
                    return guictrl;
                });
    }
    
  

    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPoints() {        
        return lists().of(pairOfPoints()).ofSizeBetween(0,20);
    }

    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPoints() {
        return points().zip(points(),(p1,p2) -> new Pair<>(p1,p2));
    }

    private Gen<java.awt.Point> points() {
        return integers().allPositive()
            .zip(integers().allPositive(), (x,y) -> new java.awt.Point(x,y));
    }
    
    /// Nuevos generadores
    
   
    private Gen<java.awt.Point> pointsSameY() {      	
        return range(0,20).zip(range(0,20),(x,y) -> new java.awt.Point(x,y));
    }
    
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsY() {
        return points().zip(points(),(p1,p2) ->new Pair<>(p1,p2));
    }
    
   
    @Test
    public void Testsss(){
        qt().forAll(pointsSameY()
                    .describedAs((p) -> "x & y= " + p.getX() +"  "+ p.getY()))
            .check((p) -> {
                    double x =p.getX() ;
                  
                    double y = p.getY();
                    
                    System.out.println(p.getX()+" "+p.getY());
                    return true;
                });
    }

    
    
}
