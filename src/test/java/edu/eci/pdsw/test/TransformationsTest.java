/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.test;

import eci.pdsw.draw.controller.Controller;
import eci.pdsw.draw.model.ElementType;
import eci.pdsw.draw.model.Shape;
import eci.pdsw.draw.model.Point;
import eci.pdsw.util.Pair;

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
    
    //Nuevas pruebas
    /**
     * Prueba Horizontal
     */
    @Test
    public void rotateSelectedShapeTest1(){
    	qt().forAll(linesControllerHorizontal()
                .describedAs((c) -> "Cantidad Lineas Horizontales " + c.getShapes().size()))
        .check((controller) -> {
        		List<Shape> shapes = controller.getShapes();
        		boolean ans=true;
        		for (int i=0; i<shapes.size();i++){
        			//Guardar puntos anteriores
        			eci.pdsw.draw.model.Point[] parpuntos=new eci.pdsw.draw.model.Point[]{
        					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};
        			//Rota la linea
        			controller.rotateSelectedShape(i);
        			//Nuevos puntos
        			eci.pdsw.draw.model.Point[] nuevoparpuntos=new eci.pdsw.draw.model.Point[]{
        					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};   
        			// Verificar si se roto la linea
        			ans=ans&& lineaRotada(parpuntos,nuevoparpuntos);
        		}       		
        		return ans;
            });
    }    
    
    /**
    * Prueba Vertical
    * Basado en la 1
    */
   @Test
   public void rotateSelectedShapeTest2(){
   	qt().forAll(linesControllerVertical()
               .describedAs((c) -> "Cantidad Lineas Horizontales " + c.getShapes().size()))
       .check((controller) -> {
       		List<Shape> shapes = controller.getShapes();
       		boolean ans=true;
       		for (int i=0; i<shapes.size();i++){       		
       			eci.pdsw.draw.model.Point[] parpuntos=new eci.pdsw.draw.model.Point[]{
       					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};       			
       			controller.rotateSelectedShape(i);       			
       			eci.pdsw.draw.model.Point[] nuevoparpuntos=new eci.pdsw.draw.model.Point[]{
       					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};        		
       			ans=ans&& lineaRotada(parpuntos,nuevoparpuntos);
       		}       		
       		return ans;
           });
   }    
   
   @Test
   public void rotateSelectedShapeTest3(){
   	qt().forAll(linesControllerVertical()
               .describedAs((c) -> "Cantidad Lineas Horizontales " + c.getShapes().size()))
       .check((controller) -> {
       		List<Shape> shapes = controller.getShapes();
       		boolean ans=true;
       		for (int i=0; i<shapes.size();i++){       		
       			eci.pdsw.draw.model.Point[] parpuntos=new eci.pdsw.draw.model.Point[]{
       					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};       			
       			controller.rotateSelectedShape(i);       			
       			eci.pdsw.draw.model.Point[] nuevoparpuntos=new eci.pdsw.draw.model.Point[]{
       					controller.getShapes().get(i).getPoint1(),controller.getShapes().get(i).getPoint2()};        		
       			ans=ans&& lineaRotada(parpuntos,nuevoparpuntos);
       		}       		
       		return ans;
           });
   }    
    
    public boolean lineaRotada(eci.pdsw.draw.model.Point[] v,eci.pdsw.draw.model.Point[] n){    	
    		// Esquinas para comprobar el eje de rotacion
    		float[] EsquinaIV=new float[]{Math.min(v[0].getX(), v[1].getX()),Math.max(v[0].getY(), v[1].getY())};
    		float[] EsquinaSN=new float[]{Math.min(n[0].getX(), n[1].getX()),Math.min(n[0].getY(), n[1].getY())};
    		return n[0].getX()-n[1].getX()==v[0].getY()-v[1].getY() // distacia en x de los viejos = dist y de los nuevos
    				&& n[0].getY()-n[1].getY()==v[0].getX()-v[1].getX() // distancia y de los viejos = dist x de los nuevos
    				&& EsquinaIV[0]==EsquinaSN[0] && EsquinaIV[1]==EsquinaSN[1] // Corresponte la esquina
    						;   		
    }   

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
   
    @Test
    public void TestMismaYPuntos(){
        qt().forAll(pairOfPointsY()
                    .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                    		+p.getSecond().getX() +"  "+ p.getSecond().getY()))        
        
            .check((p) ->  p.getSecond().getY()==p.getFirst().getY()               	
                );
    }   
    
    @Test
    public void TestMismaXPuntos(){
        qt().forAll(pairOfPointsX()
                    .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                    		+p.getSecond().getX() +"  "+ p.getSecond().getY()))        
        
            .check((p) ->  p.getSecond().getX()==p.getFirst().getX()               	
                );
    }   
    
    @Test
    public void TestDifPuntos(){
        qt().forAll(pairOfPointsDif()
        		.describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                    		+p.getSecond().getX() +"  "+ p.getSecond().getY()))        
        
            .check((p) ->  p.getSecond().getX()!=p.getFirst().getX() && p.getSecond().getY()!=p.getFirst().getY()          	
                );
    }   
    

    private Gen<Controller> linesControllerHorizontal() {
        return listsLineAsPointsSameY().map( (ls) -> {
                    Controller guictrl=new Controller();        
                    guictrl.setSelectedElementType(ElementType.Line);
                    for(Pair<java.awt.Point,java.awt.Point> p : ls) {
                        guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());                        
                    }
                    return guictrl;
                });
    }
    
    private Gen<Controller> linesControllerVertical() {
        return listsLineAsPointsSameX().map( (ls) -> {
                    Controller guictrl=new Controller();        
                    guictrl.setSelectedElementType(ElementType.Line);
                    for(Pair<java.awt.Point,java.awt.Point> p : ls) {
                        guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());                        
                    }
                    return guictrl;
                });
    }
    
    private Gen<Controller> linesControllerDif() {
        return listsLineAsPointsDif().map( (ls) -> {
                    Controller guictrl=new Controller();        
                    guictrl.setSelectedElementType(ElementType.Line);
                    for(Pair<java.awt.Point,java.awt.Point> p : ls) {
                        guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());                        
                    }
                    return guictrl;
                });
    }
    
    //---
    //
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsSameY() {        
        return lists().of(pairOfPointsY()).ofSizeBetween(0,20);
    }
    
    
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsY() {
        return points().zip(integers().allPositive(),(p1,x2) ->new Pair<>(new java.awt.Point(x2,p1.y),p1));
    }
       
    //---
    //
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsSameX() {        
        return lists().of(pairOfPointsX()).ofSizeBetween(0,20);
    }
    
    
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsX() {
        return points().zip(integers().allPositive(),(p1,y2) ->new Pair<>(new java.awt.Point(p1.x,y2),p1));
    }
    
    //--
    //
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsDif() {
        return points().zip(integers().allPositive(),(p1,dif) -> new Pair<>(new java.awt.Point(p1.x+dif,p1.y+dif),p1));
    }
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsDif() {        
        return lists().of(pairOfPointsDif()).ofSizeBetween(0,20);
    }
    
}
    
    
