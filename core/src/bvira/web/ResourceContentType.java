package bvira.web;

import bvira.framework.RequestUri;

import java.util.HashMap;
import java.util.Map;

public class ResourceContentType {

    private final static Map<String, String> ContentTypes = new HashMap<String, String>();

    private final static String DefaultContentType = "text/html";
    private final static String DefaultEncoding = "UTF-8";

    private final String contentType;
    private final String encoding;

    public ResourceContentType() {
        this(DefaultContentType, DefaultEncoding);
    }

    private ResourceContentType(String contentType, String encoding) {
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public static ResourceContentType fromRequest(RequestUri requestUri) {
        String contentType = ContentTypes.get(requestUri.getExtension());
        if (contentType == null) {
            contentType = DefaultContentType;
        }
        return new ResourceContentType(contentType, DefaultEncoding);
    }

    public String toString() {
        return String.format("%s;charset=%s", contentType, encoding);
    }
}
