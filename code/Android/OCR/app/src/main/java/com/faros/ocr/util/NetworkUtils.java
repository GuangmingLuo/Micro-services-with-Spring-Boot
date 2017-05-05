/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.faros.ocr.util;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String MENU_URL =//"http://10.1.15.101/api/menu"; //physical android device.
            "http://10.0.2.2/api/menu";   //virtual device

    final static String RESTS_URL = //"http://10.1.15.101/api/restaurants"; //physical android device.
            "http://10.0.2.2/api/restaurants";   //virtual device

    final static String MENU_PARAM_QUERY = "restaurantId";


    /**
     * Builds the URL used to query Menu.
     *
     * @param menuSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the Menu.
     */
    public static URL buildUrlForMenu(String menuSearchQuery) {
        Uri builtUri = Uri.parse(MENU_URL).buildUpon()
                .appendQueryParameter(MENU_PARAM_QUERY, menuSearchQuery)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Builds the URL used to query restaurants.
     * @return The URL to use to query the restaurants.
     */
    public static URL buildUrlForRests() {
        Uri builtUri = Uri.parse(RESTS_URL).buildUpon()
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}