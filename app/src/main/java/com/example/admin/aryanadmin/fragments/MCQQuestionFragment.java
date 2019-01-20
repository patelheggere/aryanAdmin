package com.example.admin.aryanadmin.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.aryanadmin.AdminBaseApplication;
import com.example.admin.aryanadmin.R;
import com.example.admin.aryanadmin.base.BaseFragment;
import com.example.admin.aryanadmin.model.MCQQuestionModel;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MCQQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MCQQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MCQQuestionFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private EditText editTextQno, editTextQuestion, editTextNoOptions, editTextCorrectAns, op1, op2, op3, op4;
    private Button mButtonSubmit;
    private DatabaseReference databaseReferenceEnglish;

    private OnFragmentInteractionListener mListener;

    public MCQQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MCQQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MCQQuestionFragment newInstance(String param1, String param2) {
        MCQQuestionFragment fragment = new MCQQuestionFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_mcqquestion, container, false);
        initViews();
        return mView;
    }

    private void initViews() {
        editTextCorrectAns = mView.findViewById(R.id.editTextCorrectAns);
        editTextNoOptions = mView.findViewById(R.id.editTextNoOfOptions);
        editTextQno = mView.findViewById(R.id.editTextQNo);
        editTextQuestion = mView.findViewById(R.id.editTextQuestion);
        mButtonSubmit = mView.findViewById(R.id.buttonSubmit);
        op1 = mView.findViewById(R.id.editTextOption1);
        op2 = mView.findViewById(R.id.editTextOption2);
        op3 = mView.findViewById(R.id.editTextOption3);
        op4 = mView.findViewById(R.id.editTextOption4);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MCQQuestionModel mcqQuestionModel = new MCQQuestionModel();
                mcqQuestionModel.setAnswered(false);
                mcqQuestionModel.setmCorrectAns(Integer.parseInt(editTextCorrectAns.getText().toString()));
                mcqQuestionModel.setmNoOfOptions(Integer.parseInt(editTextNoOptions.getText().toString()));
                mcqQuestionModel.setAnswered(false);
                mcqQuestionModel.setmQuestionNo(Integer.parseInt(editTextQno.getText().toString()));
                mcqQuestionModel.setmQuestion(editTextQuestion.getText().toString());
                MCQQuestionModel.Options options = new MCQQuestionModel.Options();
                options.setOp1(op1.getText().toString());
                options.setOp2(op2.getText().toString());
                options.setOp3(op3.getText().toString());
                options.setOp4(op4.getText().toString());
                mcqQuestionModel.setOptions(options);
                mcqQuestionModel.setViewed(false);
               databaseReferenceEnglish =  AdminBaseApplication.getFireBaseRef();
               databaseReferenceEnglish = databaseReferenceEnglish.child("ASSESSMENT").child("en");
               databaseReferenceEnglish.push().setValue(mcqQuestionModel);
            }
        });

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
}
