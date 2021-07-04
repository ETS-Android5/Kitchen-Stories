package com.example.kitchenstories.View.createRecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kitchenstories.Model.recipe.StepsForRecipe;
import com.example.kitchenstories.R;
import com.example.kitchenstories.View.Create;
import com.example.kitchenstories.ViewModel.CookingRecipeActivity.RecyclerViewAdapter_Ingredient_CookingRecipe;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class AddNewStep extends AppCompatActivity {

    private static final int Pick_Image_Request = 1;
    private Uri mImageUri;

    private String documentRef;

    private EditText editText_nameStep;

    private ImageView imageView_step;

    private EditText editText_desStep;

    private ArrayList<String> ingredients;
    private ArrayList<String> amountOfIngredients;
    private TextView textView_ingre;
    private Button button_add_ingre;

    private TextView textView_utensils;
    private Button button_add_utensil;

    private Button button_save;

    private StorageTask<UploadTask.TaskSnapshot> mUploadTask;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_step);

        if(getIntent().hasExtra("documentRef")){
            documentRef = getIntent().getExtras().getString("documentRef");
        }
        findByIdForComponents();
    }

    public void findByIdForComponents() {
        editText_nameStep = findViewById(R.id.editText_name_AddNewStepActivity);

        imageView_step = findViewById(R.id.imageView_step_AddNewStepActivity);

        editText_desStep = findViewById(R.id.editText_des_AddNewStepActivity);
        ingredients = new ArrayList<>();
        amountOfIngredients = new ArrayList<>();

        textView_ingre = findViewById(R.id.txt_ingre_AddNewStepActivity);
        button_add_ingre = findViewById(R.id.btn_Add_Ingre_AddNewStepActivity);

        textView_utensils = findViewById(R.id.txt_utensils_AddNewStepActivity);
        button_add_utensil = findViewById(R.id.btn_Add_Utensil_AddNewStepActivity);

        button_save = findViewById(R.id.btn_Save_AddNewStepActivity);

        imageView_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChoser();
            }
        });

        button_add_ingre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialogAddIngre();
            }
        });

        button_add_utensil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialogAddUtensil();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStep();
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
            Glide.with(AddNewStep.this)
                    .load(mImageUri)
                    .into(imageView_step);
        }
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

                        String ingre = amount +" " + nameIngre;

                        ingredients.add(nameIngre);
                        amountOfIngredients.add(amount);

                        if(textView_ingre.getText()==""){
                            textView_ingre.append(ingre);
                        }
                        else
                            textView_ingre.append(  " - " + ingre);
                        dialog.cancel();
                    }
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
    private void saveStep(){

        if(editText_nameStep.getText().toString().isEmpty()){
            editText_nameStep.setError("Please input name step");
            editText_nameStep.requestFocus();
            return;
        }
        if(mImageUri==null){
            Toast.makeText(AddNewStep.this, "Please choose image recipe", Toast.LENGTH_LONG).show();
            return;
        }
        if(editText_desStep.getText().toString().isEmpty()){
            editText_desStep.setError("Please input des step");
            editText_desStep.requestFocus();
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
                    String step = editText_nameStep.getText().toString();
                    Uri downloadUri = task.getResult();
                    String url_image_step = downloadUri.toString();

                    StepsForRecipe stepsForRecipe = new StepsForRecipe(step,
                            url_image_step,
                            textView_ingre.getText().toString(),
                            textView_utensils.getText().toString(),
                            editText_desStep.getText().toString());
                    firebaseFirestore.document(documentRef).collection("Steps").add(stepsForRecipe);
                }
                else { Toast.makeText(AddNewStep.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewStep.this, "Photo upload failed ", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(this, "Step added", Toast.LENGTH_SHORT).show();
        finish();
    }
}