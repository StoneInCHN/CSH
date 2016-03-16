package com.cheweishi.android.http;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.lb.enduser.android.ConstantValue;
import com.lb.enduser.android.R;
import com.lb.enduser.android.bean.Amr;
import com.lb.enduser.android.bean.ChatMsg;
import com.lb.enduser.android.bean.Picture;
import com.lb.enduser.android.bean.PushMessage;
import com.lb.enduser.android.net.BaseResponse;
import com.lb.enduser.android.net.PublicMessageResponse;
import com.lb.enduser.android.ui.ChatActivity;
import com.lidroid.xutils.util.LogUtils;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Tanck
 * @version : 1.0
 * @Description :
 * @date : 2015/1/22 10:41
 * @name : lb-enduser-android
 */
public class VolleyUtils {
    /**
     * 是否使用缓存 [配置true后，将在第一时间返回缓存数据]
     */
    private static final boolean useDiskCache = true;
    /**
     * 数据加密器  //TODO 未添加
     */
    private static KeyPair keyPair;
    private static ImageLoader imageLoader;

    /**
     * 用户消息
     */
    public static final String ME_CHAT = "0";
    /**
     * 律师消息
     */
    public static final String OTHER_CHAT = "1";


    /** */
    private String dirFilePath;

    /**
     * params的Tag
     */
    private String paramstag = null;

    /**
     * 网络请求队列
     */
    private RequestQueue requestQueue;
    /**
     * POST超时
     */
    private int TIMEOUT_POST = 10000;
    private Context context;
    private Gson gson = new Gson();

    private static final String TEXT = "text";

    private VolleyUtils() {
    }

    public interface RespListener<T> {

        void onResponse(T response);

        void onResponse(T response, String type);

        void onErrorResponse(String error);
    }

    /**
     * 初始化配置
     *
     * @param requestQueue 网络请求队列
     * @param context      context
     * @return
     */
    public static VolleyUtils init(RequestQueue requestQueue,
                                   Context context, ImageLoader imgLoader) {
        VolleyUtils v = new VolleyUtils();
        v.requestQueue = requestQueue;
        v.context = context;
        keyPair = KeyGenerator.generateKeys();
        imageLoader = imgLoader;
        return v;
    }

    /**
     * 该方法暂时废弃掉了
     *
     * @param url
     * @param listener
     * @param <T>
     */
    public <T> void post_json(String url, final Map<String, String> params1, final RespListener<String> listener) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.onResponse(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.d("error" + error.getMessage());
                listener.onErrorResponse(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", "1234567800");
                try {
                    params.put("password", KeyGenerator.encrypt("123456"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogUtils.d(params.get("password") + "---" + "password");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put("Accept", "application/json");

                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;

            }

        };
        requestQueue.add(request);
    }

    /**
     * 该方法为测试方法.
     *
     * @param url      服务器地址
     * @param params   Post参数
     * @param listener 回调监听
     * @param <T>
     */
    public <T> void post(String url, final Map<String, Object> params, final RespListener<String> listener) {

        if (NetworkUtils.isDisconnected(context)) {
            listener.onErrorResponse("当前没有网络...");
            return;
        }

        //保证userId不为空才调用更新
        if (LruCacheUtils.getJsonLruCache("userId") != null) {
            upClientId();
        }

        paramstag = null;
        if (null != params.get(ConstantValue.FUNCTION_TYPE_TAG)) {
            paramstag = params.get(ConstantValue.FUNCTION_TYPE_TAG).toString();
            params.remove(ConstantValue.FUNCTION_TYPE_TAG);
        }


        // TODO 可能需要判断参数是否为空
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                if (null != paramstag) {
                    listener.onResponse(jsonObject.toString(), paramstag);
                } else {
                    listener.onResponse(jsonObject.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.d(error.getMessage());
                if (null != error.networkResponse) {
                    byte[] htmlBodyBytes = error.networkResponse.data;
                    LogUtils.d(new String(htmlBodyBytes));
                }
                listener.onErrorResponse(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Accept", "application/json");

                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        request.setRetryPolicy(getRetryPolicy());
        requestQueue.add(request);
    }


    /**
     * @param tag      缓存标记,<b>一般为类名</b>
     * @param url      请求地址
     * @param params   Post参数
     * @param listener 回调监听
     * @param <T>
     */
    public <T> void post(String tag, String url, Map<String, Object> params, RespListener listener) {

        // cancelLastRequest(tag);

        paramstag = null;
        if (null != params.get(ConstantValue.FUNCTION_TYPE_TAG)) {
            paramstag = params.get(ConstantValue.FUNCTION_TYPE_TAG).toString();
            params.remove(ConstantValue.FUNCTION_TYPE_TAG);
        }

        /** 获取缓存密匙 */
        //final String cacheKey = ConstantValue.SERVER_INTERFACE_PART_END_USER_NAME + "_" + tag;
        //final String cacheKey = tag;

        dirFilePath = tag;
        if (useDiskCache) {
            /** 获取缓存响应 */
            InputStream in = DiskLruCacheUtils.get(context,
                    ConstantValue.SERVER_INTERFACE_PART_END_USER_NAME, dirFilePath);
            if (in != null) {
                /** 获取到了缓存数据 */
                LogUtils.d("获取缓存");
                final String cacheResult = BaseUtils.inputStreamToString(in);
                // TODO 可能需要解密数据
                if (null != paramstag) {
                    listener.onResponse(cacheResult, paramstag);
                } else {
                    listener.onResponse(cacheResult);
                }
            } else {
                listener.onResponse("NO_CACHE", paramstag);
            }
        }
        //重新加载最新数据
//        post_cache(tag, url, params, listener, true, dirFilePath, paramstag);

    }


    /**
     * @param tag      缓存标记,<b>一般为类名</b>
     * @param url      请求地址
     * @param params   Post参数
     * @param listener 回调监听
     * @param <T>
     */
    public <T> void post_more_cache(String tag, String url, Map<String, Object> params, RespListener listener, boolean isUpRequest) {

        cancelLastRequest(tag);

        paramstag = null;
        if (null != params.get(ConstantValue.FUNCTION_TYPE_TAG)) {
            paramstag = params.get(ConstantValue.FUNCTION_TYPE_TAG).toString();
            params.remove(ConstantValue.FUNCTION_TYPE_TAG);
        }

        /** 获取缓存密匙 */
        final String cacheKey = ConstantValue.SERVER_INTERFACE_PART_END_USER_NAME + "_" + tag;
        //final String cacheKey = tag;

//        dirFilePath = tag;
        if (useDiskCache) {
            /** 获取缓存响应 */
            InputStream in = DiskLruCacheUtils.get(context,
                    ConstantValue.SERVER_INTERFACE_PART_END_USER_NAME, cacheKey);
            if (in != null) {
                /** 获取到了缓存数据 */
                final String cacheResult = BaseUtils.inputStreamToString(in);
                // TODO 可能需要解密数据
                if (null != paramstag) {
                    listener.onResponse(cacheResult, paramstag);
                }
            } else {
                //没有拿到缓存,但是又不需要更新的时候
                if (!isUpRequest) {
                    post_cache(tag, url, params, listener, true, cacheKey, paramstag, isUpRequest);
                }
            }
        }
        if (isUpRequest)
            //重新加载最新数据
            post_cache(tag, url, params, listener, true, cacheKey, paramstag, isUpRequest);

    }

    /**
     * @param tag      缓存的标志<b>一般为类名</b>
     * @param url      目标地址
     * @param params   Post参数
     * @param listener 回调监听
     * @param useCache 是否使用缓存
     * @param cacheKey 缓存地址
     * @param <T>
     */
    private <T> void post_cache(String tag, String url, final Map<String, Object> params, final RespListener<String> listener, final boolean useCache, final String cacheKey, final String paramstag, final boolean isUpRequest) {

        if (NetworkUtils.isDisconnected(context)) {
            listener.onErrorResponse("当前没有网络...");
            return;
        }


        // TODO 可能需要判断参数是否为空
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject jsonObject) {
                if (useCache) {
                    /** 开启一个线程去存放对应的消息缓存*/
                    new Thread(new Runnable() {
                        public void run() {
                            DiskLruCacheUtils.save(context,
                                    ConstantValue.SERVER_INTERFACE_PART_END_USER_NAME, cacheKey,
                                    new ByteArrayInputStream(jsonObject.toString().getBytes()));
                        }
                    }).start();
                }
                // TODO 暂时只是一个存入缓存的操作
                if (null != paramstag) {
//                    new saveToke().execute(jsonObject.toString());
                    listener.onResponse(jsonObject.toString(), paramstag);
                }
            }
        }

                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogUtils.d(error.getMessage());
                if (null != error.networkResponse) {
                    byte[] htmlBodyBytes = error.networkResponse.data;
                    LogUtils.d(new String(htmlBodyBytes));
                }
                //  listener.onErrorResponse(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<String, String>();

                headers.put("Accept", "application/json");

                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        request.setTag(tag);
        request.setRetryPolicy(

                getRetryPolicy()

        );//重试策略,可能会影响性能,应用场景:请求大数据
        requestQueue.add(request);
    }


    /**
     * 重试策略
     */

    private RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy = new DefaultRetryPolicy(TIMEOUT_POST,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        return retryPolicy;
    }
}
