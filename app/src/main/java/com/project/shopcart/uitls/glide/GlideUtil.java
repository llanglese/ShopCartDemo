package com.project.shopcart.uitls.glide;

/**
 * Created by Administrator on 2017/2/28.
 */

/*
 *  项目名：  GankClient
 *  包名：    com.liuguilin.gankclient.util
 *  文件名:   GlideUtil
 *  创建者:   LGL
 *  创建时间:  2016/10/20 16:45
 *  描述：    Glide封装
 */

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

public class GlideUtil {

    /**
     * Glide特点
     * 使用简单
     * 可配置度高，自适应程度高
     * 支持常见图片格式 Jpg png gif webp
     * 支持多种数据源  网络、本地、资源、Assets 等
     * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
     * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
     * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
     * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
     */

    //默认加载
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    //默认加载
    public static void loadImageView(Context mContext, int placeholderId, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().placeholder(placeholderId).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
    }

    public static final int SOURCCE_SD = 1;
    public static final int SOURCCE_ASSETS = 2;
    public static final int SOURCCE_RAW = 3;
    public static final int SOURCCE_DRAWABLE = 4;
    public static final int SOURCCE_MIPMAP = 5;
    public static final int SOURCCE_CONTENTPROVIDER = 6;
    public static final int SOURCCE_HTTP = 7;

    /**
     * 根据资源不同，选择不同的加载方式
     * load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
     * load assets资源：load("file:///android_asset/f003.gif")
     * load raw资源：load("android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
     * load drawable资源：load("android.resource://com.frank.glide/drawable/news")或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)
     * load ContentProvider资源：load("content://media/external/images/media/139469")
     * load http资源：load("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
     * load https资源：load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
     */
    public static void loadImageViewBySource(Context mContext, int placeholderId, int sourceType, String path, ImageView mImageView) {
       // KLog.e("显示路径", "" + path);
        switch (sourceType) {
            case SOURCCE_SD:
                Glide.with(mContext).load(new File(path)).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_ASSETS:
                Glide.with(mContext).load("file:///android_asset/" + path).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_RAW:
                Glide.with(mContext).load("android.resource://" + mContext.getPackageName() + "/" + path).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_DRAWABLE:
                Glide.with(mContext).load("android.resource://" + mContext.getPackageName() + "/drawable/" + path).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_MIPMAP:
                Glide.with(mContext).load("android.resource://" + mContext.getPackageName() + "/mipmap/" + path).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_CONTENTPROVIDER:
                Glide.with(mContext).load(path).placeholder(placeholderId).into(mImageView);
                break;
            case SOURCCE_HTTP:
                Glide.with(mContext).load(path).placeholder(placeholderId).into(mImageView);
                break;
        }
    }

    //圆形图片
    public static void loadImageViewCircle(Context mContext, String path, int placeholderId, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeholderId).centerCrop().transform(new GlideCircleTransform(mContext)).into(mImageView);
    }

    //圆角图片
    public static void loadImageViewRoundedCorners(Context mContext, String path, int placeholderId, int radius, int margin, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().placeholder(placeholderId).error(placeholderId).crossFade()
                .transform(new RoundedCornersTransformation(mContext, radius, margin)).into(mImageView);
    }

    //圆角图片
    public static void loadImageViewRound(Context mContext, String path, int placeholderId, int radius, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop().placeholder(placeholderId).error(placeholderId).crossFade()
                .transform(new RoundTransform(mContext, radius)).into(mImageView);
    }

    //灰度图片
    public static void loadImageViewGrayscale(Context mContext, String path, int placeholderId, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(placeholderId).error(placeholderId).crossFade()
                .bitmapTransform(new GrayscaleTransformation(mContext)).into(mImageView);
    }

    //旋转图片
    public static void loadImageViewRotate(Context mContext, String path, float rotateRotationAngle, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().transform(new RotateTransformation(mContext, rotateRotationAngle)).into(mImageView);
    }

    //高斯模糊图片
    public static void loadImageViewBlur(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().transform(new BlurTransformation(mContext)).into(mImageView);
    }

    //加载指定大小
    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
        Glide.with(mContext).load(path).override(width, height).into(mImageView);
    }

    //设置加载中以及加载失败图片
    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置加载中以及加载失败图片并且指定大小
    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView mImageView, int lodingImage, int errorImageView) {
        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into(mImageView);
    }

    //设置跳过内存缓存
    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
    }

    //设置下载优先级
    //    Priority.LOW
    //    Priority.NORMAL
    //    Priority.HIGH
    //    Priority.IMMEDIATE
    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
    }

    /**
     * 策略解说：
     * <p>
     * all:缓存源资源和转换后的资源
     * <p>
     * none:不作任何磁盘缓存
     * <p>
     * source:缓存源资源
     * <p>
     * result：缓存转换后的资源
     */

    //设置缓存策略
    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
    }

    /**
     * api也提供了几个常用的动画：比如crossFade()
     */

    //设置加载动画
    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
        Glide.with(mContext).load(path).animate(anim).into(mImageView);
    }

    /**
     * 会先加载缩略图
     */

    //设置缩略图支持
    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
    }

    /**
     * api提供了比如：centerCrop()、fitCenter()等
     */

    //设置动态转换
    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).centerCrop().into(mImageView);
    }

    //设置动态GIF加载方式
    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asGif().into(mImageView);
    }

    //设置静态GIF加载方式
    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).asBitmap().into(mImageView);
    }

    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘

    //设置监听请求接口
    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView, RequestListener<String, GlideDrawable> requstlistener) {
        Glide.with(mContext).load(path).listener(requstlistener).into(mImageView);
    }

    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排

    //设置要加载的内容
    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
    }

    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }

}
