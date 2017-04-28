package com.patrick.developer.babymonitoring.tools.dialog_manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.dao.BabyDao;
import com.patrick.developer.babymonitoring.fragment.HomeFragment;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.tools.FragmentTool;
import com.patrick.developer.babymonitoring.tools.constant.Sexe;
import com.patrick.developer.babymonitoring.tools.date_manager.DateManager;

import java.nio.BufferUnderflowException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by developer on 4/27/17.
 */

public class AddBabyDialogBox {

    protected static EditText dateOfBirth;
    protected static boolean isInsert = false;

    public static void addBaby(final Context context, final Baby baby) {
        final Handler handler = new Handler();

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.save_baby_layout, null);

        final EditText firstName = (EditText) dialogView.findViewById(R.id.firstName);
        final EditText lastName = (EditText) dialogView.findViewById(R.id.lastName);

        final RadioGroup sexeGroup = (RadioGroup) dialogView.findViewById(R.id.sexeGroup);

        final RadioButton boyRadio = (RadioButton) dialogView.findViewById(R.id.sexeBoy);
        final RadioButton girlRadio = (RadioButton) dialogView.findViewById(R.id.sexeGirl);

        boyRadio.setOnClickListener(radioListener(girlRadio));
        girlRadio.setOnClickListener(radioListener(boyRadio));

        dateOfBirth = (EditText) dialogView.findViewById(R.id.dateOfBirth);
        dateOfBirth.addTextChangedListener(tw);

        firstName.setText(baby.getFirstName());
        lastName.setText(baby.getLastName());
        if(baby.getSexe() == Sexe.Fille.name()) {
            boyRadio.setChecked(false);
            girlRadio.setChecked(true);
        } else {
            boyRadio.setChecked(true);
            girlRadio.setChecked(false);
        }
        dateOfBirth.setText(baby.getDatOfBirth() == null ? "" : DateManager.dateToString(baby.getDatOfBirth()));

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setCancelable(false);

        final AlertDialog dialog = alertDialogBuilder.setView(dialogView).create();
        dialog.show();

        Button saveButton = (Button) dialogView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                baby.setFirstName(firstName.getText().toString());
                baby.setLastName(lastName.getText().toString());
                Date date;
                try {
                    date = DateManager.stringToDate(dateOfBirth.getText().toString());
                } catch (ParseException e) {
                    date = null;
                }
                baby.setDatOfBirth(date);
                baby.setSexe(getSexe(sexeGroup));

                new Thread() {
                    @Override
                    public void run() {
                        final Runnable run = new Runnable() {
                            @Override
                            public void run() {
                                ((Activity) context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isInsert) {
                                            Fragment fragment = new HomeFragment();
                                            FragmentTool.replaceFragment(context, fragment);
                                        } else {
                                            addBaby(context, baby);
                                            Toast.makeText(context, "Tous les champs sont obligatoire!", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                });
                            }
                        };
                        try {
                            if(isValid(baby)) {
                                final Baby babyDb = new BabyDao(context).create(baby);
                                isInsert = babyDb.getId() != null;
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        handler.post(run);
                    }
                }.start();
                dialog.cancel();
            }
        });

        Button cancelButton = (Button) dialogView.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    protected static String getSexe(final RadioGroup sexGroup) {
        int radioChecked = sexGroup.getCheckedRadioButtonId();
        switch (radioChecked) {
            case R.id.sexeBoy:
                return Sexe.Garçon.name();
            case R.id.sexeGirl:
                return Sexe.Fille.name();
            default: break;
        }
        return "";
    }

    protected static View.OnClickListener radioListener(final RadioButton radioButton) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton.setChecked(false);
            }
        };
    }

    protected static TextWatcher tw = new TextWatcher() {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int before, int i1, int i2) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8){
                    clean = clean + ddmmyyyy.substring(clean.length());
                }else{
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    if(mon > 12) mon = 12;
                    cal.set(Calendar.MONTH, mon-1);
                    year = (year<1900)?1900:(year>2100)?2100:year;
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d",day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                dateOfBirth.setText(current);
                dateOfBirth.setSelection(sel < current.length() ? sel : current.length());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    protected static boolean isValid(Baby b) {
        if(b.getFirstName().isEmpty() || b.getLastName().isEmpty() || b.getDatOfBirth() == null || b.getSexe().isEmpty()) {
            return false;
        }
        return true;
    }
}
