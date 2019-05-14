package com.example.cs441_p6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OptionsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private OnFragmentInteractionListener mListener;
    private EditText name;
    private Button submitButton;
    private TextInputEditText name_field;

    private RequestQueue queue;
    private String url;


    public OptionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionsFragment newInstance(String param1, String param2) {
        OptionsFragment fragment = new OptionsFragment();
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
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

        name = getView().findViewById(R.id.options_name);
        submitButton = getView().findViewById(R.id.options_submit);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        url ="http://cs.binghamton.edu/~ocollin1/whatever.php";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.options_fragment, container, false);
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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //setContentView(R.layout.answer);
                    //final String guess = guessField.getText().toString();
                   //int value = Integer.parseInt(guess);

                    //String name = nameField.getText().toString().replaceAll(" ", "_");

                    if(name.equals("")){
                        name.setText("Anonymous");
                    }

                    //String name_given = name.getText().toString();
                    String url ="http://cs.binghamton.edu/~ocollin1/sendname.php?name=" + name.toString();

                    System.out.println(url);
                    //mainText.setText(url);

                    // Request a string response from the provided URL.
                    final StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    System.out.print(response);
                                    int maxL = 500;
                                    if(response.length()<500) {
                                        maxL = response.length();
                                    }

                                    String result = response.substring(0,1);
                                    System.out.println(result);
                                    if(result.equals("0")){
                                        //mainText.setText("Sorry, but " + guess + " is incorrect!");

                                    } else {
                                        //setContentView(R.layout.answer);
                                    }
                                    //mainText.setText(response.substring(0,maxL));
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            name.setText("That didn't work!");
                            System.out.println(error);
                        }
                    });


                    queue.add(getRequest);
                    //guessField.setHint(guess);

                } catch (Exception e){
                    //guessField.setHint("numbers please...");

                }
            }
        });


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
