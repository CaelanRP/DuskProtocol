package duskProtocol;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
public class Background {

	Texture texture;
	
	public Background(String location) throws IOException{
		texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location));
	}
	
	public void display(int winX, int winY){

		Color.pink.bind();
		texture.bind();
		
		 GL11.glBegin(GL11.GL_QUADS);
		 GL11.glTexCoord2f(0,0);
         GL11.glVertex2f(0,0);
         GL11.glTexCoord2f(1,0);
         GL11.glVertex2f(texture.getTextureWidth(),0);
         GL11.glTexCoord2f(1,1);
         GL11.glVertex2f(texture.getTextureWidth(),texture.getTextureHeight());
         GL11.glTexCoord2f(0,1);
         GL11.glVertex2f(0,texture.getTextureHeight());
        GL11.glEnd();
	}
}
