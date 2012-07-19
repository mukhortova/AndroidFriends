package com.noveo.internship.androidFriends.activity;

import android.accounts.*;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoginActivity extends ListActivity {

    /**
     * Logging level for HTTP requests/responses.
     */
    private static final Level LOGGING_LEVEL = Level.OFF;

    private static final String TAG = "TasksSample";

    // This must be the exact string, and is a special for alias OAuth 2 scope
    // "https://www.googleapis.com/auth/tasks"
//  private static final String AUTH_TOKEN_TYPE = "https://www.google.com/m8/feeds";
    private static final String AUTH_TOKEN_TYPE = "https://www.googleapis.com/auth/tasks";
    private static final int MENU_ACCOUNTS = 0;

    private static final int REQUEST_AUTHENTICATE = 0;

    static final String PREF_ACCOUNT_NAME = "accountName";

    static final String PREF_AUTH_TOKEN = "authToken";

    GoogleAccountManager accountManager;

    SharedPreferences settings;

    String accountName;

    String credential = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getPreferences(MODE_PRIVATE);
        accountName = settings.getString(PREF_ACCOUNT_NAME, null);
        System.out.println("accountName: " + accountName);
        System.out.println("authToken: " + settings.getString(PREF_AUTH_TOKEN, null));
        credential = settings.getString(PREF_AUTH_TOKEN, null);
        Logger.getLogger("com.google.api.client").setLevel(LOGGING_LEVEL);
        accountManager = new GoogleAccountManager(this);
        gotAccount();
    }

    void gotAccount() {
        Account account = accountManager.getAccountByName(accountName);
        if (account == null) {
            chooseAccount();
            return;
        }
        if (credential != null) {
            onAuthToken();
            return;
        }
        accountManager.getAccountManager()
                .getAuthToken(account, AUTH_TOKEN_TYPE, true, new AccountManagerCallback<Bundle>() {

                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            Bundle bundle = future.getResult();
                            if (bundle.containsKey(AccountManager.KEY_INTENT)) {
                                Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
                                intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivityForResult(intent, REQUEST_AUTHENTICATE);
                            } else if (bundle.containsKey(AccountManager.KEY_AUTHTOKEN)) {
                                setAuthToken(bundle.getString(AccountManager.KEY_AUTHTOKEN));
                                System.out.println("(got account) got new auth token: " + bundle.getString(AccountManager.KEY_AUTHTOKEN));
                                onAuthToken();
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                    }
                }, null);
    }

    private void chooseAccount() {
        accountManager.getAccountManager().getAuthTokenByFeatures(GoogleAccountManager.ACCOUNT_TYPE,
                AUTH_TOKEN_TYPE,
                null,
                LoginActivity.this,
                null,
                null,
                new AccountManagerCallback<Bundle>() {

                    public void run(AccountManagerFuture<Bundle> future) {
                        Bundle bundle;
                        try {
                            bundle = future.getResult();
                            setAccountName(bundle.getString(AccountManager.KEY_ACCOUNT_NAME));
                            setAuthToken(bundle.getString(AccountManager.KEY_AUTHTOKEN));
                            System.out.println("(choose) got new auth token: " + bundle.getString(AccountManager.KEY_AUTHTOKEN));
                            System.out.println("(choose) got new user: " + bundle.getString(AccountManager.KEY_ACCOUNT_NAME));
                            onAuthToken();
                        } catch (OperationCanceledException e) {
                            // user canceled
                        } catch (AuthenticatorException e) {
                            Log.e(TAG, e.getMessage(), e);
                        } catch (IOException e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                    }
                },
                null);
    }

    void setAccountName(String accountName) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_ACCOUNT_NAME, accountName);
        editor.commit();
        this.accountName = accountName;
    }

    void setAuthToken(String authToken) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_AUTH_TOKEN, authToken);
        editor.commit();
        credential = authToken;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_AUTHENTICATE:
                if (resultCode == RESULT_OK) {
                    gotAccount();
                } else {
                    chooseAccount();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (accountManager.getAccounts().length >= 2) {
            menu.add(0, MENU_ACCOUNTS, 0, "Switch Account");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ACCOUNTS:
                chooseAccount();
                return true;
        }
        return false;
    }

    void onAuthToken() {
        //new AsyncLoadTasks(this).execute();
    }
}
