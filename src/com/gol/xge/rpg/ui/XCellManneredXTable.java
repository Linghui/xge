package com.gol.xge.rpg.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;


/*
 * this is a special ManneredXTable
 * which can not choose the cell without object
 */
public class XCellManneredXTable extends ManneredXTable {

    public XCellManneredXTable(int row, int col) {
        super(row, col);
    }
    
    @Override
    public void click(float x, float y) {

        Actor actor = hit(x, y, true);
        if( actor instanceof XCell ){
            if( ((XCell)actor).getObject() == null ){
                return;
            }
        }
        super.click(x, y);
    }

}
