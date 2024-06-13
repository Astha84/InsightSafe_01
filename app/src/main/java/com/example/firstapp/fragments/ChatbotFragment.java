package com.example.firstapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapp.R;
import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatbotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatbotFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatbotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatbotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatbotFragment newInstance(String param1, String param2) {
        ChatbotFragment fragment = new ChatbotFragment();
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
        View view= inflater.inflate(R.layout.fragment_chatbot, container, false);

        EditText quest=view.findViewById(R.id.chatbot_ques);
        EditText answ=view.findViewById(R.id.chatbot_ans);
        Button askme=view.findViewById(R.id.chatbot_askme);


        // Use a model that's applicable for your use case (see "Implement basic use cases" below)
        GenerativeModel gm = new GenerativeModel(/* modelName */ "gemini-pro","AIzaSyDWEZ1rtMLwqyDaNU9_qrkGEN6Go7X54I4");

// Use the GenerativeModelFutures Java compatibility layer which offers
// support for ListenableFuture and Publisher APIs
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);
        askme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String q=quest.getText().toString();
                if(!q.isEmpty()){
                    Content content = new Content.Builder()
                            .addText(q)
                            .build();
                    Executor executor = Executors.newSingleThreadExecutor();

//                    executor.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Perform background operation
//                            // For example, make a network request
//                            // This will not block the UI thread
//                        }
//                    });

                    ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
                    Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
                        @Override
                        public void onSuccess(GenerateContentResponse result) {
                            String resultText = result.getText();
                            setText(answ, resultText);
                            System.out.println(resultText);
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            t.printStackTrace();
                        }
                    }, executor);



                }

            }
        });






    return view;
    }
    public void setText(final EditText text, final String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

}