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

package com.mEmoZz.qrgen.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mEmoZz.qrgen.R;

/**
 * Created by Mohamed Fathy on 5/12/15.
 */

public class AboutFragment extends Fragment {
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.about_fragment, container, false);

        tv = (TextView) fragView.findViewById(R.id.noOne);

        tv.setText(Html.fromHtml("<font color='#000000'>Special version only for</font> ... \uD83D\uDE0A"));

        return fragView;
    }
}
