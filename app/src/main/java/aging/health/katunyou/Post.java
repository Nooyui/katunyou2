package aging.health.katunyou;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.required;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String name , lastname, sex,age ,weight,high,phone_number,heart_rate,walking_rate,calories_rate,sleep_time,emergency_call,emergency_name ;


    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String name, String lastname ,String sex) {
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.high = high;
        this.phone_number = phone_number;
        this.heart_rate = heart_rate;
        this.walking_rate = walking_rate;
        this.calories_rate = calories_rate;
        this.sleep_time = sleep_time;
        this.emergency_call = emergency_call;
        this.emergency_name = emergency_name;


    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("lastname", lastname);
        result.put("sex", sex);
       // result.put("age", age);
        //result.put("weight", weight);
       // result.put("high", high);
       // result.put("phone_number", phone_number);
       // result.put("heart_rate", heart_rate);
       // result.put("walking_rate", walking_rate);
       // result.put("calories_rate", calories_rate);
       // result.put("sleep_time", sleep_time);
       // result.put("emergency_call", emergency_call);
       // result.put("emergency_name", emergency_name);




        return result;
    }
    // [END post_to_map]

}
// [END post_class]
