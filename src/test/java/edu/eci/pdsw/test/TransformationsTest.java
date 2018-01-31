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
import java.awt.Dimension;
import java.awt.Toolkit;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.Throwables;

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
    	Controller guictrl=new Controller();      
    	List<Shape> sh=guictrl.getShapes();
		guictrl.setSelectedElementType(ElementType.Line);
    	qt().forAll(pairOfPointsY()
                .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                		+p.getSecond().getX() +"  "+ p.getSecond().getY()))
        .check((p) -> {             		
        		guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());             		  
        		Shape last=sh.get(sh.size()-1); 
        		float[] viejos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
        				last.getPoint2().getX(),last.getPoint2().getY()};        	
        		guictrl.rotateSelectedShape(sh.size()-1);
        		float[] nuevos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
        				last.getPoint2().getX(),last.getPoint2().getY()};         	       		
        		return lineaRotadaf(nuevos,viejos);
            });	    
    }    
    
  
    
    /**
    * Prueba Vertical
    * Basado en la 1
    */
  @Test
   public void rotateSelectedShapeTest2(){
    	Controller guictrl=new Controller();      
    	List<Shape> sh=guictrl.getShapes();
		guictrl.setSelectedElementType(ElementType.Line);
    	qt().forAll(pairOfPointsX()
                .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                		+p.getSecond().getX() +"  "+ p.getSecond().getY()))
        .check((p) -> {             		
        		guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());             		  
        		Shape last=sh.get(sh.size()-1); 
        		float[] viejos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
        				last.getPoint2().getX(),last.getPoint2().getY()};        	
        		guictrl.rotateSelectedShape(sh.size()-1);
        		float[] nuevos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
        				last.getPoint2().getX(),last.getPoint2().getY()};         	       		
        		return lineaRotadaf(nuevos,viejos);
            });	    
   }    
   
   @Test
   public void rotateSelectedShapeTest3(){
	   Controller guictrl=new Controller();      
	   List<Shape> sh=guictrl.getShapes();
	   guictrl.setSelectedElementType(ElementType.Line);
	   qt().forAll(pairOfPointsDif()
               .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
               		+p.getSecond().getX() +"  "+ p.getSecond().getY()))
       .check((p) -> {             		
       		guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());             		  
       		Shape last=sh.get(sh.size()-1); 
       		float[] viejos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
       				last.getPoint2().getX(),last.getPoint2().getY()};        	
       		guictrl.rotateSelectedShape(sh.size()-1);
       		float[] nuevos=new float[]{last.getPoint1().getX(),last.getPoint1().getY(),
       				last.getPoint2().getX(),last.getPoint2().getY()};         	       		
       		return lineaRotadaf(nuevos,viejos);
           });	       
   }
   
    /**
    * Prueba Afuera de la pantalla
    */
    @Test
    public void rotateSelectedShapeTest4(){
        Controller guictrl=new Controller();
        List<Shape> sh=guictrl.getShapes();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println(screenSize);
        guictrl.setSelectedElementType(ElementType.Line);
        
        qt().forAll(pairOfPointsDif()
            .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
               		+p.getSecond().getX() +"  "+ p.getSecond().getY()))
            .check((p) -> {             		
       		guictrl.addShapeFromScreenPoints(p.getFirst(),p.getSecond());             		  
       		return guictrl.getShapes().get(0).getPoint1().getY() < screenSize.height && guictrl.getShapes().get(0).getPoint2().getX() < screenSize.width;
           });
    }
   
   /**
    * Identifica si roto 90 grados una linea respecto a la esquina inferior derecha
    * @param n [x1,y1,x2,y2] nuevos puntos
    * @param v [x1,y1,x2,y2] viejos puntos
    * @return Si roto 90 grados
    */
    public boolean lineaRotadaf(float[] n,float[] v){    		
    	// 0=x1 1=y1 2=x2 3=y2
    	boolean ans=false;
    	try{
    		// Esquinas para comprobar el eje de rotacion
			float[] EsquinaIV=new float[]{Math.min(v[0], v[2]),Math.max(v[1], v[3])};		
			float[] EsquinaSN=new float[]{Math.min(n[0], n[2]),Math.min(n[1], n[3])};			
			ans= n[1]-n[3]==v[0]-v[2] // distacia en x de los viejos = dist y de los nuevos
					&& n[0]-n[2]==v[1]-v[3] // distancia y de los viejos = dist x de los nuevos
						&& EsquinaIV[0]==EsquinaSN[0] && EsquinaSN[1]==EsquinaIV[1]; // direccion de giro	
			System.out.println(EsquinaIV[0]+" "+EsquinaIV[1]+" "+EsquinaSN[0]+" "+EsquinaSN[1]+" ");
	    }catch(Exception ex){ 	}	
    	return ans;
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
        
            .check((p) ->  p.getSecond().getY()==p.getFirst().getY()  && p.getFirst().getY()>=0 && p.getSecond().getY()>0            	
                );
    }   
    
    @Test
    public void TestMismaXPuntos(){
        qt().forAll(pairOfPointsX()
                    .describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                    		+p.getSecond().getX() +"  "+ p.getSecond().getY()))        
        
            .check((p) ->  p.getSecond().getX()==p.getFirst().getX() && p.getFirst().getY()>=0 && p.getSecond().getY()>0              	
                );
    }   
    
    @Test
    public void TestDifPuntos(){
        qt().forAll(pairOfPointsDif()
        		.describedAs((p) -> "x & y: Primero " + p.getFirst().getX() +"  "+ p.getFirst().getY()+"\n Segundo punto : "
                    		+p.getSecond().getX() +"  "+ p.getSecond().getY()))        
        
            .check((p) ->  p.getSecond().getX()!=p.getFirst().getX() && p.getSecond().getY()!=p.getFirst().getY() 
            && p.getFirst().getY()>=0 && p.getSecond().getY()>0
                );
    }       
   
    
    //---
    //
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsSameY() {        
        return lists().of(pairOfPointsY()).ofSizeBetween(0,20);
    }
    
    
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsY() {
        return points().zip(integers().allPositive(),(p1,x2) ->new Pair<>(new java.awt.Point(Math.abs(p1.x+x2),p1.y),p1));
    }
       
    //---
    //
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsSameX() {        
        return lists().of(pairOfPointsX()).ofSizeBetween(0,20);
    }
    
    
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsX() {
        return points().zip(integers().allPositive(),(p1,y2) ->new Pair<>(new java.awt.Point(p1.x,Math.abs(p1.y+y2)),p1));
    }
    
    //--
    //
    private Gen<Pair<java.awt.Point,java.awt.Point>> pairOfPointsDif() {
        return pointsS().zip(range(1,20),(p1,dif) -> new Pair<>(new java.awt.Point(Math.abs(p1.x+dif),Math.abs(p1.y+dif)),p1));
    }
    
    private Gen<java.awt.Point> pointsS() {
        return range(1,30000)
            .zip(range(1,30000), (x,y) -> new java.awt.Point(x,y));
    }
        
    private Gen<List<Pair<java.awt.Point,java.awt.Point>>> listsLineAsPointsDif() {        
        return lists().of(pairOfPointsDif()).ofSizeBetween(0,20);
    }
    
    
}
    
    
