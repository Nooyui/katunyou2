package aging.health.katunyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;


public class Register extends AppCompatActivity {


    Button btnNext_to_page3;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Register";
    // private EditText textpost;
    // private Button buttonpost ;
    // private EditText textname;
    private String hello;

    private void postData(String userId, final String post, String name) {

        Post itempost = new Post(name, post);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userId).setValue(itempost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        mAuth = FirebaseAuth.getInstance();

        // buttonpost = (Button)findViewById(R.id.button2);
        // textpost  = (EditText)findViewById(R.id.textpost);
        //textname  = (EditText)findViewById(R.id.textname);
        hello = getIntent().getStringExtra("KEY");
        Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        btnNext_to_page3 = (Button) findViewById(R.id.button_next_to_page3);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        findViewById(R.id.subscribeButton).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.unsubscribeButton).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.logTokenButton).setOnClickListener((View.OnClickListener) this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribeButton:
                FirebaseMessaging.getInstance().subscribeToTopic("droiddev/news");
                Log.d(TAG, "SubscribeToTopic");
                Toast.makeText(Register.this, "SubscribeToTopic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.unsubscribeButton:
                FirebaseMessaging.getInstance().unsubscribeFromTopic("droiddev/news");
                Log.d(TAG, "UnsubscribeFromTopic");
                Toast.makeText(Register.this, "UnsubscribeFromTopic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logTokenButton:
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Token : " + token);
                Toast.makeText(Register.this, "Token : " + token, Toast.LENGTH_SHORT).show();
                break;
        }

        // buttonpost.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View view) {

        //  String gettextpost = textpost.getText().toString();
        // String gettextname = textname.getText().toString();
        // postData(FirebaseAuth.getInstance().getCurrentUser().getUid(),gettextpost, gettextname);
        //}
        // });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(Register.this, "Hi", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btnNext_to_page3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), Register_page2.class);
                //intent.putExtra("text1", et.getText().toString());
                startActivity(intent);
            }
        });


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
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                return true;



            case R.id.menu_logout:
                if(mAuth!=null){
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
}
