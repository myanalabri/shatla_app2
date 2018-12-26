package online.shatla_app.homePage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import online.shatla_app.R;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import online.shatla_app.fruitNursery.FruitNurseryFragment;
import online.shatla_app.ornamentalNursery.OrnamentalNurseryFragment;
import online.shatla_app.profile.NewProdectFragment;
import online.shatla_app.profile.ProductFragment;
import online.shatla_app.tools.ToolFragment;
import online.shatla_app.vegatableNursery.VegatableNurseryFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment implements AdapterHomeFragment.OnAdapterItemClick{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";





    private RecyclerView recyclerView;
    private ArrayList<SupportItems> arrayList;
    private String[] supportItems;
    private int[] images;
    private SupportItems items;
    private CircleIndicator indicator;
    private ViewPager viewPager;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private static int[] pagerImage={R.drawable.page1,R.drawable.page2,
            R.drawable.page3,R.drawable.page1,R.drawable.page2};

    private Handler handler;
    private Runnable runnable;
    private int mPageNumber;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView=view.findViewById(R.id.home_page);
        viewPager=view.findViewById(R.id.home_view_pager);
        indicator=view.findViewById(R.id.indicator);



        sectionsPagerAdapter=new SectionsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        indicator.setViewPager(viewPager);



        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        arrayList=new ArrayList<>();
        supportItems=getResources().getStringArray(R.array.home_items);
        images=new int[]{R.drawable.image1,R.drawable.page1,
                R.drawable.page2,R.drawable.page3,R.drawable.image1,R.drawable.image1,
               };
        createContainer();
//        CustomAdapter customAdapter=new CustomAdapter(arrayList,getActivity());
        AdapterHomeFragment customAdapter=new AdapterHomeFragment(arrayList,getActivity());
        customAdapter.newMethod(this);
        recyclerView.setAdapter(customAdapter);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                if (sectionsPagerAdapter.getCount() == mPageNumber) {
                    mPageNumber = 0;
                } else {
                    mPageNumber++;
                }
                viewPager.setCurrentItem(mPageNumber, true);
                handler.postDelayed(this, 3000);
            }
        };
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }

    public void createContainer(){
        for (int i=0;i<images.length;i++){
            items=new SupportItems();
            items.setName(supportItems[i]);
            items.setImageView(images[i]);
            arrayList.add(items);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }






    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    @Override
    public void onItemClick(int position) {
        FragmentTransaction fragmentTransaction=
                getActivity().getSupportFragmentManager().beginTransaction();
        Fragment fragment=null;
        switch(supportItems[position]){
            case "Fruit Nursery":
              fragment= FruitNurseryFragment.newInstance(supportItems[position],null);
                break;
            case "Vegetable Nursery":
                fragment= VegatableNurseryFragment.newInstance(supportItems[position],null);
                break;
            case "Ornamental Nursery":
                fragment= OrnamentalNurseryFragment.newInstance(supportItems[position],null);
                break;
            case "Forest Nursery":
                fragment= FruitNurseryFragment.newInstance(supportItems[position],null);
                break;
            case "Tools and Equipment":
                fragment= ToolFragment.newInstance(supportItems[position],null);
                break;
            case "Gardens":
                fragment= ProductFragment.newInstance(supportItems[position],null);


              // fragment= NewProdectFragment.newInstance(supportItems[position],null);

              //  fragment= GardenFragment.newInstance(supportItems[position],null);
                break;


        }
        if(fragment!=null){
            fragmentTransaction.replace(R.id.main_container,fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */







    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    public static class PlaceholderFragment extends Fragment{
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.home_image_pager, container, false);
            int pageNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            //textView.setText(getString(R.string.section_format, pageNumber));
            ImageView tutorialImage = (ImageView) rootView.findViewById(R.id.pager_image);
            tutorialImage.setImageResource(pagerImage[pageNumber - 1]);
            return rootView;
        }

    }





    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            return pagerImage.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }


}
