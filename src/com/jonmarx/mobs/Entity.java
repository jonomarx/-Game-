/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.mobs;

import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author Jon
 */
public class Entity implements Serializable {
    private int x;
    private int y;
    private String fileName;
    private EntityBehavior b;
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int[] getLocation() {
        return new int[] {x, y};
    }
    
    public Entity(int x, int y, EntityBehavior b) {
        this.b = b;
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g) {
        b.render(g, fileName, x, y);
    }
    
    public void update(int frame) {
        b.update(frame);
    }
}
