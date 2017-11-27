package com.sandy.myframe.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sandy.myframe.CommonResultCode;
import com.sandy.myframe.R;
import com.sandy.myframe.base.BaseActivity;
import com.sandy.myframe.util.DebugLogs;
import com.sandy.myframe.util.PictureObtain;
import com.sandy.myframe.util.UriHelper;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class LoadImgActivity extends BaseActivity {

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.view_avatar)
    SimpleDraweeView viet_avatar;

    @Override
    public int getContentViewId() {
        return R.layout.activity_loadimg;
    }

    private PictureObtain mObtain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        mObtain = new PictureObtain();
    }
    
    @OnClick({R.id.btn_test,R.id.btn_net})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.btn_test:
                txtName.setText("设置本地图像");
                mObtain.showDialog(this);
                break;
            case R.id.btn_net:
                txtName.setText("设置网络图像");
                viet_avatar.setImageURI(UriHelper.obtainUri("http://f.hiphotos.baidu.com/image/pic/item/d53f8794a4c27d1eef5c850c11d5ad6edcc438fb.jpg"));
                break;
        }
    }

    @Override
    public void doHandler(Message msg) {
        switch (msg.what) {


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    String distUri;
    String path = null;

    /**
     * 接收用户返回头像参数
     */
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode != RESULT_CANCELED && resultCode == RESULT_OK) {
            switch (requestCode) {
                case CommonResultCode.SET_ADD_PHOTO_CAMERA:
                    //拍照
                      distUri = mObtain.obtainUrl();


                    DebugLogs.e("imgpath:" + distUri);

                    mObtain.notifyChange(this, mObtain.getUri(this));
                    mObtain.cropBig(this, mObtain.getUri(this), distUri, CommonResultCode.REQUEST_CROP_PICTURE, 400, 400);
                    break;
                case CommonResultCode.SET_ADD_PHOTO_ALBUM:
                    //从相册获取
                    if (data != null && data.getData() != null) {
                        distUri = mObtain.obtainUrl();

                        DebugLogs.e("imgpath:" + distUri);

                        mObtain.cropBig(this, data.getData(), distUri, CommonResultCode.REQUEST_CROP_PICTURE, 400, 400);
                    }
                    break;
                case CommonResultCode.REQUEST_CROP_PICTURE:
                    //裁剪后的图片
                    Uri localUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        localUri = FileProvider.getUriForFile(this, "com.sandy.myframe.fileprovider",
                                new File(distUri));
                    } else {
                        localUri = Uri.fromFile(new File(distUri));
                    }
                    viet_avatar.setImageURI(localUri);
//                    path = mObtain.getRealPathFromURI(this, localUri);
//                    String imgPath = null;
//                    if (!new File(path).exists()) {
//                        return;
//                    }

//                    try {
//                       Bitmap bitmap = mObtain.getimage(path);
//                       imgPath = mObtain.saveBitmapFile(bitmap);
//                       //DebugLogs.e("imagepath:" + imgPath);
//                       viet_avatar.setImageBitmap(bitmap);
//                        //
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    //imgTest.setImageBitmap(UriHelper.obtainUri(imgPath));

                    //上传图片
                    break;
                default:
                    break;
            }
        }
    }
}
