/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jon
 */
public abstract class TileBehavior implements Serializable {
    public abstract void render(int frame, Graphics g, int x, int y);
    public abstract void update(int frame, int x, int y);
    
}
