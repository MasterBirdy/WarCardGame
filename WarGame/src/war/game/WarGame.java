package war.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarGame extends Game{

	@Override
	public void create() {
		  setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
	}

}
