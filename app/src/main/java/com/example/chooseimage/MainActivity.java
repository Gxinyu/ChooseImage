package com.example.chooseimage;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.chooseimage.utils.PermissionHelper;

public class MainActivity extends AppCompatActivity implements ChooseImageTask.OnSelectListener {

    private ImageView imageView;
    private ChooseImageTask.Builder perform;

    String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        PermissionHelper.checkMultiPermission(this, permissions);
    }


    public void getImage(View view) {
        perform = ChooseImageTask.getInstance()
                .createBuilder(this)
                .setIsCrop(false)
                .setIsCompress(true)
                .setOnSelectListener(this)
                .setType(ChooseImageTask.TYPE_GALLERY)
                .perform();

    }

    public void getImage2(View view) {
        perform = ChooseImageTask.getInstance()
                .createBuilder(this)
                .setIsCrop(false)
                .setOnSelectListener(this)
                .setType(ChooseImageTask.TYPE_ALBUM)
                .perform();
    }


    public void getPhoto(View view) {
        perform = ChooseImageTask.getInstance()
                .createBuilder(this)
                .setIsCrop(false)
                .setOnSelectListener(this)
                .setType(ChooseImageTask.TYPE_PHOTO)
                .perform();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ChooseImageTask.getInstance().handleResult(requestCode, resultCode, data, perform);
    }

    @Override
    public void onSuccess(Bitmap bitmap) {
        Log.e("TAG", "成功");
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError(String message) {
        Log.e("TAG", "失败");
    }

}
