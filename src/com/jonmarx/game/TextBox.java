/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonmarx.game;

import com.jonmarx.tiles.Tile;

/**
 *
 * @author Jon
 */
public class TextBox {
    private boolean isOpen = false;
    Tile[][] box;
    public TextBox(Tile[][] tiles) {
        box = tiles;
    }
    public void open() {
        isOpen = true;
    }
    public void close() {
        isOpen = false;
        onClose();
    }
    protected void onClose() {
        
    }
    public boolean isOpen() {
        return isOpen;
    }
    public Tile[][] getTiles() {
        return box;
    }
}
