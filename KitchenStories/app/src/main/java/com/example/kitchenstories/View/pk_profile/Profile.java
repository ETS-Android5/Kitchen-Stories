package com.example.kitchenstories.View.pk_profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Create;
import com.example.kitchenstories.View.MainActivity;
import com.example.kitchenstories.View.MainLoginActivity;
import com.example.kitchenstories.View.Search;
import com.example.kitchenstories.View.Shopping;
import com.example.kitchenstories.ViewModel.ViewPagerAdapter;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;

public class Profile extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private ImageView image_avatarUser_ProfileActivity;
    private TextView name_user_ProfileActivity;
    private TextView name_groupUser_ProfileActivity;
    private Button btn_LogOut_ProfileActivity;
    BottomNavigationView bottomNavigationView;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    LoginButton fb_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        //
        transparentStatusAndNavigation();

        // find by id
        findByIdForComponents();



        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add fragment
        //viewPagerAdapter.AddFragment(new fm_Profile_tab1(), "Cookbooks");
        viewPagerAdapter.AddFragment(new fm_Profile_tab2(), "Recipes");
        viewPagerAdapter.AddFragment(new fm_Profile_tab3(), "Likes");


        // view set adapter
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        // get email user
        checkUser();

        btn_LogOut_ProfileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();

                LoginManager.getInstance().logOut();

                checkUser();
            }
        });


        //


        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.today:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), Create.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.shopping:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        return true;

                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }

    public void findByIdForComponents(){

        tabLayout = findViewById(R.id.tabs_ProfileActivity);
        viewPager = findViewById(R.id.view_pager_ProfileActivity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        image_avatarUser_ProfileActivity = findViewById(R.id.image_avatarUser_ProfileActivity);
        btn_LogOut_ProfileActivity = findViewById(R.id.btn_LogOut_ProfileActivity);
        name_user_ProfileActivity = findViewById(R.id.name_user_ProfileActivity);
        name_groupUser_ProfileActivity = findViewById(R.id.name_groupUser_ProfileActivity);

        //fb_login=findViewById(R.id.fb_login);
    }

    private void checkUser(){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser == null){
            // user not logged in
            startActivity(new Intent(Profile.this, MainLoginActivity.class));
            finish();
        }
        else{
            // user logged in
            // get user info

            String name = firebaseUser.getDisplayName();
            name_user_ProfileActivity.setText(name.toString());

            // load avatar user
            String urlLink = firebaseUser.getPhotoUrl().toString();
            LoadImage loadImage = new LoadImage(image_avatarUser_ProfileActivity);
            loadImage.execute(urlLink);
        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;
        public LoadImage(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;

            try{
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            catch (IOException e){
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image_avatarUser_ProfileActivity.setImageBitmap(bitmap);

        }
    }


    // Transparent Status Bar
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
            //getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

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