package war.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class WarGame extends Game{

	@Override
	public void create() {
		  setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log("DESTROYED", "OH NO");
		getScreen().dispose();
	}

}
