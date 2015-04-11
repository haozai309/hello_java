package com.web.networktest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class ContentHandler extends DefaultHandler {

    private static final String TAG = "NetworkTest/ParseHandler";
    private String mNodeName;
    private StringBuilder mId;
    private StringBuilder mName;
    private StringBuilder mVersion;

    @Override
    public void startDocument() throws SAXException {
        mId = new StringBuilder();
        mName = new StringBuilder();
        mVersion = new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        mNodeName = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            Log.i(TAG, "id is " + mId.toString().trim());
            Log.i(TAG, "name is " + mName.toString().trim());
            Log.i(TAG, "version is " + mVersion.toString().trim());
            mId.setLength(0);
            mName.setLength(0);
            mVersion.setLength(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ("id".equals(mNodeName)) {
            mId.append(ch, start, length);
        } else if("name".equals(mNodeName)) {
            mName.append(ch, start, length);
        } else if("version".equals(mNodeName)) {
            mVersion.append(ch, start, length);
        }
    }

}
