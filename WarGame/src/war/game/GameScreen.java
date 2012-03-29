package war.game;

import war.cards.components.War;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	
	private Game game;
	private War war;
	private OrthographicCamera guiCam;
	private SpriteBatch batch;
	private Texture card1;
	private Texture card2;
	private Texture cardBorder;
    private Rectangle glViewport;
    private BitmapFont font;
    private CharSequence chars;
    private CharSequence charsDeck1;
    private CharSequence charsDeck2;
    private Music music;

	public GameScreen (Game game)
	{
			this.game = game;
			music = Gdx.audio.newMusic(Gdx.files.internal("data/DST-GameForest.mp3"));
			music.setLooping(true);
			music.setVolume(0.5f);
			music.play();
			
			font = new BitmapFont(Gdx.files.internal("data/consolasfnt.fnt"), Gdx.files.internal("data/consolasfnt_0.png"), false);
			font.setColor(0, 0, 200, 1);
			
			war = new War();
			
			guiCam = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);
			guiCam.position.set(GAME_WIDTH / 2, GAME_HEIGHT / 2, 0);
			batch = new SpriteBatch();
			round();
			glViewport = new Rectangle(0, 0, GAME_WIDTH, GAME_HEIGHT);
			cardBorder = new Texture(Gdx.files.internal("data/cardborder.png"));
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			  war.round();
			  round();
		}
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0, (float) .6, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glViewport((int) glViewport.x, (int) glViewport.y,
                (int) glViewport.width, (int) glViewport.height);
		//gl.glViewport((int) glViewport.x, (int) glViewport.y,
			//	Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		
		guiCam.update();
		guiCam.apply(gl);
		batch.setProjectionMatrix(guiCam.combined);
		batch.begin();
		batch.draw(card1, 10, 10, 0, 0, 142, 192);
		batch.draw(cardBorder, 8, 8, 0, 0, 144, 194);
		font.draw(batch, chars, 100, Gdx.graphics.getHeight()/2 + 50);
		font.draw(batch, charsDeck1, Gdx.graphics.getWidth() - 100, 100);
		font.draw(batch, charsDeck2, 10, Gdx.graphics.getHeight() - 10);
		batch.draw(card2, Gdx.graphics.getWidth()-142-10, Gdx.graphics.getHeight()-192-10, 0, 0, 142, 192);
		batch.draw(cardBorder, Gdx.graphics.getWidth()-144-10, Gdx.graphics.getHeight()-194-10, 0, 0, 144, 194);
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
	private CharSequence displayWhoWon(int i) {
		if (i == -1)
			return "Player 2 wins!";
		else if (i == 0)
			return "Tie game";
		else if (i == 1)
			return "Player 1 wins!";
		else
			return "Error";		
	}
	
	private void round() {
		  card1 = new Texture(Gdx.files.internal("data/" + war.getPlayer1CurrentCard().toString() + ".png"));
	      card2 = new Texture(Gdx.files.internal("data/" + war.getPlayer2CurrentCard().toString() + ".png"));
	      chars = displayWhoWon(war.whoWon());
	      charsDeck1 = war.getPlayer1DeckSize() + "";
		  charsDeck2 = war.getPlayer2DeckSize() + "";
	}
	private static final int GAME_WIDTH = 540;
	private static final int GAME_HEIGHT = 960;

}
