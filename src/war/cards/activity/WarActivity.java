package war.cards.activity;

import com.badlogic.gdx.backends.android.AndroidApplication;
import war.game.WarGame;
import android.os.Bundle;

public class WarActivity extends AndroidApplication {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new WarGame(), false);
    }
}