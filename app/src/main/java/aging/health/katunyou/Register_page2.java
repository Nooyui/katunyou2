package aging.health.katunyou;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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


public class Register_page2 extends AppCompatActivity {



    private EditText textLastname,textName,textAge , textWeight , textHigh,textPhone;
    Button btnNext_to_page4;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;


    private String name , lastname , sex ,age,weight,high,phone_number;
    private void postData ( String userId , String name, String lastname,String sex, String age ,String weight,String high ){

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
        setContentView(R.layout.page_register2);
        mAuth = FirebaseAuth.getInstance();

       Intent intent = getIntent();
        name = intent.getStringExtra("getname");
        lastname = intent.getStringExtra("getlastname");
        sex = intent.getStringExtra("getsex");

        textLastname = (EditText) findViewById(R.id.textLastname);
        textName = (EditText) findViewById(R.id.textName);
        textAge = (EditText) findViewById(R.id.editText);
        textWeight = (EditText) findViewById(R.id.editText3);
        textHigh = (EditText) findViewById(R.id.editText4);
        RadioButton radio_men = (RadioButton) findViewById(R.id.radioButtonMen);
        RadioButton radio_women = (RadioButton) findViewById(R.id.radioButtonWomen);
        btnNext_to_page4 = (Button) findViewById(R.id.button_next_to_page4);






        btnNext_to_page4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), Register_page3.class);

                String gettextAge= textAge.getText().toString();
                String gettextweigh = textWeight.getText().toString();
                String gettexthigh = textHigh.getText().toString();

                intent.putExtra("getage",textAge.getText().toString());
                intent.putExtra("getweigh",textWeight.getText().toString());
                intent.putExtra("gethigh",textHigh.getText().toString());

                postData(FirebaseAuth.getInstance().getCurrentUser().getUid(),name, lastname,sex, gettextAge,gettextweigh,gettexthigh);
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
                        Toast.makeText(Register_page2.this, "Hi", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }





}
