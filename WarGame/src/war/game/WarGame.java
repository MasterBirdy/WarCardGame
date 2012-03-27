package war.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarGame implements ApplicationListener{
Random rand = new Random();
private SpriteBatch batch;
private Texture texture;




	@Override
	public void create() {
		  batch = new SpriteBatch();
		  texture = new Texture(Gdx.files.internal("data/c1.png"));
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		   batch.begin();
		   batch.draw(texture, 0, 0, 0, 0, 142, 192);
           batch.end();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		  batch.dispose();
          texture.dispose();	
	}

}
