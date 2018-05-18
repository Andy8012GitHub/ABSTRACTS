//package com.example.wilsoash005.abstracts;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageButton;
//
//import java.util.ArrayList;
//
///**
// * Created by wilsoash005 on 4/27/2018.
// */
//
//public class ImageAdapter extends BaseAdapter {
//    private Context mContext;
//    private Integer[] mPPTListsThumbIds = {
//            R.drawable.ppt_2018_basic,
//            R.drawable.ppt_2018_full,
//            R.drawable.ppt_1988,
//            R.drawable.ppt_sports,
//            R.drawable.ppt_movies,
//            R.drawable.ppt_music_stars,
//            R.drawable.ppt_desserts,
//            R.drawable.ppt_make_your_own_list
//    };
//
////    public ArrayList<ImageButton> imgBtns = new ArrayList<>();
//
//    public ImageAdapter(Context c) {
//        mContext = c;
//    }
//
//    @Override
//    public int getCount() {
//        return mPPTListsThumbIds.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View view, ViewGroup viewGroup) {
//        final ImageButton imgBtn;
//        imgBtn = new ImageButton(mContext);
//        imgBtn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        imgBtn.setScaleType(ImageButton.ScaleType.CENTER_CROP);
//        imgBtn.setPadding(8, 16, 8, 16);
//        imgBtn.setImageResource(mPPTListsThumbIds[position]);
//        imgBtn.setTag(mPPTListsThumbIds[position]);
////        imgBtns.add(imgBtn);
//        return imgBtn;
//    }
//}
