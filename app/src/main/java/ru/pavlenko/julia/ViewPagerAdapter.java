package ru.pavlenko.julia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    private final static int[] IMAGES = {
            R.drawable.welcome_page_1,
            R.drawable.welcome_page_2,
            R.drawable.welcome_page_3
    };

    private final static int[] TITLES = {
            R.string.welcome_title_1,
            R.string.welcome_title_2,
            R.string.welcome_title_3
    };

    private final static int[] SUBTITLES = {
            R.string.welcome_subtitle_1,
            R.string.welcome_subtitle_2,
            R.string.welcome_subtitle_3
    };

    private LayoutInflater mInflater;

    public ViewPagerAdapter(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.welcome_page, container, false);

        container.addView(view);

        ImageView welcomeImageView = view.findViewById(R.id.image);
        welcomeImageView.setImageResource(IMAGES[position]);

        TextView titleTextView = view.findViewById(R.id.title);
        TextView subtitleTextView = view.findViewById(R.id.subtitle);

        titleTextView.setText(TITLES[position]);
        subtitleTextView.setText(SUBTITLES[position]);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return Objects.equals(view, object);
    }
}
