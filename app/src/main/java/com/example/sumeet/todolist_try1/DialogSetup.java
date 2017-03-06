package com.example.sumeet.todolist_try1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

/**
 * Created by Sumeet on 22-02-2017.
 */

public class DialogSetup extends DialogFragment {
    Context context;
    dbHelper db;
    Home home = new Home();
    int updateId;
    AdapterList adapterList = new AdapterList();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog, container);
        Button savebtn = (Button) view.findViewById(R.id.btn_save);
        Button cancelbtn = (Button) view.findViewById(R.id.btn_cancel);


        final EditText title = (EditText) view.findViewById(R.id.et_tittle);
        final EditText description = (EditText) view.findViewById(R.id.et_description);
        final DatePicker datedata = (DatePicker) view.findViewById(R.id.dp_date);

        db = new dbHelper(getActivity());
        final dataContents dataContentsobj = new dataContents();

        final int i;
        i = getArguments().getInt("Ins/del");
        if (i == 1) {
            updateId = getArguments().getInt("selectid");
            Cursor cr = db.getdataContents(updateId);
            cr.moveToFirst();

            title.setText(cr.getString(cr.getColumnIndex("title")));
            description.setText(cr.getString(cr.getColumnIndex("description")));
            String[] datedat = cr.getString(cr.getColumnIndex("date")).split("/");
            datedata.updateDate(Integer.parseInt(datedat[2]), (Integer.parseInt(datedat[1]) - 1), Integer.parseInt(datedat[0]));
        }


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    dataContentsobj.setKey_Tittle(title.getText().toString());
                    dataContentsobj.setKey_Description(description.getText().toString());
                    dataContentsobj.setKey_Date(datedata.getDayOfMonth() + "/" + (datedata.getMonth() + 1) + "/" + datedata.getYear());
                    db.createToDo(dataContentsobj);

                    getDialog().dismiss();
                    ((Home) getActivity()).refrehsList();


                }

                if (i == 1) {
                    dataContentsobj.setKey_Tittle(title.getText().toString());
                    dataContentsobj.setKey_Description(title.getText().toString());
                    dataContentsobj.setKey_Date(datedata.getDayOfMonth() + "/" + (datedata.getMonth() + 1 + "/" + datedata.getYear()));
                    dataContentsobj.setKey_id(updateId);
                    db.updatedataContents(dataContentsobj);
                    ((Home) getActivity()).refrehsList();
                }
            }
        });


        return view;
    }



}
