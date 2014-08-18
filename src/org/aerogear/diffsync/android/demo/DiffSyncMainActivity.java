/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.aerogear.diffsync.android.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jboss.aerogear.diffsync.ClientDocument;
import org.jboss.aerogear.diffsync.DefaultClientDocument;
import org.jboss.aerogear.diffsync.DiffSyncClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class DiffSyncMainActivity extends Activity implements Observer {

    private ProgressDialog dialog;
    private DiffSyncClient<String> syncClient;
    private String documentId;
    private String clientId;
    private TextView profession;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        clientId = UUID.randomUUID().toString();
        documentId = getString(R.string.documentId);
        
        final JSONObject document = new JSONObject();
        try {
            document.put("id", documentId);
            document.put("clientId", clientId);
            final JSONObject content = new JSONObject();
            content.put("name", "Luke Skywalker");
            content.put("profession", "Jedi");
            final JSONArray hobbies = new JSONArray();
            hobbies.put(new JSONObject().put("id", UUID.randomUUID().toString()).put("description", "Fighting the Dark Side"));
            hobbies.put(new JSONObject().put("id", UUID.randomUUID().toString()).put("description", "going into Tosche Station to pick up some power converters"));
            hobbies.put(new JSONObject().put("id", UUID.randomUUID().toString()).put("description", "Kissing his sister"));
            hobbies.put(new JSONObject().put("id", UUID.randomUUID().toString()).put("description", "Bulls eyeing Womprats on his T-16"));
            content.put("hobbies", hobbies);
            document.put("content", content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("onCreate", "json" + document.toString());

        ((TextView) findViewById(R.id.name)).setText(getName(document));
        profession = (TextView) findViewById(R.id.profession);
        final String seedDoc = getString(R.string.seedDoc);
        profession.setText(getProfession(document));

        Log.i("onCreate", "observer :" + this);
        syncClient = DiffSyncClient.<String>forHost(getString(R.string.serverHost))
                .port(Integer.parseInt(getString(R.string.serverPort)))
                .observer(this)
                .build();
        
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    try {
                        syncClient.connect();
                        final ClientDocument<String> document = clientDoc(documentId, clientId, seedDoc);
                        Log.i("onCreate", "Seed Document:" + document);
                        syncClient.addDocument(document);
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }.execute();

        final Button sync = (Button) findViewById(R.id.sync);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.i(DiffSyncMainActivity.class.getName(), "Profession:" + profession.getText());
                final ClientDocument<String> document = clientDoc(documentId, clientId, profession.getText().toString());

                dialog = ProgressDialog.show(DiffSyncMainActivity.this, getString(R.string.wait), getString(R.string.syncing), true, false);
                
                new AsyncTask<ClientDocument, Void, String>() {

                    @Override
                    protected String doInBackground(final ClientDocument... params) {
                        Log.i("doInBackground", "Document:" + params[0]);
                        syncClient.diffAndSend(params[0]);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(final String s) {
                        super.onPostExecute(s);
                        dialog.dismiss();
                        //TODO should be able to update the UI in this method as it runs on the main/ui thread.
                    }
                }.execute(document);
            }
            
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            syncClient.connect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static ClientDocument<String> clientDoc(final String id, final String clientId, final String content) {
        return new DefaultClientDocument<String>(id, clientId, content);
    }
    
    private static String getName(final JSONObject json) {
        return (String) getContent("name", json);
    }
    
    private static String getProfession(final JSONObject json) {
        return (String) getContent("profession", json);
    }
    
    private static Object getContent(final String name, final JSONObject from) {
        try {
            return from.getJSONObject("content").get(name);
        } catch (JSONException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    } 

    @Override
    public void update(final Observable observable, final Object data) {
        Log.i(DiffSyncMainActivity.class.getName(), "updated:" + data);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ClientDocument<String> document = (ClientDocument<String>) data;
                profession.setText(document.content());
            }
        });
    }
}
