package aging.health.katunyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    private Button Edituser;
    private Button EditEmCall;
    private Button EditDevice;
    private Button EditHealth;
    private Button EditAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_more);
        Edituser = (Button) findViewById(R.id.button2);
        EditEmCall = (Button) findViewById(R.id.button3);
        EditDevice = (Button) findViewById(R.id.button4);
        EditHealth = (Button) findViewById(R.id.button5);
        EditAbout = (Button) findViewById(R.id.button6);


        Edituser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), EditUser.class);
                startActivity(intent);
            }
        });

        EditEmCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), EditEmberanceCall.class);
                startActivity(intent);
            }
        });

        EditDevice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), EditDevice.class);
                startActivity(intent);
            }
        });

        EditHealth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), EditHealth.class);
                startActivity(intent);
            }
        });

        EditAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), About.class);
                startActivity(intent);
            }
        });



    }

}
