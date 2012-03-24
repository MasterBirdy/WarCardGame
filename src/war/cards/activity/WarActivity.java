package war.cards.activity;

import war.cards.components.R;
import war.cards.components.R.layout;
import android.app.Activity;
import android.os.Bundle;

public class WarActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}