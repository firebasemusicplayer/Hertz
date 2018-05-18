package com.example.nikma.firebasemusicplayer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.RecyclerViewHolder> {
    private final Context context;
    private static final int IS_HEADER = 0;
    private static final int IS_NORMAL = 1;
    private ViewPager viewPager;
    private String[] colorId = new String[]{"#00A5BF", "#1D697C", "#763568", "#A4345D", "#C91F37", "#FF4E20", "#FFA631"};
    private List<ImageView> imageViews;
    private String[] titles;
    private int[] imageResId = new int[]{R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
    private List<View> dots;
    private TextView tv_title;
    private int currentItem = 0;


    public CustomerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder holder;
        // Create different view holder with different flag
        if (viewType == IS_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_header, parent, false);

            imageViews = new ArrayList<ImageView>();
            for (int i = 0; i < imageResId.length; i++) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(imageResId[i]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageViews.add(imageView);

            }

            holder = new RecyclerViewHolder(view, IS_HEADER);
            return holder;


        } else if (viewType == IS_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_item, parent, false);
            holder = new RecyclerViewHolder(view, IS_NORMAL);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.RecyclerViewHolder holder, int position) {
        if (position != 0 && position != colorId.length + 1 && holder.viewType == IS_NORMAL) {
            holder.imgView.setBackgroundColor(Color.parseColor(colorId[position]));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return IS_HEADER;
        } else {
            return IS_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return colorId.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public int viewType;
        public ImageView imgView;
        public TextView txtView;

        public RecyclerViewHolder(View itemView, int viewType ) {
            super(itemView);
            this.viewType = viewType;
            if (viewType == IS_HEADER) {

                ViewPager viewPager = (ViewPager) itemView.findViewById(R.id.vpheader);
                viewPager.setAdapter(new MyAdapter());



            }
//
            if (viewType == IS_NORMAL) {
                txtView = (TextView) itemView.findViewById(R.id.homeTextView);
                imgView = (ImageView) itemView.findViewById(R.id.homeCateView);

            }
        }

    }

    private class MyAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView((View) object);

        }

        @Override
        public int getCount() {
            return imageResId.length;

        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }




}


