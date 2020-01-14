package com.furkanzayimoglu.appcentdemo.api;

public class ApiEndPoint {

    private static final String method = "flickr.photos.getRecent";
    private static final String API_KEY = "96050acd22c5eb84d43420e5cbc92f60";
    private static final String EXTRA_SMALL_URL = "url_s";
    private static int PAGE = 1;
    private static int PERPAGE = 20;
    private static String FORMAT = "json";
    private static String NO_JSON_CALLBACK = "1";


    public static String getMethod() {
        return method;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getExtraSmallUrl() {
        return EXTRA_SMALL_URL;
    }

    public static int getPAGE() {
        return PAGE;
    }

    public static void setPAGE(int PAGE) {
        ApiEndPoint.PAGE = PAGE;
    }

    public static int getPERPAGE() {
        return PERPAGE;
    }

    public static void setPERPAGE(int PERPAGE) {
        ApiEndPoint.PERPAGE = PERPAGE;
    }

    public static String getFORMAT() {
        return FORMAT;
    }

    public static void setFORMAT(String FORMAT) {
        ApiEndPoint.FORMAT = FORMAT;
    }

    public static String getNoJsonCallback() {
        return NO_JSON_CALLBACK;
    }

    public static void setNoJsonCallback(String noJsonCallback) {
        NO_JSON_CALLBACK = noJsonCallback;
    }
}
