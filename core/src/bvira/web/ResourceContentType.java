package bvira.web;

import bvira.framework.RequestUri;

import java.util.HashMap;
import java.util.Map;

public class ResourceContentType {

    private static final Map<String, String> ContentTypes = new HashMap<String, String>() {{
        put("txt", "text/text");
    }};

    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private static final String DEFAULT_ENCODING = "UTF-8";

    private final String contentType;
    private final String encoding;

    public ResourceContentType() {
        this(DEFAULT_CONTENT_TYPE, DEFAULT_ENCODING);
    }

    private ResourceContentType(String contentType, String encoding) {
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public static ResourceContentType fromRequest(RequestUri requestUri) {
        String contentType = ContentTypes.get(requestUri.getExtension());
        if (contentType == null) {
            contentType = DEFAULT_CONTENT_TYPE;
        }
        return new ResourceContentType(contentType, DEFAULT_ENCODING);
    }

    public String toString() {
        return String.format("%s;charset=%s", contentType, encoding);
    }
}
