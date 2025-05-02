package ru.samsung.mysteryforest;

import static ru.samsung.mysteryforest.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class SpaceButton {

        BitmapFont font;
        String text;
        float x, y;
        float width, height;

        public SpaceButton(BitmapFont font, float x, float y,  String text) {
            this.font = font;
            this.text = text;
            this.y = y;
            this.x = x;
            GlyphLayout glyphLayout = new GlyphLayout(font, text);
            width = glyphLayout.width;
            height = glyphLayout.height;

        }
        public SpaceButton(BitmapFont font, float y, String text) {
            this.font = font;
            this.text = text;
            this.y = y;
            GlyphLayout glyphLayout = new GlyphLayout(font, text);
            width = glyphLayout.width;
            height = glyphLayout.height;
            this.x = SCR_WIDTH/2-width/2;

        }

        public boolean hit(float tx, float ty) {
            return(x < tx && tx < x + width && y > ty && ty > y - height);

        }

        public void setText(String text){
            this.text = text;
            GlyphLayout glyphLayout = new GlyphLayout(font, text);
            width = glyphLayout.width;
            height = glyphLayout.height;

        }
    }


