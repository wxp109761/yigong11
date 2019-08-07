package com.example.a17494.yigong11.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a17494.yigong11.Activity.HourStatisticActivity;
import com.example.a17494.yigong11.Activity.MyActionsActivity;
import com.example.a17494.yigong11.Activity.MyCollectActivity;
import com.example.a17494.yigong11.Activity.MyMessActivity;
import com.example.a17494.yigong11.Activity.SettingActivity;
import com.example.a17494.yigong11.Activity.UserInfoActivity;
import com.example.a17494.yigong11.Bean.HourBean;
import com.example.a17494.yigong11.MainActivity;
import com.example.a17494.yigong11.R;
import com.example.a17494.yigong11.Utils.Constants;
import com.example.a17494.yigong11.Utils.SpUtils;
import com.example.a17494.yigong11.cookie.RetrofitClient;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Subscriber;

import static android.app.Activity.RESULT_OK;


public class MineFragment extends BaseFragment implements View.OnClickListener{
    private View rootView;
    ImageView headerImg;
    private TextView userName;
    private TextView mActions;
    private TextView mMess;
    private TextView hourStatistic;
    private TextView settings;
    private TextView user_info;
    private TextView mCollect;
    private TextView userTips;
    private TextView mAllHour;



    private Bitmap head;// 头像Bitmap
    private static String path = "/sdcard/myHead/";// sd路径

    //调用照相机返回图片文件
    private File tempFile;


    @Override
    protected void setSubListenter() {

    }
    @Override
    protected View getSubView(LayoutInflater inflater, ViewGroup container) {
        rootView=inflater.inflate(R.layout.mine_view_fg,container,false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        initView();
        return rootView;
    }
    private void initView() {
        headerImg=rootView.findViewById(R.id.header_img);
        userName=rootView.findViewById(R.id.user_name);
        mAllHour = rootView.findViewById(R.id.all_hour);
        mActions = rootView.findViewById(R.id.my_action);
        hourStatistic=rootView.findViewById(R.id.hour_statistic);
        mMess=rootView.findViewById(R.id.my_mess);
        user_info=rootView.findViewById(R.id.use_info);
        mCollect = rootView.findViewById(R.id.my_collect);
        settings = rootView.findViewById(R.id.settings);
        userTips=rootView.findViewById(R.id.user_tips);
        headerImg.setOnClickListener(this);
        mActions.setOnClickListener(this);
        mMess.setOnClickListener(this);
        hourStatistic.setOnClickListener(this);
        user_info.setOnClickListener(this);
        mCollect.setOnClickListener(this);
        settings.setOnClickListener(this);
        Log.d("GGG",SpUtils.getString(getContext(),Constants.STU_NAME)+"");
        userName.setText(SpUtils.getString(getContext(),Constants.STU_NAME));
        userTips.setText(SpUtils.getString(getContext(),Constants.STU_ID));
        getAllHour();

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            headerImg.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.header_img:
                changeHeaderImg();
                break;
            case R.id.my_action:
                Toast.makeText(rootView.getContext(),"我的活动",Toast.LENGTH_SHORT).show();
                initMyActions();
                break;
            case R.id.my_mess:
                Toast.makeText(rootView.getContext(),"我的消息,待开放",Toast.LENGTH_SHORT).show();
                //initMyMess();
                break;
            case R.id.hour_statistic:
                Toast.makeText(rootView.getContext(),"工时统计",Toast.LENGTH_SHORT).show();
                initHourStatistic();
                break;
            case R.id.use_info:
                Toast.makeText(rootView.getContext(),"个人信息",Toast.LENGTH_SHORT).show();
                initUserInfo();
                break;
            case R.id.my_collect:
                Toast.makeText(rootView.getContext(),"我的收藏,待开放",Toast.LENGTH_SHORT).show();
                //initMyCollect();
                break;
            case R.id.settings:
                Toast.makeText(rootView.getContext(),"设置",Toast.LENGTH_SHORT).show();
                initSettings();
                break;
        }
    }

    private void changeHeaderImg() {
        String stringItems[]={"拍照","从相册选择"};
        final ActionSheetDialog dialog = new ActionSheetDialog(rootView.getContext(), stringItems, null);
        dialog.isTitleShow(true).show();
        dialog.title("更换头像");
        dialog.itemTextColor(Color.parseColor("#e9857d"));
        dialog.cancelText(Color.parseColor("#e9857d"));
       // dialog.itemPressColor(Color.parseColor("#e9857d"));
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(rootView.getContext(),"拍照",Toast.LENGTH_SHORT).show();
                        initCamera();
                        break;
                    case 1:
                        Toast.makeText(rootView.getContext(),"从相册选择",Toast.LENGTH_SHORT).show();
                        initLocal();
                        break;
                }
                dialog.dismiss();
            }
        });

    }

    private void initMyActions() {
        Intent intent=new Intent(getActivity(),MyActionsActivity.class);
        startActivity(intent);
    }
    private void initMyMess() {
        Intent intent=new Intent(rootView.getContext(),MyMessActivity.class);
        startActivity(intent);
    }

    private void initHourStatistic() {
        Intent intent=new Intent(rootView.getContext(),HourStatisticActivity.class);
        startActivity(intent);
    }
    private void initUserInfo() {
        Intent intent=new Intent(rootView.getContext(),UserInfoActivity.class);
        startActivity(intent);
    }
    private void initMyCollect() {
        Intent intent=new Intent(getActivity(),MyCollectActivity.class);
        startActivity(intent);
    }
    private void initSettings() {
        Intent intent=new Intent(rootView.getContext(),SettingActivity.class);
        startActivity(intent);
    }

    public void getAllHour() {
        RetrofitClient.getInstance(getContext()).getAllWorkHour(new Subscriber<HourBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HourBean hourBean) {
                mAllHour.setText(hourBean.getData().getSum1()+"");
                //mAllHour.setText("96");
               // Log.d("FFF",);
            }
        },Long.parseLong(SpUtils.getString(getContext(),Constants.STU_ID)));
    }



    public void initCamera() {

        //最好用try/catch包裹一下，防止因为用户未给应用程序开启相机权限，而使程序崩溃
        try {
            Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//开启相机应用程序获取并返回图片（capture：俘获）
            intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                    "head.jpg")));//指明存储图片或视频的地址URI
            startActivityForResult(intent2, 2);//采用ForResult打开
        } catch (Exception e) {
            Toast.makeText(rootView.getContext(), "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
        }
    }
    public void initLocal() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);//返回被选中项的URI
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");//得到所有图片的URI
//                System.out.println("MediaStore.Images.Media.EXTERNAL_CONTENT_URI  ------------>   "
//                        + MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//   content://media/external/images/media
        startActivityForResult(intent1, 1);
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //从相册里面取相片的返回结果
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }

                break;
            //相机拍照后的返回结果
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            //调用系统裁剪图片后
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        headerImg.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    ;

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //找到指定URI对应的资源图片
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);

        //进入系统裁剪图片的界面
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd卡是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建以此File对象为名（path）的文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件（compress：压缩）

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
