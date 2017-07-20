package aging.health.katunyou;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class SettingActivity extends AppCompatActivity {

    private static final String TAG = "SettingActivity";



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.setting);

        //FirebaseApp.initializeApp(this);


        // Get token
        String token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        String msg = getString(R.string.msg_token_fmt, token);
        Log.d(TAG, msg);
        Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();


        //Load Pref
        //settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //settings = PreferenceManager.getDefaultSharedPreferences(this);


    }





//        Button subscribeButton = (Button) findViewById(R.id.subscribeButton);
//        subscribeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            // [START subscribe_topics]
//            FirebaseMessaging.getInstance().subscribeToTopic("news");
//            // [END subscribe_topics]
//
//            // Log and toast
//            String msg = getString(R.string.msg_subscribed);
//            Log.d(TAG, msg);
//            Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//
//            FirebaseMessaging.getInstance().subscribeToTopic("0001pm10");
//            //FirebaseMessaging.getInstance().unsubscribeFromTopic();
//            // [END subscribe_topics]
//
//            // Log and toast
//            msg = getString(R.string.msg_subscribed);
//            Log.d(TAG, msg);
//            Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//            FirebaseMessaging.getInstance().subscribeToTopic("0001pm25");
//            // [END subscribe_topics]
//
//            // Log and toast
//            msg = getString(R.string.msg_subscribed);
//            Log.d(TAG, msg);
//            Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();
//
//            }
//        });


//        Button logTokenButton = (Button) findViewById(R.id.logTokenButton);
//        logTokenButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get token
//                String token = FirebaseInstanceId.getInstance().getToken();
//
//                // Log and toast
//                String msg = getString(R.string.msg_token_fmt, token);
//                Log.d(TAG, msg);
//                Toast.makeText(SettingActivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });


    @Override
    protected void onStop() {
        super.onStop();
        finish();

    }

    @Override
	public void onDestroy() {
		super.onDestroy();
		//exitDialog();
	}

    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_setting, menu);
        //return true;
   // }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                return true;

           // case R.id.hardware:
                //intent = new Intent(getApplicationContext(), Device.class);
                //startActivity(intent);
               // return true;

            //case R.id.usb:
                //intent = new Intent(getApplicationContext(), UsbDevice.class);
               // startActivity(intent);
               // return true;

          //  case R.id.about:
               // intent = new Intent(getApplicationContext(), About.class);
               // startActivity(intent);
             //   return true;

           // case R.id.exit:
                //System.exit(0);
                //return true;

            default:
                return false;
        }
    }
	
    
}
