package org.aerogear.diffsync.android.demo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiffSyncMainActivityTest extends ActivityInstrumentationTestCase2<DiffSyncMainActivity> {
    
    private DiffSyncMainActivity activity;

    public DiffSyncMainActivityTest() {
        super(DiffSyncMainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testSyncButton() {
        final View view = activity.findViewById(R.id.sync);
        assertTrue(view instanceof Button);
    }
    
    public void testProfessionTextView() {
        final View view = activity.findViewById(R.id.profession);
        assertTrue(view instanceof TextView);
    }
    
    public void testSync() {
        final Button syncButton = (Button) activity.findViewById(R.id.sync);
        final TextView profession = (TextView) activity.findViewById(R.id.profession);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                profession.setText("Jedi");
                syncButton.requestFocus();
                syncButton.performClick();
            }
        });
        
        
    }

}
