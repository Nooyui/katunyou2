package aging.health.katunyou;


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class Tab1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);

    }


}