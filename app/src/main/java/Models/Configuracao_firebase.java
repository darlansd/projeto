package Models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Configuracao_firebase {
    private static FirebaseAuth auth;
    private static DatabaseReference database;

    public static FirebaseAuth getfirebaseauth(){

        if (auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static DatabaseReference getfirebasedatabase(){

        if (database == null){
            database = FirebaseDatabase.getInstance().getReference();
        }

        return database;
    }




}
