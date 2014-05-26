package com.endavafii.util;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.auth.Permission;
import com.aetrion.flickr.contacts.ContactsInterface;
import com.aetrion.flickr.people.PeopleInterface;
import com.aetrion.flickr.photosets.PhotosetsInterface;
import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;

public class UploadFile {

	protected static String alphabetString = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
	protected static char[] alphabet = alphabetString.toCharArray();
	protected static int base_count = alphabet.length;
	
	public static String encode(long num){
		
		
		
		String result = "";
		long div;
		int mod = 0;
		
		while (num >= base_count) {
			div = num/base_count;
			mod = (int)(num-(base_count*(long)div));
			result = alphabet[mod] + result;
			num = (long)div;
		}
		if (num>0){
			result = alphabet[(int)num] + result;
		}
		return result;
	}
	
	
	public static void createImageLink(String image) throws IOException, NoSuchAlgorithmException, XMLStreamException{
		
		
		
		String apiKey = "57d6c075fda2b732a57b8de52fcb4cec";
	    String token = "72157634886905570-06f4b3b3528defe0";
	    String secretKey = "6486f28771fd7c04";
	    String title="Cryov";
	    String photo = "D:/poza2.jpg";
	    String description="This is photo description";
	    String api_sig = "";
	 
	    Flickr f;
	    ContactsInterface c;
	    PeopleInterface p;
	    PhotosetsInterface o;
	    Uploader up = new Uploader(apiKey,secretKey);
	    REST rest;
	 
	    RequestContext requestContext;
	 
	    AuthInterface authInterface;
	    String frob = "";
	    //void setup()
	 
	       InputStream in = new FileInputStream(photo);
	       ByteArrayOutputStream out = new ByteArrayOutputStream();
	       int i;
	       byte[] buffer = new byte[1024];
	       while ((i = in.read(buffer)) != -1) {
	           out.write(buffer, 0, i);
	       }
	       in.close();
	    //   byte[] result = out.toByteArray();
	 
	    byte data[] = out.toByteArray();
	    //size(500, 500);
	    f= new Flickr(apiKey,secretKey,(new Flickr(apiKey)).getTransport());
	    up=f.getUploader();
	    authInterface=f.getAuthInterface();
	    requestContext = RequestContext.getRequestContext();
	    requestContext.setSharedSecret(secretKey);
	 
	    try {
	    frob = authInterface.getFrob();
	    System.out.println(frob);
	    URL joep = authInterface.buildAuthenticationUrl(Permission.WRITE, frob);
	    System.out.println(joep.toExternalForm());
	   api_sig = joep.toExternalForm().substring(joep.toExternalForm().lastIndexOf('=') + 1).trim();
	    System.out.println("Sig key is:" + api_sig);
	    
	    System.out.println("Press return after you granted access at this URL:");
	 
	    } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    } catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    } catch (FlickrException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	 
	    try {
	    Auth auth = new Auth();
	    requestContext.setAuth(auth);
	    //authInterface.addAuthToken();
	    auth.setToken(token);
	       auth.setPermission(Permission.WRITE);
	     System.out.println("Token Is: " + auth.getToken());
	    System.out.println("Permission for token: " + auth.getPermission());
	    f.setAuth(auth);
	    UploadMetaData uploadMetaData = new UploadMetaData(); uploadMetaData.setTitle("hello Satish");
	    up.upload(data,uploadMetaData);
	    String result = up.upload(data,uploadMetaData);
	    
	    System.out.println(encode(Long.parseLong(result)));
	 	   System.out.println(result);
            System.out.println(uploadMetaData.getDescription());
            
	 
	    } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    } catch (SAXException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    } catch (FlickrException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    }
	    
	    URLConnection uc = new URL("http://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=a01b61395014805f3b37c5c248d3ca69&photo_id=9412740343&format=rest&auth_token=72157634887303000-62cc0fa0909e69b3&api_sig=823c0883846541f129a8da57b6ce3467").openConnection();
		
		URLConnection uc2 = new URL("http://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=" + apiKey + "&photo_id=9412740343&format=rest&auth_token=" + token + "&api_sig=" + api_sig).openConnection();
        DataInputStream dis = new DataInputStream(uc.getInputStream());
        FileWriter fw = new FileWriter(new File("D:\\\\xmlfile.xml"));
        String nextline;
        String[] servers = new String[10];
        String[] ids = new String[10];
        String[] secrets = new String[10];
        while ((nextline = dis.readLine()) != null) {
            fw.append(nextline);
        }
        dis.close();
        fw.close();
        String filename = "D:\\\\xmlfile.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        System.out.println("FACTORY: " + factory);

        XMLEventReader r = factory.createXMLEventReader(filename, new FileInputStream(filename));
        int i2 = -1;
        while (r.hasNext()) {

            XMLEvent event = r.nextEvent();
            if (event.isStartElement()) {
                StartElement element = (StartElement) event;
                String elementName = element.getName().toString();
                if (elementName.equals("photo")) {
                    i2++;
                    Iterator iterator = element.getAttributes();

                    while (iterator.hasNext()) {

                        Attribute attribute = (Attribute) iterator.next();
                        QName name = attribute.getName();
                        String value = attribute.getValue();
                        System.out.println("Attribute name/value: " + name + "/" + value);
                        if ((name.toString()).equals("server")) {
                            servers[i2] = value;
                            System.out.println("Server Value" + servers[0]);
                        }
                        if ((name.toString()).equals("id")) {
                            ids[i2] = value;
                        }
                        if ((name.toString()).equals("secret")) {
                            secrets[i2] = value;
                        }
                    }
                }
            }
        }
        System.out.println(i);
        String flickrurl = "http://static.flickr.com/" + servers[i2] + "/" + ids[i2] + "_" + secrets[i2] + ".jpg";
        try {
            URI uri = new URI(flickrurl);
            Desktop desktop = null;
            if (Desktop.isDesktopSupported()) {
                desktop = Desktop.getDesktop();
            }

            if (desktop != null) {
                desktop.browse(uri);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
		
	
		
	}
	

}
