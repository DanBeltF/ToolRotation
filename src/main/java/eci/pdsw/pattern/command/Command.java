/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eci.pdsw.pattern.command;
import java.util.List;

import eci.pdsw.draw.controller.IController;
import eci.pdsw.draw.model.Shape;


/**
 *
 * @author fchaves
 */
public interface Command {
    public void redo();
    public void undo();    
}
