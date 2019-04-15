/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import java.awt.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jon
 */
public class Tile implements Serializable {
    TileBehavior b;
    boolean isSolid;
    
    public Tile(TileBehavior t, boolean isSolid) {
        b = t;
        this.isSolid = isSolid;
    }
    
    public void render(Graphics g, int x, int y, int frame) {
        if(b == null) return;
        b.render(frame, g, x, y);
    }
    
    public void update(int x, int y, int frame) {
        if(b == null) return;
        b.update(frame, x, y);
    }
    
    public boolean isSolid() {
        return isSolid;
    }
}
