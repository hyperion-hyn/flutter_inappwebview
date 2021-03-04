package com.pichillilorenzo.flutter_inappwebview.AphaWeb3.web3;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import java.net.URI;

public class SignCallbackJSInterface
{
    private static final String JS_PROTOCOL_CANCELLED = "cancelled";
    private static final String JS_PROTOCOL_ON_SUCCESSFUL = "executeCallback(%1$s, null, \"%2$s\")";
    private static final String JS_PROTOCOL_ON_FAILURE = "executeCallback(%1$s, \"%2$s\", null)";
    private final WebView webView;
    /*@NonNull
    private final OnSignTransactionListener onSignTransactionListener;
    @NonNull
    private final OnSignMessageListener onSignMessageListener;
    @NonNull
    private final OnSignPersonalMessageListener onSignPersonalMessageListener;
    @NonNull
    private final OnSignTypedMessageListener onSignTypedMessageListener;
    @NonNull
    private final OnEthCallListener onEthCallListener;*/

    public SignCallbackJSInterface(
            WebView webView/*,
            @NonNull OnSignTransactionListener onSignTransactionListener,
            @NonNull OnSignMessageListener onSignMessageListener,
            @NonNull OnSignPersonalMessageListener onSignPersonalMessageListener,
            @NonNull OnSignTypedMessageListener onSignTypedMessageListener,
            @NotNull OnEthCallListener onEthCallListener*/) {
        this.webView = webView;
        /*this.onSignTransactionListener = onSignTransactionListener;
        this.onSignMessageListener = onSignMessageListener;
        this.onSignPersonalMessageListener = onSignPersonalMessageListener;
        this.onSignTypedMessageListener = onSignTypedMessageListener;
        this.onEthCallListener = onEthCallListener;*/
    }

    @JavascriptInterface
    public void signTransaction(
            int callbackId,
            String recipient,
            String value,
            String nonce,
            String gasLimit,
            String gasPrice,
            String payload) {
        /*if (value.equals("undefined") || value == null) value = "0";
        if (gasPrice == null) gasPrice = "0";
        Web3Transaction transaction = new Web3Transaction(
                TextUtils.isEmpty(recipient) ? Address.EMPTY : new Address(recipient),
                null,
                Hex.hexToBigInteger(value),
                Hex.hexToBigInteger(gasPrice, BigInteger.ZERO),
                Hex.hexToBigInteger(gasLimit, BigInteger.ZERO),
                Hex.hexToLong(nonce, -1),
                payload,
                callbackId);

        webView.post(() -> onSignTransactionListener.onSignTransaction(transaction, getUrl()));*/
    }

    @JavascriptInterface
    public void signMessage(int callbackId, String data) {
        Log.e("signMessage!!!",""+callbackId + "  " + data);
        callbackToJS(callbackId, JS_PROTOCOL_ON_SUCCESSFUL, "hello callback native");
//        webView.post(() -> onSignMessageListener.onSignMessage(new EthereumMessage(data, getUrl(), callbackId, SignMessageType.SIGN_MESSAGE)));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void callbackToJS(long callbackId, String function, String param) {
        String callback = String.format(function, callbackId, param);
        webView.post(() -> webView.evaluateJavascript(callback, value -> Log.d("WEB_VIEW", value)));
    }

    @JavascriptInterface
    public void signPersonalMessage(int callbackId, String data) {
        Log.e("signPersonalMessage!!!",""+callbackId);
//        webView.post(() -> onSignPersonalMessageListener.onSignPersonalMessage(new EthereumMessage(data, getUrl(), callbackId, SignMessageType.SIGN_PERSONAL_MESSAGE)));
    }

    @JavascriptInterface
    public void signTypedMessage(int callbackId, String data) {
        Log.e("signTypedMessage!!!",""+callbackId);
        /*webView.post(() -> {
            try
            {
                JSONObject obj = new JSONObject(data);
                String address = obj.getString("from");
                String messageData = obj.getString("data");
                CryptoFunctions cryptoFunctions = new CryptoFunctions();

                EthereumTypedMessage message = new EthereumTypedMessage(messageData, getDomainName(), callbackId, cryptoFunctions);
                onSignTypedMessageListener.onSignTypedMessage(message);
            }
            catch (Exception e)
            {
                EthereumTypedMessage message = new EthereumTypedMessage(null, "", getDomainName(), callbackId);
                onSignTypedMessageListener.onSignTypedMessage(message);
                e.printStackTrace();
            }
        });*/
    }

    @JavascriptInterface
    public void ethCall(int callbackId, String recipient, String payload) {
        Log.e("ethCall!!!",""+callbackId);
       /*DefaultBlockParameter defaultBlockParameter;
        if (payload.equals("undefined")) payload = "0x";
        defaultBlockParameter = DefaultBlockParameterName.LATEST;

        Web3Call call = new Web3Call(
                new Address(recipient),
                defaultBlockParameter,
                payload,
                callbackId);

        webView.post(() -> onEthCallListener.onEthCall(call));*/
    }

    private String getUrl() {
        return webView == null ? "" : webView.getUrl();
    }

    private String getDomainName() {
        return webView == null ? "" : getDomainName(webView.getUrl());
    }

    public static String getDomainName(String url)
    {
        try
        {
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        }
        catch (Exception e)
        {
            return url != null ? url : "";
        }
    }
}
