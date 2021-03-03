package com.xgsama.apiuse.es;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;


/**
 * DocumentApis
 *
 * @author xgSama
 * @date 2021/2/22 17:08
 */
public class DocumentApis extends Client {


    public static void main(String[] args) {
        DocumentApis documentApis = new DocumentApis();
        documentApis.index();

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void index() {
        IndexRequest request = new IndexRequest("posts")
                .id("1")
                .source("field", "value");
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                System.out.println(e.status());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void get() throws IOException {
        DeleteByQueryRequest delete = new DeleteByQueryRequest("", "");
        delete.setQuery(matchQuery("", ""));

        client.deleteByQuery(delete, RequestOptions.DEFAULT);
    }


}
