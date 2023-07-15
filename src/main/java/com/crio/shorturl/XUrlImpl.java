//package com.crio.xurl;
package com.crio.shorturl;

import java.util.*;
import com.crio.shorturl.*;
public class XUrlImpl implements XUrl{

  Map<String,Integer> countmapping=new HashMap<>();
    Map<String,String> mapping=new HashMap<>();
    // If longUrl already has a corresponding shortUrl, return that shortUrl
  // If longUrl is new, create a new shortUrl for the longUrl and return it
    public String registerNewUrl(String longUrl){

        if(mapping.containsValue(longUrl)){
          for(Map.Entry<String,String> entry:mapping.entrySet()){
            if(entry.getValue().equals(longUrl)){
                return entry.getKey();
            }
          }
        }
        String shorturl="http://short.url/";
        shorturl+=randomString(9);
        if(!mapping.containsKey(shorturl)){
            mapping.put(shorturl, longUrl);

        }
        return shorturl;
    
    }

    public  String randomString(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder s=new StringBuilder();
        for(int i=0; i<n; i++){
            int index=new Random().nextInt(AlphaNumericString.length());
            s.append(AlphaNumericString.charAt(index));
        }
        return s.toString();

    }

    // If shortUrl is already present, return null
  // Else, register the specified shortUrl for the given longUrl
  // Note: You don't need to validate if longUrl is already present, 
  //       assume it is always new i.e. it hasn't been seen before 
 public  String registerNewUrl(String longUrl, String shortUrl){
    if(mapping.containsKey(shortUrl)){
        return null;
    }
    else{
        mapping.put(shortUrl,longUrl);
    }
    return shortUrl;
  }

  // If shortUrl doesn't have a corresponding longUrl, return null
  // Else, return the corresponding longUrl
  public String getUrl(String shortUrl){
    
    if(!mapping.containsKey(shortUrl)){
        return null;
    }else{
      String st= mapping.get(shortUrl);
      ////countmapping
      if(!countmapping.containsKey(st)){
        countmapping.put(st,1);
      }else  if(countmapping.containsKey(st)){
        countmapping.put(st,countmapping.get(st)+1);
      }
      
      return st;
    }

  }

  // Return the number of times the longUrl has been looked up using getUrl()
  public Integer getHitCount(String longUrl){
    if(!countmapping.containsKey(longUrl))return 0;
    return countmapping.get(longUrl);

  }

  // Delete the mapping between this longUrl and its corresponding shortUrl
  // Do not zero the Hit Count for this longUrl
  public String delete(String longUrl){
    if(longUrl.equals(null))return longUrl;
    if(mapping.containsValue(longUrl)){
      mapping.entrySet()
      .removeIf(
          entry -> (longUrl.equals(entry.getValue())));
      }
      return null;
  }
}