package com.example.kitchenstories.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;

import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.Recipe.Recipe;
import com.example.kitchenstories.Model.Recipe.StepsForRecipe;
import com.example.kitchenstories.Model.TypesOfRecipe;
import com.example.kitchenstories.Model.User;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.allRecipe.All_recipes;
import com.example.kitchenstories.View.filterInSearch_AllRecipe.FilterInSearchAllRecipe;
import com.example.kitchenstories.View.profile.Profile;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_Option_Large;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_Option_Medium;
import com.example.kitchenstories.ViewModel.Today_Activity.RecyclerViewAdapter_Option_Part13;
import com.example.kitchenstories.aOthersClass.DotsIndicatorDecoration;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


//<color name="colorPrimary">#6200EE</color>
//<color name="colorPrimaryDark">#3700B3</color>
//<color name="colorAccent">#03DAC5</color>

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    private ImageView image_exhibit_today_activity;
    private CardView cardView_exhibit_today_activity;
    private TextView tv_nameRecipe_exhibit_today_activity;
    private TextView tv_nameAuthor_exhibit_today_activity;
    private Button btn_likes_exhibit_today_activity;

    private Button btn_SeeAll_part1_today_activity;
    private Button btn_SeeAll_part9_today_activity;
    private Button btn_SeeAll_part11_today_activity;
    private Button btn_SeeAll_part12_today_activity;

    private CardView cardView_part3_today_activity;
    private TextView name_story_part3_today_activity;
    private TextView name_author_part3_today_activity;
    private TextView tv_periodCooking_part3_today_activity;
    private ImageView image_story_part3_today_activity;
    private ImageView image_author_part3_today_activity;

    private TextView title_name_part3_today_activity;
    private TextView title_name_part6_today_activity;

    private VideoView videoView_part2;
    private VideoView videoView_part6;
    private VideoView videoView_part8;

    private NestedScrollView nestedScrollView;
    private MediaController mediaController;

    private RecyclerView recyclerView_part1;
    private ArrayList<Recipe> mData_part1;
    private RecyclerViewAdapter_Option_Medium adapter_option_medium_Part1;

    private RecyclerView recyclerView_part4;
    private ArrayList<Recipe> mData_part4;
    private RecyclerViewAdapter_Option_Large adapterOptionLarge_Part4;

    private RecyclerView recyclerView_part7;
    private ArrayList<Recipe> mData_part7;
    private RecyclerViewAdapter_Option_Large adapterOptionLarge_Part7;

    private RecyclerView recyclerView_part9;
    private ArrayList<Recipe> mData_part9;
    private RecyclerViewAdapter_Option_Medium adapterOptionMedium_Part9;

    private RecyclerView recyclerView_part10;
    private ArrayList<Recipe> mData_part10;
    private RecyclerViewAdapter_Option_Large adapterOptionLarge_Part10;

    private RecyclerView recyclerView_part11;
    private ArrayList<Recipe> mData_part11;
    private RecyclerViewAdapter_Option_Medium adapterOptionMedium_Part11;

    private RecyclerView recyclerView_part12;
    private ArrayList<Recipe> mData_part12;
    private RecyclerViewAdapter_Option_Medium adapterOptionMedium_Part12;

    private RecyclerView recyclerView_part13;
    private ArrayList<Recipe> mData_part13;
    private RecyclerViewAdapter_Option_Part13 adapterOptionPart13;


    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    View viewLoading_today_activity;
    ProgressBar progressBar_today_activity;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        //transparentStatusAndNavigation();

        //TransparentStatusAndNavigation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Transparent status bar
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        // start progess bar
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                viewLoading_today_activity.setVisibility(View.VISIBLE);
                progressBar_today_activity.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {

            }
        }.start();

        // add types of recipe
        //addTypeOfRecipe();

        // set recipe into firebase
        //setUpDataAndCreateDataToFirebase();


        // add user in firebase
        addUserIntoFirebase();


        // FIND VIEW BY ID
        //toolbar = findViewById(R.id.toolbar_today_activity);
        findByIdForComponents();



        // BOTTOM NAVIGATION
        bottomNavigationView.setSelectedItemId(R.id.today);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.today:
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;

                    case R.id.create:
                        startActivity(new Intent(getApplicationContext(), Create.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;

                    case R.id.shopping:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        return true;


                }
                return false;
            }
        });




        // Part Exhibit
        getDataExhibitPart();


        // PART2: VIDEO VIEW
        getDataPart2();


        // PART 6: VIDEOVIEW
        getDataPart6();


        // PART8: VIDEOVIEW
        getDataPart8();


        // videoView in localVisible of NestedScroll
        final Rect scrollBounds = new Rect();
        nestedScrollView.getHitRect(scrollBounds);

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                // VIDEOVIEW PART 2 is autoplayed
                if (videoView_part2 != null) {

                    if (videoView_part2.getLocalVisibleRect(scrollBounds)) {

                        //Toast.makeText(MainActivity.this, "VIDEOVIEW APPEAR FULLY", Toast.LENGTH_SHORT).show();

                        // count down timer 2 seconds
                        new CountDownTimer(1000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {

                                videoView_part2.start();

//                                mediaController = new MediaController(MainActivity.this);
//                                videoView.setMediaController(mediaController);
//                                mediaController.setAnchorView(videoView);

                            }
                        }.start();
                    } else {

                        videoView_part2.pause();
                        //Toast.makeText(MainActivity.this, "VIDEOVIEW NO APPEAR", Toast.LENGTH_SHORT).show();
                    }

                }

                //VIDEOVIEW PART 6 is autoplayed
                if (videoView_part6 != null) {

                    if (videoView_part6.getLocalVisibleRect(scrollBounds)) {

                        new CountDownTimer(1000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoView_part6.start();
                            }
                        }.start();
                    } else {
                        videoView_part6.pause();
                    }
                }

                // VIDEOVIEW PART 8 is autoplayed
                if (videoView_part8 != null) {

                    if (videoView_part8.getLocalVisibleRect(scrollBounds)) {

                        new CountDownTimer(1000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                videoView_part8.start();
                            }
                        }.start();
                    } else {
                        videoView_part8.pause();
                    }
                }


            }
        });


        // RECYCLERVIEW

        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        final int radius = getResources().getDimensionPixelSize(R.dimen.radius);
        final int dotsHeight = getResources().getDimensionPixelSize(R.dimen.dots_height);
        final int colorInactive = ContextCompat.getColor(MainActivity.this, R.color.Gray500);
        final int colorActive = ContextCompat.getColor(MainActivity.this, R.color.black);


        // PART 1: RecyclerView_part1
        getDataPart1();


        // PART 3:
        getDataPart3();


        // PART 4: RecyclerView
        getDataPart4();
        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part4.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part4);


        // PART 7: RecyclerView
        getDataPart7();
        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part7.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part7);


        // PART 9: RecylerView
        getDataPart9();


        // PART 10: Recyclerview
        getDataPart10();
        // CREATE A DOT INDICATOR FOR RECYCLERVIEW
        recyclerView_part10.addItemDecoration(new DotsIndicatorDecoration(radius, radius * 4, dotsHeight, colorInactive, colorActive));
        new PagerSnapHelper().attachToRecyclerView(recyclerView_part10);


        // PART 11: RecylerView
        getDataPart11();


        // PART 12: RecylerView
        getDataPart12();

        // PART 13: RecyclerView
        getDataPart13();

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter_option_medium_Part1.startListening();
        adapterOptionLarge_Part4.startListening();
        adapterOptionLarge_Part7.startListening();
        adapterOptionMedium_Part9.startListening();
        adapterOptionLarge_Part10.startListening();
        adapterOptionMedium_Part11.startListening();
        adapterOptionMedium_Part12.startListening();
        adapterOptionPart13.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter_option_medium_Part1.stopListening();
        adapterOptionLarge_Part4.stopListening();
        adapterOptionLarge_Part7.stopListening();
        adapterOptionMedium_Part9.stopListening();
        adapterOptionLarge_Part10.stopListening();
        adapterOptionMedium_Part11.stopListening();
        adapterOptionMedium_Part12.stopListening();
        adapterOptionPart13.stopListening();
    }


    public void addUserIntoFirebase() {

        String emailUser = firebaseUser.getEmail();

        Task<QuerySnapshot> query = firebaseFirestore.collection("User")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                            User userCheck = documentSnapshot.toObject(User.class);

                            if (userCheck.getEmailUser().equals(emailUser)) {
                                checkUserExist(true);
                                return;
                            }
                        }
                        checkUserExist(false);
                    }
                });

    }

    public void checkUserExist(Boolean check) {

        Boolean isUserExist = check;

        if (!isUserExist) {

            String url_imageUser = firebaseUser.getPhotoUrl().toString();
            String displayNameUser = firebaseUser.getDisplayName();
            String emailUser = firebaseUser.getEmail();
            String idUser = firebaseUser.getUid();

            User user = new User(url_imageUser,
                    displayNameUser,
                    emailUser,
                    idUser
            );
//            Log.d("TESTAUTH", emailUser);
//            Log.d("TESTAUTH", isUserExist.toString());


            firebaseFirestore.collection("User").document(emailUser).set(user);

//            Log.d("TESTAUTH", "User added!");

        } else {

//            Log.d("TESTAUTH", "User exist!");
        }
    }


    public void getDataExhibitPart() {

//        image_exhibit_today_activity = findViewById(R.id.image_exhibit_today_activity);
//        cardView_exhibit_today_activity = findViewById(R.id.cardView_exhibit_today_activity);
//        tv_nameRecipe_exhibit_today_activity = findViewById(R.id.tv_nameRecipe_exhibit_today_activity);
//        tv_nameAuthor_exhibit_today_activity = findViewById(R.id.tv_nameAuthor_exhibit_today_activity);
//        btn_likes_exhibit_today_activity = findViewById(R.id.btn_likes_exhibit_today_activity);
        String idRecipe = "Recipe3";

        Task<DocumentSnapshot> query = firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        tv_nameRecipe_exhibit_today_activity.setText(recipe.getName_cooking_recipe());
                        tv_nameAuthor_exhibit_today_activity.setText(recipe.getName_author());
                        btn_likes_exhibit_today_activity.setText(recipe.getLikeAmount().toString());

                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_CookingRecipe())
                                .into(image_exhibit_today_activity);


                        // wait for loading data and stop progress bar
                        new CountDownTimer(1000,1000){
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }
                            @Override
                            public void onFinish() {
                                viewLoading_today_activity.setVisibility(View.GONE);
                                progressBar_today_activity.setVisibility(View.GONE);
                            }
                        }.start();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });


        image_exhibit_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });

        cardView_exhibit_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });


        btn_likes_exhibit_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void getDataPart1() {

        recyclerView_part1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Query query = firebaseFirestore.collection("Recipe").limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapter_option_medium_Part1 = new RecyclerViewAdapter_Option_Medium(MainActivity.this, options, new RecyclerViewAdapter_Option_Medium.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });


        recyclerView_part1.setAdapter(adapter_option_medium_Part1);


        btn_SeeAll_part1_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_recipes.class);
                startActivity(intent);
            }
        });
    }

    public void getDataPart2() {

        String idRecipe = "Recipe33";

        TextView name_recipe_part2_today_activity = findViewById(R.id.name_recipe_part2_today_activity);
        TextView txt_duration_views_part2_today_activity = findViewById(R.id.txt_duration_views_part2_today_activity);
        TextView name_author_part2_today_activity = findViewById(R.id.name_author_part2_today_activity);

        ImageView image_author_part2_today_activity = findViewById(R.id.image_author_part2_today_activity);
        Button btnGoToRecipe_part2_today_activity = findViewById(R.id.btnGoToRecipe_part2_today_activity);


        // PART2: VIDEO VIEW
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.recipe33;
        Uri uri = Uri.parse(videoPath);
        videoView_part2.setVideoURI(uri);
        videoView_part2.requestFocus();

        MediaController mediaController = new MediaController(this);
        videoView_part2.setMediaController(mediaController);
        mediaController.setAnchorView(videoView_part2);

        // SET MUTE TO VIDEO VIEW
        videoView_part2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f, 0f);
                mp.setLooping(true);


                // CENTER CROP VIDEO VIEW
//                float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
//                float screenRatio = videoView_part2.getWidth() / (float)
//                        videoView_part2.getHeight();
//                float scaleX = videoRatio / screenRatio;
//                if (scaleX >= 1f) {
//                    videoView_part2.setScaleX(scaleX);
//                } else {
//                    videoView_part2.setScaleY(1f / scaleX);
//                }

            }
        });

        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(videoPath));
        int duration = mp.getDuration();
        mp.release();
        /*convert millis to appropriate time*/
        String stringDuration = String.format("%d:%d min.",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );


        Task<DocumentSnapshot> query = firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        name_recipe_part2_today_activity.setText(recipe.getName_cooking_recipe());
                        name_author_part2_today_activity.setText(recipe.getName_author());
                        txt_duration_views_part2_today_activity.setText(stringDuration);

                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_author())
                                .into(image_author_part2_today_activity);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });

        btnGoToRecipe_part2_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });
    }

    public void getDataPart3() {

        String idRecipe = "Recipe24";

        Task<DocumentSnapshot> query = firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        name_story_part3_today_activity.setText(recipe.getName_cooking_recipe());
                        name_author_part3_today_activity.setText(recipe.getName_author());

                        int countPeriodTime = Integer.valueOf(recipe.getPeriodCooking().get(0))
                                + Integer.valueOf(recipe.getPeriodCooking().get(1))
                                + Integer.valueOf(recipe.getPeriodCooking().get(2));

                        tv_periodCooking_part3_today_activity.setText(String.valueOf(countPeriodTime) + " min.");

                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_CookingRecipe())
                                .into(image_story_part3_today_activity);

                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_author())
                                .into(image_author_part3_today_activity);

                        title_name_part3_today_activity.setText("Introducing: " + recipe.getName_cooking_recipe() + " by" + recipe.getName_author());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });

        cardView_part3_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });


    }

    public void getDataPart4() {

        // KID FRIENDLY

        recyclerView_part4.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.kid friendly", true)
                .limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionLarge_Part4 = new RecyclerViewAdapter_Option_Large(MainActivity.this, options, new RecyclerViewAdapter_Option_Large.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_part4.setAdapter(adapterOptionLarge_Part4);


    }

    public void getDataPart6() {

        String idRecipe = "Recipe35";

        TextView name_recipe_part6_today_activity = findViewById(R.id.name_recipe_part6_today_activity);
        TextView txt_duration_views_part6_today_activity = findViewById(R.id.txt_duration_views_part6_today_activity);
        TextView name_author_part6_today_activity = findViewById(R.id.name_author_part6_today_activity);

        ImageView image_author_part6_today_activity = findViewById(R.id.image_author_part6_today_activity);
        Button btnGoToRecipe_part6_today_activity = findViewById(R.id.btnGoToRecipe_part6_today_activity);


        // PART 6: VIDEOVIEW
        String videoPath_part6 = "android.resource://" + getPackageName() + "/" + R.raw.recipe35;
        Uri uri_part6 = Uri.parse(videoPath_part6);
        videoView_part6.setVideoURI(uri_part6);
        videoView_part6.requestFocus();

        MediaController mediaController = new MediaController(this);
        videoView_part6.setMediaController(mediaController);
        mediaController.setAnchorView(videoView_part6);

        // SET MUTE TO VIDEO VIEW
        videoView_part6.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                // VOLUME
                mp.setVolume(0f, 0f);
                mp.setLooping(true);

            }
        });

        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(videoPath_part6));
        int duration = mp.getDuration();
        mp.release();
        /*convert millis to appropriate time*/
        String stringDuration = String.format("%d:%d min.",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );


        Task<DocumentSnapshot> query = firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        name_recipe_part6_today_activity.setText(recipe.getName_cooking_recipe());
                        name_author_part6_today_activity.setText(recipe.getName_author());
                        txt_duration_views_part6_today_activity.setText(stringDuration);
                        title_name_part6_today_activity.setText("Watch " + recipe.getName_author() + " make " + recipe.getName_cooking_recipe());
                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_author())
                                .into(image_author_part6_today_activity);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });

        btnGoToRecipe_part6_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });


    }

    public void getDataPart7() {

        // weeknight diner

        // PART 7: RecyclerView
        recyclerView_part7.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.weeknight dinner", true)
                .limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionLarge_Part7 = new RecyclerViewAdapter_Option_Large(MainActivity.this, options, new RecyclerViewAdapter_Option_Large.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_part7.setAdapter(adapterOptionLarge_Part7);
    }

    public void getDataPart8() {

        String idRecipe = "Recipe34";

        TextView name_recipe_part8_today_activity = findViewById(R.id.name_recipe_part8_today_activity);
        TextView txt_duration_views_part8_today_activity = findViewById(R.id.txt_duration_views_part8_today_activity);
        TextView name_author_part8_today_activity = findViewById(R.id.name_author_part8_today_activity);

        ImageView image_author_part8_today_activity = findViewById(R.id.image_author_part8_today_activity);
        Button btnGoToRecipe_part8_today_activity = findViewById(R.id.btnGoToRecipe_part8_today_activity);

        // PART8: VIDEOVIEW
        String videoPath_part8 = "android.resource://" + getPackageName() + "/" + R.raw.recipe34;
        Uri uri_part8 = Uri.parse(videoPath_part8);
        videoView_part8.setVideoURI(uri_part8);
        videoView_part8.requestFocus();

        MediaController mediaController = new MediaController(this);
        videoView_part8.setMediaController(mediaController);
        mediaController.setAnchorView(videoView_part8);

        videoView_part8.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setVolume(0f, 0f);
                mp.setLooping(true);
            }
        });

        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(videoPath_part8));
        int duration = mp.getDuration();
        mp.release();
        /*convert millis to appropriate time*/
        String stringDuration = String.format("%d:%d min.",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );


        Task<DocumentSnapshot> query = firebaseFirestore.collection("Recipe").document(idRecipe)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Recipe recipe = documentSnapshot.toObject(Recipe.class);

                        name_recipe_part8_today_activity.setText(recipe.getName_cooking_recipe());
                        name_author_part8_today_activity.setText(recipe.getName_author());
                        txt_duration_views_part8_today_activity.setText(stringDuration);

                        Glide.with(MainActivity.this)
                                .load(recipe.getUrl_image_author())
                                .into(image_author_part8_today_activity);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                    }
                });

        btnGoToRecipe_part8_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", idRecipe);
                startActivity(intent);
            }
        });

    }

    public void getDataPart9() {

        // sweet

        recyclerView_part9.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

//        Query query = firebaseFirestore.collection("Recipe")
//                .whereEqualTo("tags.sweet", true)
//                .orderBy("likeAmount", Query.Direction.DESCENDING)
//                .limit(6);

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.sweet", true)
                .limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionMedium_Part9 = new RecyclerViewAdapter_Option_Medium(MainActivity.this, options, new RecyclerViewAdapter_Option_Medium.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });



        recyclerView_part9.setAdapter(adapterOptionMedium_Part9);

        btn_SeeAll_part9_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_recipes.class);
                startActivity(intent);
            }
        });

    }

    public void getDataPart10() {

        // comfort food

        recyclerView_part10.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

//        Query query = firebaseFirestore.collection("Recipe")
//                .whereEqualTo("tags.comfort food", true)
//                .orderBy("likeAmount")
//                .limit(6);

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.comfort food", true)
                .limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionLarge_Part10 = new RecyclerViewAdapter_Option_Large(MainActivity.this, options, new RecyclerViewAdapter_Option_Large.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });

        recyclerView_part10.setAdapter(adapterOptionLarge_Part10);

    }

    public void getDataPart11() {

        // lactose free

        // PART 11: RecylerView
        recyclerView_part11.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.lactose free", true)
                .limit(6);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionMedium_Part11 = new RecyclerViewAdapter_Option_Medium(MainActivity.this, options, new RecyclerViewAdapter_Option_Medium.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });


        recyclerView_part11.setAdapter(adapterOptionMedium_Part11);

        btn_SeeAll_part11_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_recipes.class);
                startActivity(intent);
            }
        });

    }

    public void getDataPart12() {

        // Asian

        recyclerView_part12.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

//        Query query = firebaseFirestore.collection("Recipe")
//                .whereEqualTo("tags.Asian", true)
//                .orderBy("likeAmount", Query.Direction.DESCENDING);

        Query query = firebaseFirestore.collection("Recipe")
                .whereEqualTo("tags.Asian", true);

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<Recipe>()
                .setQuery(query, Recipe.class)
                .build();

        adapterOptionMedium_Part12 = new RecyclerViewAdapter_Option_Medium(MainActivity.this, options, new RecyclerViewAdapter_Option_Medium.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(MainActivity.this, CookingRecipe.class);
                intent.putExtra("KeyID_Recipe", documentSnapshot.getId());
                startActivity(intent);
            }
        });


        recyclerView_part12.setAdapter(adapterOptionMedium_Part12);

        btn_SeeAll_part12_today_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, All_recipes.class);
                startActivity(intent);
            }
        });

    }

    public void getDataPart13() {

        // PART 13: RecyclerView
        recyclerView_part13.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

        Query query = firebaseFirestore.collection("TypesOfRecipe");

        FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<TypesOfRecipe>()
                .setQuery(query, TypesOfRecipe.class)
                .build();

        adapterOptionPart13 = new RecyclerViewAdapter_Option_Part13(MainActivity.this, options, new RecyclerViewAdapter_Option_Part13.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                TypesOfRecipe typesOfRecipe = documentSnapshot.toObject(TypesOfRecipe.class);
                Map<String, Boolean> map = typesOfRecipe.getTag();

                String tagItem = "";
                for (String item : map.keySet()) {
                    tagItem = item;
                }

                Intent intent = new Intent(MainActivity.this, FilterInSearchAllRecipe.class);
                intent.putExtra("KEYSEARCH_FOR_ALLRECIPE", tagItem);
                startActivity(intent);
            }
        });

        recyclerView_part13.setAdapter(adapterOptionPart13);

    }


    public void findByIdForComponents() {

        viewLoading_today_activity = findViewById(R.id.viewLoading_today_activity);
        progressBar_today_activity = findViewById(R.id.progressBar_today_activity);

        // part exhibit
        image_exhibit_today_activity = findViewById(R.id.image_exhibit_today_activity);
        cardView_exhibit_today_activity = findViewById(R.id.cardView_exhibit_today_activity);
        tv_nameRecipe_exhibit_today_activity = findViewById(R.id.tv_nameRecipe_exhibit_today_activity);
        tv_nameAuthor_exhibit_today_activity = findViewById(R.id.tv_nameAuthor_exhibit_today_activity);
        btn_likes_exhibit_today_activity = findViewById(R.id.btn_likes_exhibit_today_activity);


        // part3
        cardView_part3_today_activity = findViewById(R.id.cardView_part3_today_activity);
        name_story_part3_today_activity = findViewById(R.id.name_story_part3_today_activity);
        name_author_part3_today_activity = findViewById(R.id.name_author_part3_today_activity);
        tv_periodCooking_part3_today_activity = findViewById(R.id.tv_periodCooking_part3_today_activity);
        image_story_part3_today_activity = findViewById(R.id.image_story_part3_today_activity);
        image_author_part3_today_activity = findViewById(R.id.image_author_part3_today_activity);


        btn_SeeAll_part1_today_activity = findViewById(R.id.btn_SeeAll_part1_today_activity);
        btn_SeeAll_part9_today_activity = findViewById(R.id.btn_SeeAll_part9_today_activity);
        btn_SeeAll_part11_today_activity = findViewById(R.id.btn_SeeAll_part11_today_activity);
        btn_SeeAll_part12_today_activity = findViewById(R.id.btn_SeeAll_part12_today_activity);

        title_name_part3_today_activity = findViewById(R.id.title_name_part3_today_activity);
        title_name_part6_today_activity = findViewById(R.id.title_name_part6_today_activity);

        videoView_part2 = findViewById(R.id.videoView_part2_today_activity);

        nestedScrollView = findViewById(R.id.nestedScrollView_today_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        recyclerView_part1 = findViewById(R.id.recyclerView_part1_today_activity);

        recyclerView_part4 = findViewById(R.id.recyclerView_part4_today_activity);

        videoView_part6 = findViewById(R.id.videoView_part6_today_activity);

        recyclerView_part7 = findViewById(R.id.recyclerView_part7_today_activity);

        videoView_part8 = findViewById(R.id.videoView_part8_today_activity);

        recyclerView_part9 = findViewById(R.id.recyclerView_part9_today_activity);

        recyclerView_part10 = findViewById(R.id.recyclerView_part10_today_activity);

        recyclerView_part11 = findViewById(R.id.recyclerView_part11_today_activity);

        recyclerView_part12 = findViewById(R.id.recyclerView_part12_today_activity);

        recyclerView_part13 = findViewById(R.id.recyclerView_part13_today_activity);
    }



    public void setUpDataAndCreateDataToFirebase() {

        String name_cooking_recipe = "Turkish-inspired scrambled eggs";
        String url_image_CookingRecipe = "null";
        //String url_video_CookingRecipe ="null";

        String name_author = "Vanessa Pass";
        String name_authorGroup = "Contributor";
        String contact_author = "";
        String author_description = "";
        String url_image_author = "null";

        Long likeAmount = 11200L;
        Long ratingAmount = 51L;

        String difficulty_Level_Recipe = "Easy ";

        ArrayList<String> periodCooking = new ArrayList<>();
        periodCooking.add("20");
        periodCooking.add("0");
        periodCooking.add("0");

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("eggs");
        ingredients.add("onion");
        ingredients.add("yellow bell pepper");
        ingredients.add("green bell pepper");
        ingredients.add("parsley");
        ingredients.add("canned crushed tomatoes");
        ingredients.add("chili flakes");
        ingredients.add("feta");
        ingredients.add("salt");
        ingredients.add("pepper");
        ingredients.add("sugar");
        ingredients.add("olive oil for frying");
        ingredients.add("bread for serving");
//        ingredients.add("Parmesan cheese (grated)");
//        ingredients.add("walnuts");
//        ingredients.add("salt");
//        ingredients.add("pepper");
//        ingredients.add("cilantro");


        ArrayList<String> amountOfIngredients = new ArrayList<>();
        amountOfIngredients.add("4");
        amountOfIngredients.add("1");
        amountOfIngredients.add("1");
        amountOfIngredients.add("½ ");
        amountOfIngredients.add("15 g");
        amountOfIngredients.add("400 g ");
        amountOfIngredients.add("1 tsp ");
        amountOfIngredients.add("100 g ");
        amountOfIngredients.add("");
        amountOfIngredients.add("");
        amountOfIngredients.add("");
        amountOfIngredients.add("");
        amountOfIngredients.add("");
//        amountOfIngredients.add("6 tbsp ");
//        amountOfIngredients.add("80 g ");
//        amountOfIngredients.add("");
//        amountOfIngredients.add("");
//        amountOfIngredients.add("5 g ");


        String utensils = "cutting board - knife - large frying pan - cooking spoon";

        ArrayList<String> nutritionPerServing = new ArrayList<>();
        nutritionPerServing.add("285");
        nutritionPerServing.add("17 g");
        nutritionPerServing.add("16 g");
        nutritionPerServing.add("17 g");

        Map<String, Boolean> tags = new HashMap<>();
        tags.put("vegetarian", true);
        tags.put("middle eastern", true);
        tags.put("cheese", true);
        tags.put("breakfast", true);
        tags.put("Meatless", true);
        tags.put("low card", true);
        tags.put("comfort food", true);
        tags.put("brunch", true);
        tags.put("spices", true);
        tags.put("gluten free", true);
//        tags.put("crustacean and shellfish", true);
//        tags.put("Asian", true);
//        tags.put("soup", true);
//        tags.put("spicy", true);
//        tags.put("weeknight diner", true);
//        tags.put("spices", true);
//        tags.put("lactose free", true);

//        Recipe recipe = new Recipe(
//                name_cooking_recipe,
//                url_image_CookingRecipe,
//                name_author,
//                name_authorGroup,
//                contact_author,
//                author_description,
//                url_image_author,
//                likeAmount,
//                ratingAmount,
//                difficulty_Level_Recipe,
//                periodCooking,
//                ingredients,
//                amountOfIngredients,
//                utensils,
//                nutritionPerServing,
//                tags);


//        firebaseFirestore.collection("Recipe").document("Recipe26")
//                .set(recipe);


        // add steps

        String step1 = "1/3";
        String url_image1 = "null";
        String ingredientsForPerStep1 = "1 onion - 1 yellow bell pepper - ½ green bell pepper - 15 g parsley ";
        String utensilsForPerStep1 = "cutting board - knife";
        String scriptForDescription1 = "Peel and mince onion. Wash yellow and green bell pepper," +
                " remove seeds and finely dice. Pluck parsley leaves from the stems and finely chop.";


        StepsForRecipe stepsForRecipe1 = new StepsForRecipe(step1, url_image1, ingredientsForPerStep1, utensilsForPerStep1, scriptForDescription1);


        String step2 = "2/3";
        String url_image2 = "null";
        String ingredientsForPerStep2 = "400 g canned crushed tomatoes - 1 tsp chili flakes - salt - pepper - sugar - olive oil for frying";
        String utensilsForPerStep2 = "large frying pan";
        String scriptForDescription2 = "In a large frying pan, heat olive oil over medium-high heat." +
                " Add onion and sauté for approx. 2 min. Add yellow and green bell pepper and sauté for approx. " +
                "3 min. more. Reduce heat and add canned crushed tomatoes. Season to taste with salt, pepper, " +
                "and sugar. Add chili flakes, stir to combine, and let simmer for approx. 5 min.";


        StepsForRecipe stepsForRecipe2 = new StepsForRecipe(step2, url_image2, ingredientsForPerStep2, utensilsForPerStep2, scriptForDescription2);

//
        String step3 = "3/3";
        String url_image3 = "null";
        String ingredientsForPerStep3 = "4 eggs - 100 g feta - bread for serving";
        String utensilsForPerStep3 = "cooking spoon";
        String scriptForDescription3 = "With a cooking spoon, create hollows in the pepper-tomato" +
                " mixture and crack an egg in each hollow. Simmer for approx. 1 min., or until eggs are slightly set. " +
                "Then, stir eggs in circular movements, distributing them around the pan. Simmer for approx. 5 more min.," +
                " or until eggs have set. Crumble feta over and garnish with chopped parsley. Enjoy with fresh bread!";


        StepsForRecipe stepsForRecipe3 = new StepsForRecipe(step3, url_image3, ingredientsForPerStep3, utensilsForPerStep3, scriptForDescription3);


//        String step4 = "4/4";
//        String url_image4 = "null";
//        String ingredientsForPerStep4 ="70 g unsalted butter - ½ lime - 30 g pine nuts (toasted) - salt - pepper";
//        String utensilsForPerStep4 ="fine grater";
//        String scriptForDescription4 ="Once the rice is cooked, add butter, dandelion pesto," +
//                " and lime zest to the pot and stir to combine. Season with salt and pepper to taste and " +
//                "serve green risotto with reserved dandelion leaves and toasted pine nuts. Enjoy!";
//
//        StepsForRecipe stepsForRecipe4 = new StepsForRecipe(step4, url_image4,ingredientsForPerStep4,utensilsForPerStep4,scriptForDescription4);

//        String step5 = "5/5";
//        String url_image5 = "url_image3";
//        String ingredientsForPerStep5 ="scallion (for serving) - sesame seed (for serving) - short grain rice (for serving)";
//        String utensilsForPerStep5 ="";
//        String scriptForDescription5 ="Place the saucepan of leftover marinade over medium heat and bring to a simmer. " +
//                "Let simmer and reduce, swirling often, until thickened slightly, approx. 2 min. Remove from heat." +
//                " Serve the tofu and vegetables over rice, if desired, and garnish with sauce, sliced scallions," +
//                " and sesame seeds. Enjoy!";
//
//        StepsForRecipe stepsForRecipe5 = new StepsForRecipe(step5, url_image5,ingredientsForPerStep5,utensilsForPerStep5,scriptForDescription5);


//        // ------------------------------------------------------------fix recipe---fix step1----------
//        firebaseFirestore.collection("Recipe").document("Recipe26")
//                .collection("Steps").document("Step1").set(stepsForRecipe1);
//        firebaseFirestore.collection("Recipe").document("Recipe26")
//                .collection("Steps").document("Step2").set(stepsForRecipe2);
//        firebaseFirestore.collection("Recipe").document("Recipe26")
//                .collection("Steps").document("Step3").set(stepsForRecipe3);
//        firebaseFirestore.collection("Recipe").document("Recipe23")
//                .collection("Steps").document("Step4").set(stepsForRecipe4);
//        firebaseFirestore.collection("Recipe").document("Recipe23")
//                .collection("Steps").document("Step5").set(stepsForRecipe5);

    }

    public void addTypeOfRecipe() {

        String nameOfType1 = "German";
        Map<String, Boolean> tag1 = new HashMap<>();
        tag1.put("german", true);
        String url_typedOfRecipe1 = "null";

        TypesOfRecipe typesOfRecipe1 = new TypesOfRecipe(nameOfType1, tag1, url_typedOfRecipe1);
        firebaseFirestore.collection("TypesOfRecipe").document("german").set(typesOfRecipe1);


        String nameOfType2 = "Salads";
        Map<String, Boolean> tag2 = new HashMap<>();
        tag2.put("salads", true);
        String url_typedOfRecipe2 = "null";

        TypesOfRecipe typesOfRecipe2 = new TypesOfRecipe(nameOfType2, tag2, url_typedOfRecipe2);
        firebaseFirestore.collection("TypesOfRecipe").document("salads").set(typesOfRecipe2);


        String nameOfType3 = "Vegetarian";
        Map<String, Boolean> tag3 = new HashMap<>();
        tag3.put("vegetarian", true);
        String url_typedOfRecipe3 = "null";

        TypesOfRecipe typesOfRecipe3 = new TypesOfRecipe(nameOfType3, tag3, url_typedOfRecipe3);
        firebaseFirestore.collection("TypesOfRecipe").document("vegetarian").set(typesOfRecipe3);


        String nameOfType4 = "BBQ";
        Map<String, Boolean> tag4 = new HashMap<>();
        tag4.put("bbq", true);
        String url_typedOfRecipe4 = "null";

        TypesOfRecipe typesOfRecipe4 = new TypesOfRecipe(nameOfType4, tag4, url_typedOfRecipe4);
        firebaseFirestore.collection("TypesOfRecipe").document("bbq").set(typesOfRecipe4);


        String nameOfType5 = "Vietnamese";
        Map<String, Boolean> tag5 = new HashMap<>();
        tag5.put("vietnamese", true);
        String url_typedOfRecipe5 = "null";

        TypesOfRecipe typesOfRecipe5 = new TypesOfRecipe(nameOfType5, tag5, url_typedOfRecipe5);
        firebaseFirestore.collection("TypesOfRecipe").document("vietnamese").set(typesOfRecipe5);


        String nameOfType6 = "Desserts";
        Map<String, Boolean> tag6 = new HashMap<>();
        tag6.put("desserts", true);
        String url_typedOfRecipe6 = "null";

        TypesOfRecipe typesOfRecipe6 = new TypesOfRecipe(nameOfType6, tag6, url_typedOfRecipe6);
        firebaseFirestore.collection("TypesOfRecipe").document("desserts").set(typesOfRecipe1);

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