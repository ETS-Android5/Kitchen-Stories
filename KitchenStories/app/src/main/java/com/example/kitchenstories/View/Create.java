package com.example.kitchenstories.View;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.recipe.Recipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.createRecipe.CreateStep;
import com.example.kitchenstories.View.pk_allRecipe.All_recipes;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Ingredient_CookingRecipe;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Create extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private static final int Pick_Image_Request = 1;

    private Toolbar toolbar;

    private EditText editText_nameRecipe;

    private Uri mImageUri;
    private ImageView imageView_recipe;

    private EditText editText_desRecipe;

    private String difficulty_recipe;
    private Button button_easy;
    private Button button_medium;
    private Button button_hard;

    private TimePickerDialog _timePickerDialog;
    private EditText editText_prepTime;
    private EditText editText_bakingTime;
    private EditText editText_restTime;

    private ArrayList<String> ingredients;
    private RecyclerView recyclerView_amountOfIngredients_CreateActivity;
    private RecyclerViewAdapter_Ingredient_CookingRecipe adapter_amountOfIngredients_CreateActivity;

    private ArrayList<String> amountOfIngredients;
    private RecyclerView recyclerView_Ingredients_CreateActivity;
    private RecyclerViewAdapter_Ingredient_CookingRecipe adapter_ingredient_CreateActivity;
    private Button button_add_ingre;

    private TextView textView_utensils;
    private Button button_add_utensil;

    private EditText editText_cal;
    private EditText editText_protein;
    private EditText editText_fat;
    private EditText editText_carb;

    private Map<String, Boolean> tags = new HashMap<>();;
    private ArrayList<String> tagsSort = new ArrayList<>();
    private TextView textView_tags;
    private Button button_add_tag;

    private Button button_next;

    private StorageTask<UploadTask.TaskSnapshot> mUploadTask;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //
        transparentStatusAndNavigation();

        Window window = Create.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(Create.this, R.color.Gray50));


        // FIND VIEW BY ID
        findByIdForComponents();

        // Navigation Icon is Clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


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

    public void findByIdForComponents() {

        toolbar = findViewById(R.id.toolbar_Create_Activity);

        editText_nameRecipe = findViewById(R.id.editText_nameRecipe);

        imageView_recipe = findViewById(R.id.imageView_recipe);

        editText_desRecipe = findViewById(R.id.editText_desRecipe);

        button_easy = findViewById(R.id.button_easy);
        button_medium = findViewById(R.id.button_medium);
        button_hard = findViewById(R.id.button_hard);

        editText_prepTime = findViewById(R.id.editText_prepTime);
        editText_bakingTime = findViewById(R.id.editText_bakingTime);
        editText_restTime = findViewById(R.id.editText_restTime);

        loadIngre();
        button_add_ingre = findViewById(R.id.btn_Add_Ingredient_CreateActivity);

        textView_utensils = findViewById(R.id.txt_utensils_Create_Activity);
        button_add_utensil = findViewById(R.id.btn_Add_Utensil_CreateActivity);

        editText_cal = findViewById(R.id.editText_cal);
        editText_protein = findViewById(R.id.editText_protein);
        editText_fat = findViewById(R.id.editText_fat);
        editText_carb = findViewById(R.id.editText_carb);

        button_add_tag = findViewById(R.id.btn_Add_Tag_CreateActivity);
        textView_tags = findViewById(R.id.txt_Tags_Create_Activity);

        button_next = findViewById(R.id.btn_Next_CreateActivity);

        imageView_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChoser();
            }
        });

        button_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_recipe = "Easy ";
                button_easy.setBackgroundColor(button_easy.getContext().getResources().getColor(R.color.DeepPurple900));
                button_easy.setTextColor(button_easy.getContext().getResources().getColor(R.color.white));
                button_medium.setBackgroundColor(button_medium.getContext().getResources().getColor(R.color.Yellow50));
                button_medium.setTextColor(button_easy.getContext().getResources().getColor(R.color.black));
                button_hard.setBackgroundColor(button_hard.getContext().getResources().getColor(R.color.Yellow50));
                button_hard.setTextColor(button_easy.getContext().getResources().getColor(R.color.black));
            }
        });

        button_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_recipe = "Medium ";
                button_medium.setBackgroundColor(button_medium.getContext().getResources().getColor(R.color.DeepPurple900));
                button_medium.setTextColor(button_easy.getContext().getResources().getColor(R.color.white));
                button_easy.setBackgroundColor(button_easy.getContext().getResources().getColor(R.color.Yellow50));
                button_easy.setTextColor(button_easy.getContext().getResources().getColor(R.color.black));
                button_hard.setBackgroundColor(button_hard.getContext().getResources().getColor(R.color.Yellow50));
                button_hard.setTextColor(button_easy.getContext().getResources().getColor(R.color.black));
            }

        });

        button_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty_recipe = "Hard ";
                button_hard.setBackgroundColor(button_hard.getContext().getResources().getColor(R.color.DeepPurple900));
                button_hard.setTextColor(button_easy.getContext().getResources().getColor(R.color.white));
                button_easy.setBackgroundColor(button_easy.getContext().getResources().getColor(R.color.Yellow50));
                button_medium.setBackgroundColor(button_medium.getContext().getResources().getColor(R.color.Yellow50));
                button_medium.setTextColor(button_easy.getContext().getResources().getColor(R.color.black));
            }
        });

        editText_prepTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPrepTime();
            }
        });

        editText_bakingTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBakingTime();
            }
        });

        editText_restTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRestTime();
            }
        });

        button_add_ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialogAddIngre();
            }
        });

        button_add_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialogAddTag();
            }
        });

        button_add_utensil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialogAddUtensil();
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecipe();
            }
        });

    }

    private void openFileChoser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Pick_Image_Request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Pick_Image_Request && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(Create.this)
                    .load(mImageUri)
                    .into(imageView_recipe);
        }
    }

    private void getPrepTime(){
        //--------< open_TimePickerDialog() >--------
        int hourOfDay=0;
        int minute=0;
        boolean is24HourView=true;

        //Theme_Holo_Light_Dialog
        //Theme_Holo_Light_DarkActionBar  //*Top Position
        _timePickerDialog=new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //*Return values
                editText_prepTime.setText(i*60+i1 +" min");
                //Toast.makeText(Create.this, "i=" + i + " i1=" + i1, Toast.LENGTH_SHORT).show();
            }
        },hourOfDay,minute,is24HourView);
        _timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _timePickerDialog.setTitle("Select a Time");
        _timePickerDialog.show();
        //--------</ open_TimePickerDialog() >--------
    }

    private void getBakingTime(){
        //--------< open_TimePickerDialog() >--------
        int hourOfDay=0;
        int minute=0;
        boolean is24HourView=true;

        //Theme_Holo_Light_Dialog
        //Theme_Holo_Light_DarkActionBar  //*Top Position
        _timePickerDialog=new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //*Return values
                editText_bakingTime.setText(i*60+i1 +" min");
                //Toast.makeText(Create.this, "i=" + i + " i1=" + i1, Toast.LENGTH_SHORT).show();
            }
        },hourOfDay,minute,is24HourView);
        _timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _timePickerDialog.setTitle("Select a Time");
        _timePickerDialog.show();
        //--------</ open_TimePickerDialog() >--------
    }

    private void getRestTime(){
        //--------< open_TimePickerDialog() >--------
        int hourOfDay=0;
        int minute=0;
        boolean is24HourView=true;

        //Theme_Holo_Light_Dialog
        //Theme_Holo_Light_DarkActionBar  //*Top Position
        _timePickerDialog=new TimePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                //*Return values
                editText_restTime.setText(i*60+i1 +" min");
                //Toast.makeText(Create.this, "i=" + i + " i1=" + i1, Toast.LENGTH_SHORT).show();
            }
        },hourOfDay,minute,is24HourView);
        _timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        _timePickerDialog.setTitle("Select a Time");
        _timePickerDialog.show();
        //--------</ open_TimePickerDialog() >--------
    }
    private void loadIngre(){
        recyclerView_amountOfIngredients_CreateActivity = findViewById(R.id.recyclerView_amountOfIngredients_CreateActivity);
        recyclerView_Ingredients_CreateActivity = findViewById(R.id.recyclerView_Ingredients_CreateActivity);

        ingredients = new ArrayList<>();
        amountOfIngredients = new ArrayList<>();

        recyclerView_amountOfIngredients_CreateActivity.setLayoutManager(new LinearLayoutManager(Create.this, LinearLayoutManager.VERTICAL, false));
        recyclerView_amountOfIngredients_CreateActivity.setHasFixedSize(true);
        adapter_amountOfIngredients_CreateActivity = new RecyclerViewAdapter_Ingredient_CookingRecipe(this, amountOfIngredients);
        recyclerView_amountOfIngredients_CreateActivity.setAdapter(adapter_amountOfIngredients_CreateActivity);


        recyclerView_Ingredients_CreateActivity.setLayoutManager(new LinearLayoutManager(Create.this, LinearLayoutManager.VERTICAL, false));
        recyclerView_Ingredients_CreateActivity.setHasFixedSize(true);
        adapter_ingredient_CreateActivity = new RecyclerViewAdapter_Ingredient_CookingRecipe(this, ingredients);
        recyclerView_Ingredients_CreateActivity.setAdapter(adapter_ingredient_CreateActivity);
    }

    private void displayDialogAddIngre() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_ingre);
        dialog.setCanceledOnTouchOutside(false);

        EditText editText_nameIngre = (EditText) dialog.findViewById(R.id.editText_nameIngre_dialog);
        EditText editText_amountIngre = (EditText) dialog.findViewById(R.id.editText_amountIngre_dialog);

        Spinner spinner_unitIngre = (Spinner) dialog.findViewById(R.id.spinner_unitIngre_dialog);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.unit_ingre, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unitIngre.setAdapter(arrayAdapter);

        Button button_Save = (Button) dialog.findViewById(R.id.btn_Save_dialog);
        Button button_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel_dialog);

        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_nameIngre.getText().toString().isEmpty()){
                    editText_nameIngre.setError("Please input name ingredient");
                    editText_nameIngre.requestFocus();
                }
                else {
                    if(editText_amountIngre.getText().toString().isEmpty()){
                        editText_amountIngre.setError("Please input amount ingredient");
                        editText_amountIngre.requestFocus();
                    }
                    else {
                        String nameIngre=editText_nameIngre.getText().toString();
                        String amount = editText_amountIngre.getText().toString() +" "+spinner_unitIngre.getSelectedItem().toString();

                        ingredients.add(nameIngre);
                        amountOfIngredients.add(amount);

                        adapter_ingredient_CreateActivity.notifyDataSetChanged();
                        adapter_amountOfIngredients_CreateActivity.notifyDataSetChanged();

                        dialog.cancel();
                    }
                }
            }
        });

        dialog.show();
    }

    private void displayDialogAddTag(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_tag);
        dialog.setCanceledOnTouchOutside(false);

        EditText editText_tag = (EditText) dialog.findViewById(R.id.editText_tag_dialog);
        Button button_Save = (Button) dialog.findViewById(R.id.btn_Save_dialog);
        Button button_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel_dialog);

        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_tag.getText().toString().isEmpty()){
                    editText_tag.setError("Please input tag");
                    editText_tag.requestFocus();
                }
                else{
                    tags.put(editText_tag.getText().toString(),true);
                    tagsSort.add(editText_tag.getText().toString());
                    //loadTags();
                    textView_tags.append("#" + editText_tag.getText().toString() + "     ");
                    dialog.cancel();
                }
            }
        });

        dialog.show();
    }

    private void displayDialogAddUtensil(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_utensil);
        dialog.setCanceledOnTouchOutside(false);

        EditText editText_utensil = (EditText) dialog.findViewById(R.id.editText_utensil_dialog);
        Button button_Save = (Button) dialog.findViewById(R.id.btn_Save_dialog);
        Button button_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel_dialog);

        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        button_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_utensil.getText().toString().isEmpty()){
                    editText_utensil.setError("Please input utensil");
                    editText_utensil.requestFocus();
                }
                else {
                    if(textView_utensils.getText()==""){
                        textView_utensils.append(editText_utensil.getText().toString());
                    }
                    else
                        textView_utensils.append(  " - " + editText_utensil.getText().toString());
                    dialog.cancel();
                }

            }
        });

        dialog.show();
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void saveRecipe(){
        if(editText_nameRecipe.getText().toString().isEmpty()){
            editText_nameRecipe.setError("Name recipe required");
            editText_nameRecipe.requestFocus();
            return;
        }
        if(mImageUri==null){
            Toast.makeText(Create.this, "Please choose image recipe", Toast.LENGTH_LONG).show();
            return;
        }
        if(editText_desRecipe.getText().toString().isEmpty()){
            editText_desRecipe.setError("Description recipe required");
            editText_desRecipe.requestFocus();
            return;
        }
        if(difficulty_recipe==null){
            Toast.makeText(Create.this, "Please select difficult", Toast.LENGTH_LONG).show();
            return;
        }
        if(editText_cal.getText().toString().isEmpty()){
            editText_cal.setError("Please input calories of recipe");
            editText_cal.requestFocus();
            return;
        }
        if(editText_protein.getText().toString().isEmpty()){
            editText_protein.setError("Please input protein nutrition of recipe");
            editText_protein.requestFocus();
            return;
        }
        if(editText_fat.getText().toString().isEmpty()){
            editText_fat.setError("Please input fat nutrition of recipe");
            editText_fat.requestFocus();
            return;
        }
        if(editText_carb.getText().toString().isEmpty()){
            editText_carb.setError("Description recipe required");
            editText_carb.requestFocus();
            return;
        }

        if(tagsSort==null){
            Toast.makeText(Create.this, "Please input tags for us easy to find your recipe ", Toast.LENGTH_LONG).show();
            return;
        }

        final StorageReference fileReference = FirebaseStorage.getInstance().getReference("Test").child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUri));
        mUploadTask = fileReference.putFile(mImageUri);
        mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri>then(@NonNull Task<UploadTask.TaskSnapshot>task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return fileReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    String name_cooking_recipe = editText_nameRecipe.getText().toString();
                    Uri downloadUri = task.getResult();
                    String url_image_CookingRecipe = downloadUri.toString();;

                    String name_author = firebaseUser.getDisplayName();
                    String name_authorGroup = "Community";
                    String contact_author = firebaseUser.getUid();
                    String author_description = editText_desRecipe.getText().toString();
                    String url_image_author = firebaseUser.getPhotoUrl().toString();;

                    Long likeAmount = 0L;
                    Long ratingAmount = 0L;

                    String[] prepTime = editText_prepTime.getText().toString().split(" ");
                    String[] bakingTime = editText_bakingTime.getText().toString().split(" ");
                    String[] restTime = editText_restTime.getText().toString().split(" ");
                    ArrayList<String> periodCooking = new ArrayList<>();
                    periodCooking.add(prepTime[0]);
                    periodCooking.add(bakingTime[0]);
                    periodCooking.add(restTime[0]);

                    String utensils = textView_utensils.getText().toString();

                    ArrayList<String> nutritionPerServing = new ArrayList<>();
                    nutritionPerServing.add(editText_cal.getText().toString());
                    nutritionPerServing.add(editText_protein.getText().toString()+" g");
                    nutritionPerServing.add(editText_fat.getText().toString()+" g");
                    nutritionPerServing.add(editText_carb.getText().toString()+" g");

                    Long preparationTimeSort = Long.valueOf(periodCooking.get(0))
                            + Long.valueOf(periodCooking.get(1))
                            + Long.valueOf(periodCooking.get(2));

                    Long caloriesSort = Long.valueOf(nutritionPerServing.get(0));


                    Recipe recipe = new Recipe(name_cooking_recipe,
                            url_image_CookingRecipe,
                            name_author,
                            name_authorGroup,
                            contact_author,
                            author_description,
                            url_image_author,
                            likeAmount,
                            ratingAmount,
                            preparationTimeSort,
                            caloriesSort,
                            difficulty_recipe,
                            periodCooking,
                            ingredients,
                            amountOfIngredients,
                            utensils,
                            nutritionPerServing,
                            tags,
                            tagsSort);

                    firebaseFirestore.collection("Recipe").add(recipe).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Intent intent = new Intent(Create.this, CreateStep.class);
                            intent.putExtra("documentRef", documentReference.getPath());
                            startActivity(intent);
                        }
                    });

                    Toast.makeText(Create.this, "Upload successful", Toast.LENGTH_LONG).show();
                }

                else { Toast.makeText(Create.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Create.this, "Photo upload failed ", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Recipe added", Toast.LENGTH_SHORT).show();
    }


}
