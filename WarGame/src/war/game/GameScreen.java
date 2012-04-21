package war.game;

import java.util.ArrayList;
import java.util.HashMap;
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
	private Texture player1Wins;
	private Texture player2Wins;
	private Texture playerWARWins;
	private List<Card> oldCards;
	private Texture warImage; // 350 x 338
	private Texture cardBorder;
	private Rectangle glViewport;
	private BitmapFont font;
	private CharSequence charsDeck1;
	private CharSequence charsDeck2;
	private Texture pictureWinner;
	private Music music;
	private ArrayList<CardAnimation> animatedCards;
	private ArrayList<CardAnimation> tempAnimatedCards;
	private ArrayList<CardAnimation> downwardAnimatedCards;
	private List<Card> theTempCards;
	//	private List<Card> theTempTempCards;
	//	private List<CardAnimation> tempCardAnimations;
	private boolean warTime;
	private int winnerX;
	private int winnerY;
	private HashMap<String, Texture> cardMap;
	
	private Texture cardBackground = new Texture(Gdx.files.internal("data/cardbackground.png"));
	private Texture c1 = new Texture(Gdx.files.internal("data/c1.png"));
	private Texture c2 = new Texture(Gdx.files.internal("data/c2.png"));
	private Texture c3 = new Texture(Gdx.files.internal("data/c3.png")); 
	private Texture c4 = new Texture(Gdx.files.internal("data/c4.png"));
	private Texture c5 = new Texture(Gdx.files.internal("data/c5.png"));
	private Texture c6 = new Texture(Gdx.files.internal("data/c6.png"));
	private Texture c7 = new Texture(Gdx.files.internal("data/c7.png"));
	private Texture c8 = new Texture(Gdx.files.internal("data/c8.png"));
	private Texture c9 = new Texture(Gdx.files.internal("data/c9.png"));
	private Texture c10 = new Texture(Gdx.files.internal("data/c10.png"));
	private Texture cj = new Texture(Gdx.files.internal("data/cj.png"));
	private Texture cq = new Texture(Gdx.files.internal("data/cq.png"));
	private Texture ck = new Texture(Gdx.files.internal("data/ck.png"));

	private Texture d1 = new Texture(Gdx.files.internal("data/d1.png"));
	private Texture d2 = new Texture(Gdx.files.internal("data/d2.png"));
	private Texture d3 = new Texture(Gdx.files.internal("data/d3.png"));
	private Texture d4 = new Texture(Gdx.files.internal("data/d4.png"));
	private Texture d5 = new Texture(Gdx.files.internal("data/d5.png"));
	private Texture d6 = new Texture(Gdx.files.internal("data/d6.png"));
	private Texture d7 = new Texture(Gdx.files.internal("data/d7.png"));
	private Texture d8 = new Texture(Gdx.files.internal("data/d8.png"));
	private Texture d9 = new Texture(Gdx.files.internal("data/d9.png"));
	private Texture d10 = new Texture(Gdx.files.internal("data/d10.png"));
	private Texture dj = new Texture(Gdx.files.internal("data/dj.png"));
	private Texture dq = new Texture(Gdx.files.internal("data/dq.png"));
	private Texture dk = new Texture(Gdx.files.internal("data/dk.png")); 

	private Texture h1 = new Texture(Gdx.files.internal("data/h1.png"));
	private Texture h2  = new Texture(Gdx.files.internal("data/h2.png"));
	private Texture h3  = new Texture(Gdx.files.internal("data/h3.png"));
	private Texture h4 = new Texture(Gdx.files.internal("data/h4.png"));
	private Texture h5  = new Texture(Gdx.files.internal("data/h5.png"));
	private Texture h6  = new Texture(Gdx.files.internal("data/h6.png"));
	private Texture h7  = new Texture(Gdx.files.internal("data/h7.png"));
	private Texture h8  = new Texture(Gdx.files.internal("data/h8.png"));
	private Texture h9  = new Texture(Gdx.files.internal("data/h9.png"));
	private Texture h10  = new Texture(Gdx.files.internal("data/h10.png"));
	private Texture hj  = new Texture(Gdx.files.internal("data/hj.png"));
	private Texture hq  = new Texture(Gdx.files.internal("data/hq.png"));
	private Texture hk  = new Texture(Gdx.files.internal("data/hk.png"));

	private Texture s1  = new Texture(Gdx.files.internal("data/s1.png"));
	private Texture s2 = new Texture(Gdx.files.internal("data/s2.png"));
	private Texture s3  = new Texture(Gdx.files.internal("data/s3.png"));
	private Texture s4  = new Texture(Gdx.files.internal("data/s4.png"));
	private Texture s5  = new Texture(Gdx.files.internal("data/s5.png"));
	private Texture s6  = new Texture(Gdx.files.internal("data/s6.png"));
	private Texture s7  = new Texture(Gdx.files.internal("data/s7.png"));
	private Texture s8  = new Texture(Gdx.files.internal("data/s8.png"));
	private Texture s9  = new Texture(Gdx.files.internal("data/s9.png"));
	private Texture s10  = new Texture(Gdx.files.internal("data/s10.png"));
	private Texture sj  = new Texture(Gdx.files.internal("data/sj.png"));
	private Texture sq  = new Texture(Gdx.files.internal("data/sq.png"));
	private Texture sk  = new Texture(Gdx.files.internal("data/sk.png"));

	public GameScreen (Game game)
	{
		cardMap = new HashMap<String, Texture>();
		createCards();
		this.game = game;
		music = Gdx.audio.newMusic(Gdx.files.internal("data/DST-GameForest.mp3"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		warImage = new Texture(Gdx.files.internal("data/war.png"));
		background = new Texture(Gdx.files.internal("data/background.png"));
		player1Wins = new Texture(Gdx.files.internal("data/player1wins.png"));
		player2Wins = new Texture(Gdx.files.internal("data/player2wins.png"));
		playerWARWins = new Texture(Gdx.files.internal("data/wartext.png"));

		font = new BitmapFont(Gdx.files.internal("data/consolasfnt.fnt"), Gdx.files.internal("data/consolasfnt_0.png"), false);
		font.setColor(0, 0, 200, 1);

		war = new War();
		animatedCards = new ArrayList<CardAnimation>();
		downwardAnimatedCards = new ArrayList<CardAnimation>();

		guiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		guiCam.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		batch = new SpriteBatch();
		card1 = cardBackground;  
		card2 = cardBackground;
		winnerY = 0;
		//doOldCards();
		//round();
		glViewport = new Rectangle(0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cardBorder = new Texture(Gdx.files.internal("data/cardborder.png"));
		tempAnimatedCards = new ArrayList<CardAnimation>();
		theTempCards = new ArrayList<Card>();
		pictureWinner = playerWARWins;
		charsDeck1 = war.getPlayer1DeckSize() + "";
		charsDeck2 = war.getPlayer2DeckSize() + "";
		//	theTempTempCards = new ArrayList<Card>();
		//	tempCardAnimations = new ArrayList<CardAnimation>();
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.justTouched()) {
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
		batch.disableBlending();
		batch.begin();
		batch.draw(background, 0, 0, 512-Gdx.graphics.getWidth()/2-20, 512-Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.end();
		batch.enableBlending();
		batch.begin();
		//batch.draw(background, 0, 0, 100, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(warImage, Gdx.graphics.getWidth()/2 - 350/2 - 20, Gdx.graphics.getHeight()/2 - 338/2, 0, 0, 350, 338);
		tempAnimatedCards.removeAll(downwardAnimatedCards);

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
		downwardAnimatedCards.clear();
		downwardAnimatedCards.addAll(tempAnimatedCards);

		batch.draw(cardBorder, 8, 8, 0, 0, 146, 196);
		batch.draw(cardBorder, Gdx.graphics.getWidth()-144-10, Gdx.graphics.getHeight()-194-10, 0, 0, 146, 196);
		batch.draw(card1, 10, 10, 0, 0, 142, 192);
		tempAnimatedCards.removeAll(downwardAnimatedCards);
		for (CardAnimation ca : animatedCards)
		{
			ca.setX(Gdx.graphics.getDeltaTime() * 350.0f);
			float tempY = (10.0f - (Gdx.graphics.getHeight()-192.0f-10.0f)) / (10.0f - (Gdx.graphics.getWidth() -142.0f -10.0f));
			ca.setY(Gdx.graphics.getDeltaTime() * 350.0f * tempY);
			if (ca.getX() < Gdx.graphics.getWidth() - 142 - 10)
			{
				tempAnimatedCards.add(ca);
				if (ca.getX() > 10 )
				{
					batch.draw(ca.getTexture(), ca.getX(), ca.getY(), 0, 0, 142, 192);
				}
			}
		}
		animatedCards.clear();
		animatedCards.addAll(tempAnimatedCards);
		/*	else if (oldCardX < Gdx.graphics.getWidth())
		{
			int i = (int) (142-(oldCardX-Gdx.graphics.getWidth()));
			batch.draw(oldCard, oldCardX, 10, 0, 0, i, 192);
		}*/

		batch.draw(pictureWinner,  Gdx.graphics.getWidth()/2 - pictureWinner.getWidth() / 2 , Gdx.graphics.getHeight()/2 - pictureWinner.getHeight() /2f, 0, 0, winnerX, winnerY);
		font.draw(batch, charsDeck1, Gdx.graphics.getWidth() - 100, 100);
		font.draw(batch, charsDeck2, 10, Gdx.graphics.getHeight() - 10);
		batch.draw(card2, Gdx.graphics.getWidth()-142-10, Gdx.graphics.getHeight()-192-10, 0, 0, 142, 192);
		tempAnimatedCards.removeAll(animatedCards);
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
		downwardAnimatedCards.clear();
		downwardAnimatedCards.addAll(tempAnimatedCards);
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
		Gdx.app.log("DESTROYED", "GRRRR");


	}

	private Texture displayWhoWon(int i) {
		if (i == PLAYER_2_WINS)
			return player2Wins;
		else if (i == PLAYER_1_WINS)
			return player1Wins;
		else
			return playerWARWins;	
	}

	private void round() {
		int whoWon = war.whoWon();
		card1 = cardMap.get(war.getPlayer1CurrentCard().toString());  
		card2 = cardMap.get(war.getPlayer2CurrentCard().toString());
		pictureWinner = displayWhoWon(whoWon);
		if (whoWon == 1 || whoWon == -1)
		{
			winnerX = 349;
			winnerY = 53;
		}
		else if (whoWon == 0)
		{
			winnerX = 118;
			winnerY = 46;
		}

		charsDeck1 = war.getPlayer1DeckSize() + "";
		charsDeck2 = war.getPlayer2DeckSize() + "";
	}

	private void doOldCards()
	{
		if (!war.isFirstTime()) {
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
							animatedCards.add(new CardAnimation(cardMap.get(d.toString()), true, i));
							i += CARD_SPEED;
						}
						theTempCards.clear();
						warTime = false;
					}
					animatedCards.add(new CardAnimation(cardMap.get(c.toString()), true, i));
					i += CARD_SPEED;
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
							downwardAnimatedCards.add(new CardAnimation(cardMap.get(d.toString()), false, i));
							i += CARD_SPEED;
						}
						theTempCards.clear();
						warTime = false;
					}
					downwardAnimatedCards.add(new CardAnimation(cardMap.get(c.toString()), false, i));
					i += CARD_SPEED;
				}
			}
			else if (whoWon == WAR)
			{
				theTempCards.addAll(oldCards);
				warTime = true;
			}
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


	private void createCards() {
		cardMap.put("c1", c1);
		cardMap.put("c2", c2);
		cardMap.put("c3", c3);
		cardMap.put("c4", c4);
		cardMap.put("c5", c5);
		cardMap.put("c6", c6);
		cardMap.put("c7", c7);
		cardMap.put("c8", c8);
		cardMap.put("c9", c9);
		cardMap.put("c10", c10);
		cardMap.put("cj", cj);
		cardMap.put("cq", cq);
		cardMap.put("ck", ck);
		cardMap.put("d1", d1);
		cardMap.put("d2", d2);
		cardMap.put("d3", d3);
		cardMap.put("d4", d4);
		cardMap.put("d5", d5);
		cardMap.put("d6", d6);
		cardMap.put("d7", d7);
		cardMap.put("d8", d8);
		cardMap.put("d9", d9);
		cardMap.put("d10", d10);
		cardMap.put("dj", dj);
		cardMap.put("dq", dq);
		cardMap.put("dk", dk);
		cardMap.put("h1", h1);
		cardMap.put("h2", h2);
		cardMap.put("h3", h3);
		cardMap.put("h4", h4);
		cardMap.put("h5", h5);
		cardMap.put("h6", h6);
		cardMap.put("h7", h7);
		cardMap.put("h8", h8);
		cardMap.put("h9", h9);
		cardMap.put("h10", h10);
		cardMap.put("hj", hj);
		cardMap.put("hq", hq);
		cardMap.put("hk", hk);
		cardMap.put("s1", s1);
		cardMap.put("s2", s2);
		cardMap.put("s3", s3);
		cardMap.put("s4", s4);
		cardMap.put("s5", s5);
		cardMap.put("s6", s6);
		cardMap.put("s7", s7);
		cardMap.put("s8", s8);
		cardMap.put("s9", s9);
		cardMap.put("s10", s10);
		cardMap.put("sj", sj);
		cardMap.put("sq", sq);
		cardMap.put("sk", sk);
	}

	private static final int GAME_WIDTH = 540;
	private static final int GAME_HEIGHT = 960;
	private static final int PLAYER_2_WINS = -1;
	private static final int WAR = 0;
	private static final int PLAYER_1_WINS = 1;
	private static final int CARD_SPEED = 65;

}


