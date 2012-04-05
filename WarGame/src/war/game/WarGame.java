package war.game;

import com.badlogic.gdx.Game;

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
