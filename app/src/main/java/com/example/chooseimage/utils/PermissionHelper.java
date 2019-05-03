package com.example.chooseimage.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.chooseimage.ChooseImageTask;

public class PermissionHelper {


    /**
     * 操作的时候检测是否拥有当前的权限
     *
     * @param activity
     * @param type
     */
    public static void checkPermission(Activity activity, int type) {
        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            if (type == ChooseImageTask.TYPE_PHOTO) {
                checkSinglePermission(activity, permissions[0]);
            } else {
                checkSinglePermission(activity, permissions[1]);
                checkSinglePermission(activity, permissions[2]);
            }
        }
    }


    public static void checkSinglePermission(Activity activity, String permissions) {
        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
            //检查是否已经授权
            if (ActivityCompat.checkSelfPermission(activity, permissions) != PackageManager.PERMISSION_GRANTED) {
                //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Toast.makeText(activity, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                }
                //申请权限
                ActivityCompat.requestPermissions(activity, new String[]{permissions}, 1);
            } else {
                Toast.makeText(activity, "授权成功！", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 检测多个权限
     */
    public static void checkMultiPermission(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
            for (int i = 0; i < permissions.length; i++) {
                //检查是否已经授权
                if (ActivityCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(activity, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                    }
                    //申请权限
                    ActivityCompat.requestPermissions(activity, new String[]{permissions[i]}, 1);
                } else {
                    Toast.makeText(activity, "授权成功！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
