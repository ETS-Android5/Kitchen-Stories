package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kitchenstories.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainLoginActivity extends AppCompatActivity {
    private static final int GOOGLE_SIGN_IN_REQUEST =112 ;
    FirebaseAuth auth;
    CallbackManager callbackManager;
    String verificationOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        auth=FirebaseAuth.getInstance();
        InitiallizeGoogleLogin();
        InitiallizeFacebook();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent( MainLoginActivity.this,HomeLoginActivity.class));
            finish();
        }
    }

    private void InitiallizeFacebook() {
        LoginButton fb_login=findViewById(R.id.fb_login);
        callbackManager=CallbackManager.Factory.create();

        fb_login.setPermissions("email","public_profile");
        fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Facebook","On Success");
                handleFacebookLogin(loginResult);
            }

            @Override
            public void onCancel() {
                Log.d("Facebook","On Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Facebook","On Error");
            }
        });
    }

    private void handleFacebookLogin(LoginResult loginResult){
        AuthCredential credential= FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());

        auth.signInWithCredential(credential)
                .addOnCompleteListener( MainLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity( new Intent(MainLoginActivity.this,MainActivity.class));
                            FirebaseUser user=auth.getCurrentUser();
                            SendUserData(user);
                            Log.d("Login","Success");
                        }
                        else{
                            Log.d("Login","Error");
                        }
                    }
                });
    }

    private void InitiallizeGoogleLogin() {
        Button google_login=findViewById(R.id.google_login);
        google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoGoogleLogin();
            }
        });
    }


    private void VerifyOtp(String otp){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationOtp,otp);

        auth.signInWithCredential(credential)
                .addOnCompleteListener( MainLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=task.getResult().getUser();
                            SendUserData(user);
                            startActivity( new Intent(MainLoginActivity.this,MainActivity.class));
                        }
                    }
                });
    }

    private void DoGoogleLogin() {
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestId()
                .requestProfile()
                .build();
        GoogleSignInClient googleSignInClient= GoogleSignIn.getClient( MainLoginActivity.this,gso);

        Intent intent=googleSignInClient.getSignInIntent();
        startActivityForResult(intent,GOOGLE_SIGN_IN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check Result come from Google
        if(requestCode==GOOGLE_SIGN_IN_REQUEST){
            Task<GoogleSignInAccount> accountTask= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account=accountTask.getResult(ApiException.class);
                processFirebaseLoginStep(account.getIdToken());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }

    private void processFirebaseLoginStep(String token){
        AuthCredential authCredential= GoogleAuthProvider.getCredential(token,null);
        auth.signInWithCredential(authCredential)
                .addOnCompleteListener( MainLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainLoginActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(MainLoginActivity.this,MainActivity.class));
                            FirebaseUser user=auth.getCurrentUser();
                            SendUserData(user);

                        }
                    }
                });
    }

    private void  SendUserData(FirebaseUser user){
        Log.d("Login Success","Success");
        Log.d("User ",user.getUid());
    }
}