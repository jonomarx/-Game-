/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import com.jonmarx.game.Main;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Jon
 */
public class FlashBehavior extends TileBehavior {

    @Override
    public void render(int frame, Graphics g, int x, int y) {
        if((frame % 16) > 8) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x * Main.pixelSize, y * Main.pixelSize, Main.pixelSize, Main.pixelSize);
    }

    @Override
    public void update(int frame, int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
