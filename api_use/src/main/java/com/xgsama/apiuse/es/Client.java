package com.xgsama.apiuse.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Client
 *
 * @author xgSama
 * @date 2021/2/22 17:18
 */
public abstract class Client {

    protected static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("xgsama", 9200, "http")));
}
