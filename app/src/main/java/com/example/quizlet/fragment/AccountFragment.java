package com.example.quizlet.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizlet.COMMON;
import com.example.quizlet.LoginActivity;
import com.example.quizlet.R;
import com.example.quizlet.RegisterActivity;
import com.example.quizlet.dao.UserDAO;
import com.example.quizlet.database.MyDatabase;
import com.example.quizlet.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private TextView txtAccountLoad, txtEmailLoad, getTxtAccountLoadAvatar, textViewChangePass;
    MyDatabase myDatabase;
    UserDAO userDAO;
    Button buttonLogOut;
    long idUser;
    ImageView imageView;
    LinearLayout linearLayout2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment(long idUser) {
        this.idUser = idUser;
        // Required empty public constructor
    }

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtAccountLoad = view.findViewById(R.id.txtAccountLoad);
        txtEmailLoad = view.findViewById(R.id.txtEmailLoad);
        buttonLogOut = view.findViewById(R.id.buttonLogOut);
        getTxtAccountLoadAvatar = view.findViewById(R.id.textViewAccount_register);
        imageView = view.findViewById(R.id.imageViewAccount);
        linearLayout2 = view.findViewById(R.id.linearLayout2);
        textViewChangePass = view.findViewById(R.id.textViewChangePass);
        myDatabase = Room.databaseBuilder(getActivity(), MyDatabase.class, COMMON.DB_NAME).allowMainThreadQueries().build();
        userDAO = myDatabase.createUserDAO();


        final User user = userDAO.getUser(idUser);
        Bitmap bitmap = BitmapFactory.decodeByteArray(user.getUriImage(), 0, user.getUriImage().length);
        imageView.setImageBitmap(bitmap);

        txtAccountLoad.setText(user.getUsername());
        txtEmailLoad.setText(user.getEmail());
        getTxtAccountLoadAvatar.setText(user.getUsername());
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.edit_email);
                final EditText enterNewEmail = dialog.findViewById(R.id.edit_email);
                Button save = dialog.findViewById(R.id.save_editemail);
                Button huy = dialog.findViewById(R.id.huy_editmail);
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                final COMMON common = new COMMON();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (enterNewEmail.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập email mới", Toast.LENGTH_SHORT).show();
                        } else if (!common.validateEmail(enterNewEmail.getText().toString())) {
                            Toast.makeText(getContext(), "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                        } else if (userDAO.checkEmailExist(enterNewEmail.getText().toString()) != null) {
                            Toast.makeText(getContext(), "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            userDAO.updateUserByemail(enterNewEmail.getText().toString(), user.getId());
                            Toast.makeText(getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });
                dialog.show();
            }
        });
        final boolean[] check = {false};

        textViewChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.edit_password);
                final EditText enterNewPass = dialog.findViewById(R.id.edit_pass);
                Button save = dialog.findViewById(R.id.save_editpass);
                Button huy = dialog.findViewById(R.id.huy_editpass);

                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (enterNewPass.getText().toString().equals("")) {
                            Toast.makeText(getContext(), "Vui lòng nhập password mới", Toast.LENGTH_SHORT).show();
                        } else {
                            userDAO.updateUserBypass(enterNewPass.getText().toString(), user.getId());
                            check[0] = true;
                            Toast.makeText(getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });
                dialog.show();

            }
        });

        if (check[0]) {
            final User user1 = userDAO.getUser(idUser);
            txtEmailLoad.setText(user1.getEmail() + "");
            check[0] = false;
        }
    }
}