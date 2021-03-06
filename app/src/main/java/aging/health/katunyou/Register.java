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
import android.widget.EditText;
import android.widget.RadioButton;
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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class Register extends AppCompatActivity  {


    private Button btnNext_to_page3;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "Register";
    private EditText textName;
    private EditText textLastname;
    private RadioButton radio_men;
    private RadioButton radio_women;
    private String Issex = "Men";
    private String hello;




    private String name , lastname , sex ,age,weight,high,phone_number;
    private void postData ( String userId , String name, String lastname,String sex ){

        Post itempost = new Post( name , lastname, sex,age,weight,high,phone_number);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("older").child(userId).setValue(itempost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        });

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.page_register1);
        mAuth = FirebaseAuth.getInstance();
        EventBus.getDefault().register(this);

        textLastname = (EditText) findViewById(R.id.textLastname);
        textName = (EditText) findViewById(R.id.textName);

        hello = getIntent().getStringExtra("KEY");
        Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        btnNext_to_page3 = (Button) findViewById(R.id.button_next_to_page3);
        RadioButton radio_men = (RadioButton) findViewById(R.id.radioButtonMen);
        RadioButton radio_women = (RadioButton) findViewById(R.id.radioButtonWomen);


        radio_men.setChecked(true);

        radio_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Issex = "Men";
                Toast.makeText(Register.this, " Men.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        radio_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Issex = "Women";
                Toast.makeText(Register.this, " Women .",
                        Toast.LENGTH_SHORT).show();
            }
        });





//        if( radio_men.isChecked() ){
//
//            sex = "Men";
//            Toast.makeText(Register.this, " Men failed.",
//                    Toast.LENGTH_SHORT).show();
//
//        }else {
//
//            sex = "Women";
//            Toast.makeText(Register.this, " Women failed.",
//                    Toast.LENGTH_SHORT).show();
//
//        }




        btnNext_to_page3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                String gettextlastname = textLastname.getText().toString();
                String gettextname = textName.getText().toString();


                Intent intent = new Intent(getApplicationContext(), Register_page2.class);


                intent.putExtra("getname",textName.getText().toString());
                intent.putExtra("getlastname",textLastname.getText().toString());
                intent.putExtra("getsex",Issex);

               postData(FirebaseAuth.getInstance().getCurrentUser().getUid(), gettextlastname, gettextname,Issex);
                startActivity(intent);
            }
        });


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
    public void onReceiveMessage(final Register.MessageEvent event){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String message = event.message;
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(Register.this);
                builder.setMessage(message);
                builder.show();
            }
        });
    }





    }
