/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jon
 */
public class ColorBehavior extends TileBehavior {
    Color color;
    
    public ColorBehavior(Color color) {
        this.color = color;
    }
    
    @Override
    public void render(int frame, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x * 16, y * 16, 16, 16);
    }

    @Override
    public void update(int frame, int x, int y) {
        return;
    }
    
}
