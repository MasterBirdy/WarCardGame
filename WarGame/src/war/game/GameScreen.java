package war.game;

import java.util.ArrayList;
import java.util.List;

import war.cards.components.Card;
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
	private Texture background;
	private Texture card1;
	private Texture card2;
	private List<Card> oldCards;
	private Texture warImage; // 350 x 338
	private Texture cardBorder;
	private Rectangle glViewport;
	private BitmapFont font;
	private CharSequence chars;
	private CharSequence charsDeck1;
	private CharSequence charsDeck2;
	private Music music;
	private ArrayList<CardAnimation> animatedCards;
	private ArrayList<CardAnimation> tempAnimatedCards;
	private ArrayList<CardAnimation> downwardAnimatedCards;
	private List<Card> theTempCards;
	private boolean warTime;

	public GameScreen (Game game)
	{
		this.game = game;
		music = Gdx.audio.newMusic(Gdx.files.internal("data/DST-GameForest.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		warImage = new Texture(Gdx.files.internal("data/war.png"));
		background = new Texture(Gdx.files.internal("data/background.png"));

		font = new BitmapFont(Gdx.files.internal("data/consolasfnt.fnt"), Gdx.files.internal("data/consolasfnt_0.png"), false);
		font.setColor(0, 0, 200, 1);

		war = new War();
		animatedCards = new ArrayList<CardAnimation>();
		downwardAnimatedCards = new ArrayList<CardAnimation>();

		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		batch = new SpriteBatch();
		card1 = new Texture(Gdx.files.internal("data/" + war.getPlayer1CurrentCard().toString() + ".png"));
		card2 = new Texture(Gdx.files.internal("data/" + war.getPlayer2CurrentCard().toString() + ".png"));
		doOldCards();
		round();
		glViewport = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cardBorder = new Texture(Gdx.files.internal("data/cardborder.png"));
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
			Gdx.app.log("theWar", war.whoWon() + "");
			doOldCards();
			war.round();
			round();
		}

		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0, (float) .6, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// gl.glViewport((int) glViewport.x, (int) glViewport.y,
		//		(int) glViewport.width, (int) glViewport.height);
		gl.glViewport((int) glViewport.x, (int) glViewport.y,
				Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		guiCam.update();
		guiCam.apply(gl);
		batch.setProjectionMatrix(guiCam.combined);
		batch.begin();
		batch.draw(background, 0, 0, 512-Gdx.graphics.getWidth()/2-20, 512-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//batch.draw(background, 0, 0, 100, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(warImage, Gdx.graphics.getWidth()/2 - 350/2 - 20, Gdx.graphics.getHeight()/2 - 338/2, 0, 0, 350, 338);
		tempAnimatedCards = new ArrayList<CardAnimation>();
		theTempCards = new ArrayList<Card>();
		for (CardAnimation ca : downwardAnimatedCards)
		{
			ca.setX(Gdx.graphics.getDeltaTime() * 350.0f);
			float tempY = (10.0f - (Gdx.graphics.getHeight()-192.0f-10.0f)) / (10.0f - (Gdx.graphics.getWidth() -142.0f -10.0f));
			ca.setY(Gdx.graphics.getDeltaTime() * 350.0f * tempY);
			if (ca.getX() > 10 )
			{
				tempAnimatedCards.add(ca);
				if (ca.getX() < Gdx.graphics.getWidth() - 142 * 2 - 10)
				{
					batch.draw(ca.getTexture(), ca.getX(), ca.getY(), 0, 0, 142, 192);
				}
			}
		}
		downwardAnimatedCards = tempAnimatedCards;

		batch.draw(cardBorder, 8, 8, 0, 0, 146, 196);
		batch.draw(cardBorder, Gdx.graphics.getWidth()-144-10, Gdx.graphics.getHeight()-194-10, 0, 0, 146, 196);
		batch.draw(card1, 10, 10, 0, 0, 142, 192);
		tempAnimatedCards = new ArrayList<CardAnimation>();
		for (CardAnimation ca : animatedCards)
		{
			ca.setX(Gdx.graphics.getDeltaTime() * 350.0f);
			float tempY = (10.0f - (Gdx.graphics.getHeight()-192.0f-10.0f)) / (10.0f - (Gdx.graphics.getWidth() -142.0f -10.0f));
			ca.setY(Gdx.graphics.getDeltaTime() * 350.0f * tempY);
			if (ca.getX() < Gdx.graphics.getWidth() - 142)
			{
				tempAnimatedCards.add(ca);
				if (ca.getX() > 10 )
				{
					batch.draw(ca.getTexture(), ca.getX(), ca.getY(), 0, 0, 142, 192);
				}
			}
		}
		animatedCards = tempAnimatedCards;
		/*	else if (oldCardX < Gdx.graphics.getWidth())
		{
			int i = (int) (142-(oldCardX-Gdx.graphics.getWidth()));
			batch.draw(oldCard, oldCardX, 10, 0, 0, i, 192);
		}*/

		font.draw(batch, chars,  Gdx.graphics.getWidth()/2 - font.getSpaceWidth() * chars.length() / 2 + 23, Gdx.graphics.getHeight()/2 - 338/2 - 10);
		font.draw(batch, charsDeck1, Gdx.graphics.getWidth() - 100, 100);
		font.draw(batch, charsDeck2, 10, Gdx.graphics.getHeight() - 10);
		batch.draw(card2, Gdx.graphics.getWidth()-142-10, Gdx.graphics.getHeight()-192-10, 0, 0, 142, 192);
		tempAnimatedCards = new ArrayList<CardAnimation>();
		for (CardAnimation ca : downwardAnimatedCards)
		{
			if (ca.getX() > 10 )
			{
				tempAnimatedCards.add(ca);
				if (ca.getX() > Gdx.graphics.getWidth() - 142 * 2 - 10 && ca.getX() < Gdx.graphics.getWidth() - 142 - 10)
				{
					batch.draw(ca.getTexture(), ca.getX(), ca.getY(), 0, 0, 142, 192);
				}
			}
		}
		downwardAnimatedCards = tempAnimatedCards;
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
		if (i == PLAYER_2_WINS)
			return "Player 2 wins!";
		else if (i == WAR)
			return "War!";
		else if (i == PLAYER_1_WINS)
			return "Player 1 wins!";
		else
			return "Error";		
	}

	private void round() {
		int whoWon = war.whoWon();
		card1 = new Texture(Gdx.files.internal("data/" + war.getPlayer1CurrentCard().toString() + ".png"));
		card2 = new Texture(Gdx.files.internal("data/" + war.getPlayer2CurrentCard().toString() + ".png"));
		chars = displayWhoWon(whoWon);
		charsDeck1 = war.getPlayer1DeckSize() + "";
		charsDeck2 = war.getPlayer2DeckSize() + "";
	}

	private void doOldCards()
	{
		int whoWon = war.whoWon();
		oldCards = war.getOldCards();
		int i = 0;
		if (whoWon == PLAYER_2_WINS)
		{
			for (Card c : oldCards) 
			{
				if (warTime)
				{
					for (Card d : theTempCards)
					{
						animatedCards.add(new CardAnimation(new Texture(Gdx.files.internal("data/" + d.toString() + ".png")), true, i));
						i += CARD_SPEED;
					}
					theTempCards = new ArrayList<Card>();
					warTime = false;
				}
				animatedCards.add(new CardAnimation(new Texture(Gdx.files.internal("data/" + c.toString() + ".png")), true, i));
				i += CARD_SPEED;
				Gdx.app.log("LOGMESIZE", animatedCards.size() + "");
			}
		}
		else if (whoWon == PLAYER_1_WINS)
		{
			for (Card c : oldCards)
			{
				if (warTime) 
				{
					for (Card d : theTempCards)
					{
						downwardAnimatedCards.add(new CardAnimation(new Texture(Gdx.files.internal("data/" + d.toString() + ".png")), false, i));
						i += CARD_SPEED;
					}
					theTempCards = new ArrayList<Card>();
					warTime = false;
				}
				downwardAnimatedCards.add(new CardAnimation(new Texture(Gdx.files.internal("data/" + c.toString() + ".png")), false, i));
				i += CARD_SPEED;
				Gdx.app.log("LOGMESIZE", downwardAnimatedCards.size() + "");
			}
		}
		else if (whoWon == WAR)
		{
			theTempCards.addAll(oldCards);
			warTime = true;
		}
	}

	private class CardAnimation {

		private Texture t;
		private float x; 
		private float y;
		private boolean goUpward;

		private CardAnimation(Texture t, boolean b)
		{
			this.t = t;
			goUpward = b;
			if (b) 
			{
				x = 10;
				y = 10;
			}
			else
			{
				x = Gdx.graphics.getWidth()-142-10;
				y = Gdx.graphics.getHeight()-192-10;
			}
		}

		private CardAnimation(Texture t, boolean b, int i)
		{
			this.t = t;
			goUpward = b;
			if (b) 
			{
				x = 10 - i;
				float tempY = (10.0f - (Gdx.graphics.getHeight()-192.0f-10.0f)) / (10.0f - (Gdx.graphics.getWidth() -142.0f -10.0f));
				y = 10 - i * tempY;
			}
			else
			{
				x = Gdx.graphics.getWidth()-142-10 + i;
				float tempY = (10.0f - (Gdx.graphics.getHeight()-192.0f-10.0f)) / (10.0f - (Gdx.graphics.getWidth() -142.0f -10.0f));
				y = Gdx.graphics.getHeight()-192-10 + i * tempY;
			}
		}

		public void setX(float f)
		{
			if (goUpward)
				x += f;
			else
				x -= f;
		}

		public void setY(float f)
		{
			if (goUpward)
				y += f;
			else
				y -= f;
		}

		public Texture getTexture()
		{
			return t;
		}

		public float getX()
		{
			return x;
		}

		public float getY()
		{
			return y;
		}

		public boolean goUpward()
		{
			return goUpward;
		}
	}

	private static final int GAME_WIDTH = 540;
	private static final int GAME_HEIGHT = 960;
	private static final int PLAYER_2_WINS = -1;
	private static final int WAR = 0;
	private static final int PLAYER_1_WINS = 1;
	private static final int CARD_SPEED = 65;

}


