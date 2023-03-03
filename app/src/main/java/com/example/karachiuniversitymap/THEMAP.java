package com.example.karachiuniversitymap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class THEMAP extends AppCompatActivity implements View.OnClickListener {

    ImageButton Ubit,KaneezFatima,MassCommunication,PharmacyDepartment,KUCircularRoad;
    ImageButton BiochemistryDepartment,AdminBlock,SilverJubleeGate,Gymnasium,HabibBank,PharmacyCanteen;
    ImageButton MuskanGate,KUBS,PublicAdminstrationDepartment,IBAUniversity;
    dijsktraMap dijsktraMap = new dijsktraMap();
    ArrayList<ImageButton> imageButtonArrayList = new ArrayList<>();
    ArrayList<Float> xpts = new ArrayList<>();
    ArrayList<Float> ypts = new ArrayList<>();
    ViewGroup myLayout;
    DrawView drawing;
    Canvas canvas = new Canvas();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themap);
        Ubit = findViewById(R.id.Ubit);
        KaneezFatima = findViewById(R.id.KaneezFatimaGate);
        MassCommunication = findViewById(R.id.MassCommunication);
        PharmacyDepartment = findViewById(R.id.PharmacyDepartment);
        KUCircularRoad = findViewById(R.id.KUCircularRoad);
        BiochemistryDepartment = findViewById(R.id.BiochemistryDepartment);
        AdminBlock = findViewById(R.id.AdminBlock);
        SilverJubleeGate = findViewById(R.id.SilverJubleeGate);
        Gymnasium = findViewById(R.id.Gymnasium);
        HabibBank = findViewById(R.id.HabibBank);
        PharmacyCanteen = findViewById(R.id.PharmacyCanteen);
        MuskanGate = findViewById(R.id.MuskanGate);
        KUBS = findViewById(R.id.KUBS);
        PublicAdminstrationDepartment = findViewById(R.id.PublicAdminstrationDepartment);
        IBAUniversity = findViewById(R.id.IBAUniversity);
        myLayout = findViewById(R.id.nodesImages);


        Ubit.setOnClickListener(this);
        KaneezFatima.setOnClickListener(this);
        MassCommunication.setOnClickListener(this);
        PharmacyDepartment.setOnClickListener(this);
        KUCircularRoad.setOnClickListener(this);
        BiochemistryDepartment.setOnClickListener(this);
        AdminBlock.setOnClickListener(this);
        SilverJubleeGate.setOnClickListener(this);
        Gymnasium.setOnClickListener(this);
        HabibBank.setOnClickListener(this);
        PharmacyCanteen.setOnClickListener(this);
        MuskanGate.setOnClickListener(this);
        KUBS.setOnClickListener(this);
        PublicAdminstrationDepartment.setOnClickListener(this);
        IBAUniversity.setOnClickListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Choose your Destination  ");
        builder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        Window window = alertDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.KaneezFatimaGate:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),0));
                break;
            case R.id.MassCommunication:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),1));
                break;
            case R.id.Ubit:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),2));
                break;
            case R.id.PharmacyDepartment:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),3));
                break;
            case R.id.KUCircularRoad:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),4));
                break;
            case R.id.BiochemistryDepartment:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),6));
                break;
            case R.id.AdminBlock:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),7));
                break;
            case R.id.SilverJubleeGate:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),8));
                break;
            case R.id.Gymnasium:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),9));
                break;
            case R.id.HabibBank:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),10));
                break;
            case R.id.PharmacyCanteen:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),11));
                break;
            case R.id.MuskanGate:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),12));
                break;
            case R.id.KUBS:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),13));
                break;
            case R.id.PublicAdminstrationDepartment:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),14));
                break;
            case R.id.IBAUniversity:
                showDirection(dijsktraMap.dijkstra(Pickuplocation(),15));
                break;
        }
    }

    public int Pickuplocation() {
        int value = 0;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getInt("location");
        }
        return value;
    }
    public ArrayList<String> removeDuplicates(ArrayList<String> arr) {
        for (int i = 0 ; i < arr.size();i++)
        {
            for (int j = i+1; j < arr.size(); j++)
            {
                    if(arr.get(i) == arr.get(j))
                    {
                        arr.remove(j-1);
                    }
            }
        }
        return arr;
    }

    public void showDirection(ArrayList<String> Path)
    {
        ArrayList<String> PathRevised = removeDuplicates(Path);
        char[] arrivalTime = PathRevised.get(PathRevised.size()-1).toCharArray();
        PathRevised.remove(PathRevised.size()-1);
        ArrayList<ImageButton> PathImage = addImageButtonToArrayList(PathRevised);
                        for (int i = 0; i < Path.size(); i++)
                        {
                            int[] posXY = new int[2];
                            imageButtonArrayList.get(i).getLocationOnScreen(posXY);
                            xpts.add((float) posXY[0]);
                            ypts.add((float) posXY[1]);

                        }
                        drawing = new DrawView(THEMAP.this,xpts,ypts);
                        myLayout.addView(drawing);
        if(arrivalTime.length == 2) {
            Toast.makeText(this, "Estimated Time of Arrival : " + arrivalTime[0] + arrivalTime[1] + " minutes", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Estimated Time of Arrival : " + arrivalTime[0]  + "minutes", Toast.LENGTH_LONG).show();
        }

    }
    public ArrayList<ImageButton> addImageButtonToArrayList(ArrayList<String> string)
    {
        for (String X : string)
        {
            switch (X) {
                case "Kaneez Fatima Gate":
                    imageButtonArrayList.add(KaneezFatima);
                    break;
                case "Mass Communication":
                    imageButtonArrayList.add(MassCommunication);
                    break;
                case "UBIT":
                    imageButtonArrayList.add(Ubit);
                    break;
                case "Pharmacy Department":
                    imageButtonArrayList.add(PharmacyDepartment);
                    break;
                case "KU Circular Road":
                    imageButtonArrayList.add(KUCircularRoad);
                    break;
                case "Biochemistry Department":
                    imageButtonArrayList.add(BiochemistryDepartment);
                    break;
                case "Admin Block":
                    imageButtonArrayList.add(AdminBlock);
                    break;
                case "Silver Jublee Gate":
                    imageButtonArrayList.add(SilverJubleeGate);
                    break;
                case "Gymnasium":
                    imageButtonArrayList.add(Gymnasium);
                    break;
                case "Habib Bank":
                    imageButtonArrayList.add(HabibBank);
                    break;
                case "Pharmacy Canteen":
                    imageButtonArrayList.add(PharmacyCanteen);
                    break;
                case "Muskan Gate":
                    imageButtonArrayList.add(MuskanGate);
                    break;
                case "KUBS":
                    imageButtonArrayList.add(KUBS);
                    break;
                case "Public Adminstration Department":
                    imageButtonArrayList.add(PublicAdminstrationDepartment);
                    break;
                case "IBA University":
                    imageButtonArrayList.add(IBAUniversity);
                    break;

            }
        }
        return imageButtonArrayList;
    }

}