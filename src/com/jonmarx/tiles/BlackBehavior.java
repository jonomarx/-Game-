/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.tiles;

import java.awt.*;

/**
 *
 * @author Jon
 */
public class BlackBehavior extends TileBehavior{

    @Override
    public void render(int frame, Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x * 16, y * 16, 16, 16);
        g.fillRect(x * 16, y * 16, 16, 16);
    }

    @Override
    public void update(int frame, int x, int y) {
        return;
    }
    
}
