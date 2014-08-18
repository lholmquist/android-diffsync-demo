package org.aerogear.diffsync.android.demo;

import android.content.Context;
import org.jboss.aerogear.diffsync.BackupShadowDocument;
import org.jboss.aerogear.diffsync.ClientDocument;
import org.jboss.aerogear.diffsync.Edit;
import org.jboss.aerogear.diffsync.ShadowDocument;
import org.jboss.aerogear.diffsync.client.ClientInMemoryDataStore;

import java.util.Queue;

public class AndroidInMemoryDataStore extends ClientInMemoryDataStore {

    private final Context context;

    public AndroidInMemoryDataStore(final Context context) {
        this.context = context;
    }

    @Override
    public void saveShadowDocument(ShadowDocument<String> shadowDocument) {
        super.saveShadowDocument(shadowDocument);
    }

    @Override
    public ShadowDocument<String> getShadowDocument(String documentId, String clientId) {
        return super.getShadowDocument(documentId, clientId);
    }

    @Override
    public void saveBackupShadowDocument(BackupShadowDocument<String> backupShadow) {
        super.saveBackupShadowDocument(backupShadow);
    }

    @Override
    public BackupShadowDocument<String> getBackupShadowDocument(String documentId, String clientId) {
        return super.getBackupShadowDocument(documentId, clientId);
    }

    @Override
    public void saveClientDocument(ClientDocument<String> document) {
        super.saveClientDocument(document);
    }

    @Override
    public ClientDocument<String> getClientDocument(String documentId, String clientId) {
        return super.getClientDocument(documentId, clientId);
    }

    @Override
    public void saveEdits(Edit edit) {
        super.saveEdits(edit);
    }

    @Override
    public void removeEdit(Edit edit) {
        super.removeEdit(edit);
    }

    @Override
    public Queue<Edit> getEdits(String documentId, String clientId) {
        return super.getEdits(documentId, clientId);
    }

    @Override
    public void removeEdits(String documentId, String clientId) {
        super.removeEdits(documentId, clientId);
    }
    
    private void updateObservers() {
    }
}
