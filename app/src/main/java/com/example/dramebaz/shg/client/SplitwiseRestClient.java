package com.example.dramebaz.shg.client;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.scribe.builder.api.Api;

public class SplitwiseRestClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = SplitwiseApi.class; // Change this
public static final String REST_URL = "https://secure.splitwise.com/api/v3.0"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "";       // Change this
    public static final String REST_CONSUMER_SECRET = ""; // Change this
    public static final String REST_CALLBACK_URL = "oauth://codepathtweets"; // Change this (here and in manifest)

    public SplitwiseRestClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }

    /**
     * http://dev.splitwise.com/dokuwiki/doku.php?id=get_current_user
     */
    public void getCurrentUser(JsonHttpResponseHandler handler) {
        String apiUrl = getApiUrl("get_current_user");
        RequestParams params = new RequestParams();
        getClient().get(apiUrl, params, handler);
    }

    /**
     * http://dev.splitwise.com/dokuwiki/doku.php?id=get_groups
     * @param handler
     */
    public void getGroups(JsonHttpResponseHandler handler) {
        String apiUrl = getApiUrl("get_groups");
        RequestParams params = new RequestParams();
        getClient().get(apiUrl, params, handler);
    }

    /**
     * http://dev.splitwise.com/dokuwiki/doku.php?id=create_group
     */
    public void createGroup(JsonHttpResponseHandler handler, String groupName) {
        String apiUrl = getApiUrl("create_group");
        RequestParams params = new RequestParams();
        if(groupName != null) {
            params.put("name", groupName);
        }
        getClient().post(apiUrl, params,handler);
    }

    public void addGroupMember(JsonHttpResponseHandler handler,Integer group_id, String first_name, String last_name,String email) {
        String apiUrl = getApiUrl("add_user_to_group");
        RequestParams params = new RequestParams();
        if(group_id != null) {
            params.put("group_id", group_id);
        }
        if(first_name != null) {
            params.put("first_name", first_name);
        }
        if(last_name != null) {
            params.put("last_name", last_name);
        }
        if(email != null) {
            params.put("email", email);
        }
        getClient().post(apiUrl, params,handler);
    }

    public void createFriend(JsonHttpResponseHandler handler,String user_email, String user_first_name, String user_last_name) {
        String apiUrl = getApiUrl("create_friend");
        RequestParams params = new RequestParams();
        if(user_email != null) {
            params.put("user_email", user_email);
        }
        if(user_first_name != null) {
            params.put("user_first_name", user_first_name);
        }
        if(user_last_name != null) {
            params.put("user_last_name", user_last_name);
        }
        getClient().post(apiUrl, params,handler);
    }

    /**
     * http://dev.splitwise.com/dokuwiki/doku.php?id=get_expenses
     * @param handler
     */
    public void getExpenses(JsonHttpResponseHandler handler, Integer groupId, Integer limit, Integer offset, Integer friendshipId) {
        String apiUrl = getApiUrl("get_expenses");
        RequestParams params = new RequestParams();
        if(groupId != null) {
            params.put("group_id", groupId);
        }
        if(friendshipId != null) {
            params.put("friendship_id", friendshipId);
        }
        if(limit != null) {
            params.put("limit", limit);
        }
        if(offset != null) {
            params.put("offset", offset);
        }
        getClient().get(apiUrl, params, handler);
    }

    /**
     * http://dev.splitwise.com/dokuwiki/doku.php?id=get_friends
     * @param handler
     */
    public void getFriends(JsonHttpResponseHandler handler) {
        String apiUrl = getApiUrl("get_friends");
        RequestParams params = new RequestParams();
        getClient().get(apiUrl, params, handler);
    }

    public void deleteFriend(JsonHttpResponseHandler handler, Integer friendId) {
        String apiUrl = getApiUrl("delete_friend/"+friendId);
        RequestParams params = new RequestParams();
        getClient().post(apiUrl, handler);
    }

    public void deleteGroup(JsonHttpResponseHandler handler, Integer groupId) {
        String apiUrl = getApiUrl("delete_group/"+groupId);
        RequestParams params = new RequestParams();
        getClient().post(apiUrl, handler);
    }
}
