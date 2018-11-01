package com.example.admin.aryanadmin.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.aryanadmin.R;
import com.example.admin.aryanadmin.model.NewsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewsFragment extends Fragment {


    private static final String TAG = "NewsFragment";
    private OnFragmentInteractionListener mListener;
    private View mView;
    private EditText editTextKannada, editTextEnglish;
    private Button buttonSubmit;
    private DatabaseReference databaseReferenceKannada, databaseReferenceEnglish;
    private boolean isEnUpdated, isKnUpdated;
    NewsModel model, model2;
    String msg1, msg2;
    int count;


    public NewsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_news, container, false);
        editTextEnglish = mView.findViewById(R.id.en_et_news);
        editTextKannada = mView.findViewById(R.id.kn_et_news);
        buttonSubmit = mView.findViewById(R.id.btn_submit);

        getData();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEnUpdated = false;
                isKnUpdated = false;
                sendData();
            }
        });

        return mView;
    }

    private void getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String strDate= formatter.format(date);
        databaseReferenceEnglish = database.getReference().child("en").child("currentaffairs").child(strDate);
        databaseReferenceKannada = database.getReference().child("ka").child("currentaffairs").child(strDate);

        databaseReferenceEnglish.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model = new NewsModel();
                model = dataSnapshot.getValue(NewsModel.class);

                if(model!=null) {
                    count = model.getCount();
                    msg1 = model.getMessage();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReferenceKannada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model2 = new NewsModel();
                model2 = dataSnapshot.getValue(NewsModel.class);
                if(model2!=null)
                msg2 = model2.getMessage();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendData() {

        if(count==0) {
            model = new NewsModel();
            model.setCount(1);
            model.setMessage(1 + ". " + editTextEnglish.getText().toString());

            model2 = new NewsModel();
            model2.setCount(1);
            model2.setMessage(1 + ". " + editTextKannada.getText().toString());
        }
        else {
            model = new NewsModel();
            model2 = new NewsModel();

            model.setCount(count+1);
            model2.setCount(count+1);
            count = count+1;
            model.setMessage(msg1+"\n"+count+". "+editTextEnglish.getText().toString());
            model2.setMessage(msg2+"\n"+count+". "+editTextKannada.getText().toString());
        }

      databaseReferenceEnglish.setValue(model);
      databaseReferenceKannada.setValue(model2);
      editTextKannada.setText("");
      editTextEnglish.setText("");
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
