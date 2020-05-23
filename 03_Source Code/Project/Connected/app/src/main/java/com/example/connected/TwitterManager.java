package com.example.connected;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager {
    ConfigurationBuilder cb;
    private static TwitterFactory tf;
    public TwitterManager() {
        this.cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("g9wFX0z720lDFzNYjM3cFJyPC")
                .setOAuthConsumerSecret("SHqc92VQzjInFhgYYnjWo8R1M47SYsUcXqbXkGGjXOP37jcGMs")
                .setOAuthAccessToken("2870880347-JCH9mCXbumNAyY77JdoOSEhFZachjKJBw2AE5me")
                .setOAuthAccessTokenSecret("EDNekgpQIThwuf1DNQSQPfVCKEhUnzI9HBW6mLOPuiLlb");
        tf = new TwitterFactory(cb.build());
    }
    public static Twitter getinstance()
    {
        return tf.getInstance();
    }

}
