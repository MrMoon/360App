package com.ma.moon.moonapp.Test;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.*;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ma.moon.moonapp.Constants.Constants;
import com.ma.moon.moonapp.Interfaces.Basics;
import com.ma.moon.moonapp.R;
import com.ma.moon.moonapp.Activities.ShowImagesActivity;
import com.ma.moon.moonapp.pojo.Upload;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class TestActivity extends AppCompatActivity implements Basics {

    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;

    //view objects
    private Button btnChoose, btnPreview, btnUpload;
    private EditText editTextName;
    private TextView textViewShow;
    private ImageView imageView;
    private ProgressDialog progressDialog;
    private Map<String, Upload> uploadMap;

    //uri to store file
    private Uri filePath;

    //firebase objects
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnPreview = (Button) findViewById(R.id.btnPreview);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgTest);
        editTextName = (EditText) findViewById(R.id.txtTest);
        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);
        firebaseAuth = FirebaseAuth.getInstance();

        btnChoose.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnPreview.setOnClickListener(this);

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void test(){
        filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("172605122018m ", "onSuccess: uri= "+ uri.toString());
                    }
                });
            }
        });
    }

    private void uploadFile() {
        if (filePath != null) {
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            //Getting Storage Reference:
            StorageReference reference = storageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //Adding the file To the Reference:
            reference.putFile(filePath).addOnSuccessListener(taskSnapshot -> {
                progressDialog.dismiss();

                Upload upload = new Upload(editTextName.getText().toString(), reference.getDownloadUrl().toString());

                Log.v("172605122018m", reference.getDownloadUrl().toString());
                Log.v("172605122018m", taskSnapshot.getUploadSessionUri().getPath());
                Log.v("172605122018m", reference.getBucket());
                Log.v("172605122018m", reference.getPath());


                String id = mDatabase.push().getKey();
                mDatabase.child(id + "-" + upload.imgName).setValue(upload);
            });
        } else {
            progressDialog.dismiss();
            makeToast("Error");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            Picasso.get().load(filePath).into(imageView);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnChoose) {
            showFileChooser();
        }
        if (v == btnUpload) {
            if (!TextUtils.isEmpty(editTextName.getText().toString())) {
                uploadFile();
            } else {
                editTextName.setError("Please Enter A Name");
            }
        }
        if (v == btnPreview) {
            updateUI(this, ShowImagesActivity.class);
            finish();
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI(Context context, Class<?> aClass) {
        Intent intent = new Intent(context, aClass);
        startActivity(intent);
    }

    @Override
    public void writeData() {

    }

    @Override
    public void readData() {

    }

    @Override
    public void firebase() {

    }
}
