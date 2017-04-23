package com.example.forever.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Forever on 4/22/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {


        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
            Log.d("Test", "onCreateView: ---------------------------testing ");
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    CurrentWeatherFragment currentWeatherFragment =new CurrentWeatherFragment();
                    return currentWeatherFragment;
                    //break;
                case 1:
                    ForecastFragment forecastFragment =new ForecastFragment();
                    return forecastFragment;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Current";

                case 1:
                    return "Forecast";
            }
            return null;
        }
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {

            Log.d("Test", "onCreateView: ---------------------------testing ");
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            Log.d("Test", "onCreateView: ---------------------------testing ");
            View rootView = inflater.inflate(R.layout.main_fragment, container, false);

            return rootView;
        }
    }

}
