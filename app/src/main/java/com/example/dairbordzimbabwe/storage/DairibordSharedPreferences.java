package com.example.dairbordzimbabwe.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dairbordzimbabwe.models.User;

public class DairibordSharedPreferences {

    private static final String SHARED_PRE_NAME = "dairibord_preferences";
    private static DairibordSharedPreferences m_instance;
    private final Context m_context;

    /* user properties */
    private static final String TAG_USER_ID = "user_id";
    private static final String TAG_FIRST_NAME = "first_name";
    private static final String TAG_LAST_NAME = "last_name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_USERNAME = "username";

    /**
     *
     * @param m_ctx
     */
    private DairibordSharedPreferences ( Context m_ctx ) {
        m_context = m_ctx;
    }

    /**
     *
     * @param m_ctx
     * @return instance of class, $m_instance
     */
    public static synchronized DairibordSharedPreferences get_instance ( Context m_ctx ) {
        if ( m_instance == null ) {
            m_instance = new DairibordSharedPreferences( m_ctx );
        }

        return m_instance;

    }

    /**
     *
     * @param user
     * save user's details to shared preferences
     */
    public void save_user ( User user ) {
        SharedPreferences shared_preferences = m_context.getSharedPreferences( SHARED_PRE_NAME,
                Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shared_preferences.edit();

        editor.putInt( TAG_USER_ID, user.getUser_id() );
        editor.putString( TAG_FIRST_NAME, user.getFirst_name() );
        editor.putString( TAG_LAST_NAME, user.getLast_name() );
        editor.putString( TAG_EMAIL, user.getEmail() );
        editor.putString( TAG_USERNAME, user.getUsername() );

        editor.apply();

    }

    /**
     *
     * @return
     * get signed in user's details
     */
    public User get_user () {
        SharedPreferences shared_preferences = m_context.getSharedPreferences( SHARED_PRE_NAME,
                Context.MODE_PRIVATE );

        return new User(
                shared_preferences.getInt( TAG_USER_ID, -1 ),
                shared_preferences.getString( TAG_FIRST_NAME, null ),
                shared_preferences.getString( TAG_LAST_NAME, null ),
                shared_preferences.getString( TAG_EMAIL, null ),
                shared_preferences.getString( TAG_USERNAME, null )
        );

    }

    /**
     *
     * @return boolean
     * checks if user is signed in or not
     */
    public boolean is_signed_in () {
        SharedPreferences shared_preferences = m_context.getSharedPreferences( SHARED_PRE_NAME,
                Context.MODE_PRIVATE );

        return shared_preferences.getInt( TAG_USER_ID, -1 ) != -1;

    }

    /**
     * delete user details from shared preferences and sign out user
     */
    public void sign_out() {
        SharedPreferences shared_preferences = m_context.getSharedPreferences( SHARED_PRE_NAME,
                Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = shared_preferences.edit();

        editor.clear();

        editor.apply();

    }

}
