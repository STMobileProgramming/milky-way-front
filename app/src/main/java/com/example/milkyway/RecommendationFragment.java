package com.example.milkyway;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class RecommendationFragment extends Fragment {

    ImageView[] imgArray = new ImageView[15];
    TextView[] placeArray = new TextView[15];
    TextView[] desArray = new TextView[15];
    TextView dataload;
    LinearLayout reco;

    int[] imgId = new int[15];
    int[] placeId = new int[15];
    int[] desId = new int[15];
    ArrayList<String> savedimg = new ArrayList<>();
    ArrayList<String> savedlink = new ArrayList<>();
    ArrayList<String> saveddes = new ArrayList<>();
    ArrayList<String> savedpl = new ArrayList<>();

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);

        reco = (LinearLayout) view.findViewById(R.id.reco);
        dataload = (TextView) view.findViewById(R.id.dataload);

        for(int i = 0; i < 15; i++){
            imgId[i] = getResources().getIdentifier("imageView"+(i+1), "id", getActivity().getPackageName());
            placeId[i] = getResources().getIdentifier("place"+(i+1), "id", getActivity().getPackageName());
            desId[i] = getResources().getIdentifier("description"+(i+1), "id", getActivity().getPackageName());
            imgArray[i] = (ImageView) view.findViewById(imgId[i]);
            placeArray[i] = (TextView) view.findViewById(placeId[i]);
            desArray[i] = (TextView) view.findViewById(desId[i]);
        }

        regionData task = new regionData();
        task.execute();
        return view;
    }
    private class regionData extends AsyncTask<Void, Void, ArrayList<ListData>>{

        @Override
        protected ArrayList<ListData> doInBackground(Void... voids) {
            ArrayList<ListData> data = new ArrayList<>();
            try{
                Document doc = Jsoup.connect("https://kr.trip.com/toplist/topexperience/seoul-100000001628").get();
                Elements recitem = doc.select("div.carousel");
                Elements recpla = doc.select(".HeaderText-sc-1shkl1n-3");
                Elements recdes = doc.select(".InnerContainer-sc-111i5j7-27");

                String strimg = null;
                String strlink = null;

                for(int i = 0; i < 20; i++){
                    if(i == 4 || i == 5 || i == 8 || i == 9 || i == 13) continue;
                    Element bufflink = recitem.get(i);
                    Element buffpla = recpla.get(i);

                    strimg = bufflink.toString().split("src=")[1].split("width")[0].split("style")[0];
                    strimg = strimg.substring(1, strimg.length()-2);
                    Log.e("이미지", strimg);
                    strlink = bufflink.toString().split("href=")[1].split(">")[0];
                    strlink = strlink.substring(1, strlink.length()-1);
                    Log.e("링크", strlink);
                    Log.e("장소", buffpla.text());

                    savedimg.add(strimg);
                    savedlink.add(strlink);
                    savedpl.add(buffpla.text());
                }
                for(int i = 0; i < 17; i++){
                    if(i == 4 || i == 11) continue;
                    Element buffdes = recdes.get(i);
                    saveddes.add(buffdes.text().split(":")[1].substring(1));
                }
                for(int i = 0;i < 15;i++){
                    data.add(new ListData(savedimg.get(i), savedlink.get(i), savedpl.get(i), saveddes.get(i)));
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(ArrayList<ListData> arrayList){
            Log.e("크기", Integer.toString(arrayList.size()));
            for(int i = 0; i < 15; i++){
                String Url = arrayList.get(i).getImgUrl();
                Glide.with(getActivity()).load(Url).into(imgArray[i]);
                placeArray[i].setText("- " + arrayList.get(i).getPlace() + " -");
                desArray[i].setText(arrayList.get(i).getDescript());
            }
            dataload.setVisibility(View.INVISIBLE);
            reco.setVisibility(View.VISIBLE);
        }
    }
}