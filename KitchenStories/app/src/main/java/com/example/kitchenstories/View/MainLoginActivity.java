package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

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
import com.google.android.gms.common.SignInButton;
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

    private VideoView videoview_LoginActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        // transparent
        transparentStatusAndNavigation();


        auth=FirebaseAuth.getInstance();
        InitiallizeGoogleLogin();
        InitiallizeFacebook();

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent( MainLoginActivity.this,MainActivity.class));
            finish();
        }

        // play login video
        playLoginVideo();




    }

    public void playLoginVideo(){

        videoview_LoginActivity = findViewById(R.id.videoview_LoginActivity);

        //Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/loginapp-319bc.appspot.com/o/Login%2FLoginVideo2.mp4?alt=media&token=f72b8e60-21aa-45c8-9ae5-cc1b48252d5e");
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.loginvideo2;
        Uri uri = Uri.parse(videoPath);
        videoview_LoginActivity.setVideoURI(uri);


        // SET MUTE TO VIDEO VIEW
        videoview_LoginActivity.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f, 0f);
                mp.setLooping(true);

            }
        });

        videoview_LoginActivity.start();

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
                            startActivity( new Intent(MainLoginActivity.this, MainActivity.class));
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
        SignInButton google_login=findViewById(R.id.google_login);
        google_login.setSize(SignInButton.SIZE_STANDARD);
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

    // Transparent Status Bar part 1
    public void transparentStatusAndNavigation() {
        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            );
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    }

    // Transparent Status Bar part 2
    public void setWindowFlag(final int bits, boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}