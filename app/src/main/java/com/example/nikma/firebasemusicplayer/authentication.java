package com.example.nikma.firebasemusicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class authentication extends AppCompatActivity {
    List<AuthUI.IdpConfig> providers;
    private static final int RC_SIGN_IN = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() !=null)
        {
            //user already exists
        }else
        {
            providers = new ArrayList<>();
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                    new AuthUI.IdpConfig.GoogleBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RC_SIGN_IN ){
            if(resultCode == RESULT_OK){
                Intent i = new Intent(authentication.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

}
