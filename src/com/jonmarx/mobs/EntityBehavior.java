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
public abstract class EntityBehavior implements Serializable {
    public abstract void render(Graphics g, String fileName, int x, int y);
    public abstract void update(int frame);
}
