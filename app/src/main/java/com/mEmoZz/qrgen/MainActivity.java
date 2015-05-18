/*
 * Copyright (C) 2015 Mohamed Fathy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mEmoZz.qrgen;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mEmoZz.qrgen.Tabs.AboutFragment;
import com.mEmoZz.qrgen.Tabs.EmailFragment;
import com.mEmoZz.qrgen.Tabs.PhoneFragment;
import com.mEmoZz.qrgen.Tabs.TextFragment;
import com.mEmoZz.qrgen.Tabs.VCardFragment;
import com.mEmoZz.qrgen.Tabs.WebFragment;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.vcard.VCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by Mohamed Fathy on 5/12/15.
 */

public class MainActivity extends ActionBarActivity implements MaterialTabListener {
    private MaterialTabHost tabHost;
    private ViewPager mPager;
    private EditText et1, et2, et3, et4, et5,
            et6, et7, et8, et9, et10, et11,
            et12, et13, et14, et15, et16,
            et17, et18, et19, et20, et21;
    private Bitmap bm;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Generator</font>"));

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        mPager = (ViewPager) this.findViewById(R.id.pager);

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setSelectedNavigationItem(position);

            }
        });

        for (int i = 0; i < mAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(mAdapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

    }

    @Override
    public void onTabSelected(MaterialTab tab) {

        mPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {

            super(fm);

        }

        public Fragment getItem(int num) {

            int position = num;

            if (position == 0) {

                return new TextFragment();

            } else if (position == 1) {

                return new PhoneFragment();

            } else if (position == 2) {

                return new WebFragment();

            } else if (position == 3) {

                return new EmailFragment();

            } else if (position == 4) {

                return new VCardFragment();

//            } else if (position == 5) {
//
//                return new AboutFragment();

            } else {

                return null;
            }

        }

        @Override
        public int getCount() {

            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return getResources().getStringArray(R.array.tabs)[position];
        }

    }

    public void textCore() {

        et1 = (EditText) findViewById(R.id.textInput);
        et2 = (EditText) findViewById(R.id.textSize1);
        et3 = (EditText) findViewById(R.id.textSize2);

        bm = QRCode.from(et1.getText().toString())
                .withSize(Integer.parseInt(et2.getText().toString()),
                        Integer.parseInt(et3.getText().toString()))
                .withCharset("UTF-8")
                .to(ImageType.PNG)
                .bitmap();

    }

    public void phoneCore() {

        et4 = (EditText) findViewById(R.id.phoneInput);
        et5 = (EditText) findViewById(R.id.phoneSize1);
        et6 = (EditText) findViewById(R.id.phoneSize2);

        VCard phone = new VCard(null)
                .setPhoneNumber(et4.getText().toString());

        bm = QRCode.from(phone)
                .withSize(Integer.parseInt(et5.getText().toString()),
                        Integer.parseInt(et6.getText().toString()))
                .withCharset("UTF-8")
                .to(ImageType.PNG)
                .bitmap();

    }

    public void webCore() {

        et19 = (EditText) findViewById(R.id.webInput);
        et20 = (EditText) findViewById(R.id.webSize1);
        et21 = (EditText) findViewById(R.id.webSize2);

        bm = QRCode.from(et19.getText().toString())
                .withSize(Integer.parseInt(et20.getText().toString()),
                        Integer.parseInt(et21.getText().toString()))
                .withCharset("UTF-8")
                .to(ImageType.PNG)
                .bitmap();

    }

    public void emailCore() {

        et7 = (EditText) findViewById(R.id.emailInput);
        et8 = (EditText) findViewById(R.id.emailSize1);
        et9 = (EditText) findViewById(R.id.emailSize2);

        bm = QRCode.from(et7.getText().toString())
                .withSize(Integer.parseInt(et8.getText().toString()),
                        Integer.parseInt(et9.getText().toString()))
                .withCharset("UTF-8")
                .to(ImageType.PNG)
                .bitmap();

    }

    public void vcardCore() {

        et10 = (EditText) findViewById(R.id.nameInput);
        et11 = (EditText) findViewById(R.id.vcardEmailInput);
        et12 = (EditText) findViewById(R.id.addressInput);
        et13 = (EditText) findViewById(R.id.titleInput);
        et14 = (EditText) findViewById(R.id.companyInput);
        et15 = (EditText) findViewById(R.id.phoneNoInput);
        et16 = (EditText) findViewById(R.id.webSiteInput);
        et17 = (EditText) findViewById(R.id.vcardSize1);
        et18 = (EditText) findViewById(R.id.vcardSize2);

        VCard vcard = new VCard(et10.getText().toString())
                .setEmail(et11.getText().toString())
                .setAddress(et12.getText().toString())
                .setTitle(et13.getText().toString())
                .setCompany(et14.getText().toString())
                .setPhoneNumber(et15.getText().toString())
                .setWebsite(et16.getText().toString());

        bm = QRCode.from(vcard)
                .withSize(Integer.parseInt(et17.getText().toString()),
                        Integer.parseInt(et18.getText().toString()))
                .withCharset("UTF-8")
                .to(ImageType.PNG)
                .bitmap();

    }

    public void mCore() {

        Toast.makeText(getApplicationContext(),
                "Generating code...",
                Toast.LENGTH_SHORT).show();

        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Button yupBtn, delBtn, shareBtn;

                Toast.makeText(getApplicationContext(),
                        "Success", Toast.LENGTH_SHORT)
                        .show();

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);

                dialog.setTitle("Want to save?");

                iv = (ImageView) dialog.findViewById(R.id.iv);
                iv.setImageBitmap(bm);

                shareBtn = (Button) dialog.findViewById(R.id.shareBtn);
                shareBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shareIt();
                    }
                });

                yupBtn = (Button) dialog.findViewById(R.id.btn1);
                yupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mStream();

                        dialog.dismiss();
                    }
                });

                delBtn = (Button) dialog.findViewById(R.id.btn2);
                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        Toast.makeText(getApplicationContext(),
                                "Deleted!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
                dialog.setCancelable(false);
                keepDialog(dialog);
            }
        }.start();

    }

    private static void keepDialog(Dialog dialog) {

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);

    }

    private void shareIt() {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/png");

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "title");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);


        OutputStream outStream;
        try {
            outStream = getContentResolver().openOutputStream(uri);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "I/O error",
                    Toast.LENGTH_SHORT).show();
        }

        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(shareIntent, "Share Image"));

    }

    public void mStream() {
        String fileName;

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/QRCodes");

        myDir.mkdirs();

        Date d = new Date();
        CharSequence s = DateFormat.format("MM-dd-yy hh-mm-ss", d.getTime());

        fileName = "QR-" + s + ".png";

        File file = new File(myDir, fileName);

        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "I/O error",
                    Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(getApplicationContext(),
                "Saved to:\n \\QRCodes\\" + fileName,
                Toast.LENGTH_LONG).show();

    }

}
