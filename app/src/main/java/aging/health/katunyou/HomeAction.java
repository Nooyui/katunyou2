package aging.health.katunyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeAction extends AppCompatActivity {
    TabHost tabhost;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "HomeAction";
    private String hello;
     Button EmergencyCall_button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_action_3);
        mAuth = FirebaseAuth.getInstance();
        EventBus.getDefault().register(this);
        hello = getIntent().getStringExtra("KEY");
        Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        EmergencyCall_button = (Button) findViewById(R.id.button_call_3);
        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("แม่");
        spec.setContent(R.id.tab1);
        spec.setIndicator("แม่");
        host.addTab(spec);
        EmergencyCall_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), EmergencyCall.class);
                startActivity(intent);
            }
        });


        //Tab 2
        spec = host.newTabSpec("กล้อง");
        spec.setContent(R.id.tab2);
        spec.setIndicator("กล้อง");
        host.addTab(spec);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        //Toast.makeText(HomeAction.this, "Hi", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
                return true;

            case R.id.menu_token:
                Intent intent_i = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent_i);
                return true;


            case R.id.menu_logout:
                if (mAuth != null) {
                    mAuth.signOut();
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                return true;

            case R.id.menu_exit:
                System.exit(0);
                return true;

            default:
                return false;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static final class MessageEvent {
        public String message;

        public MessageEvent(String message) {
            this.message = message;
        }
    }

    @Subscribe
    public void onReceiveMessage(final Register.MessageEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = event.message;
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(HomeAction.this);
                builder.setMessage(message);
                builder.show();
            }
        });
    }
}