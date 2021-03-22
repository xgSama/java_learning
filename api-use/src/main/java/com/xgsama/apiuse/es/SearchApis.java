package com.xgsama.apiuse.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.function.BiConsumer;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * SearchApis
 *
 * @author xgSama
 * @date 2021/2/23 10:08
 */
public class SearchApis extends Client {

    public static void main(String[] args) throws IOException {
        SearchApis searchApis = new SearchApis();

        searchApis.scroll();


        client.close();


    }

    public void search() {
        SearchRequest searchRequest = new SearchRequest("mx_demo");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(matchQuery("area_name", "南"));
        searchSourceBuilder.sort("order_id");
        searchRequest.source(searchSourceBuilder);

        ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {

//                    System.out.println(hit);

                    System.out.println(hit.getSourceAsString());

                }
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("failure");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        };

        client.searchAsync(searchRequest, RequestOptions.DEFAULT, listener);
    }


    public void scroll() throws IOException {
        final Scroll scroll = new Scroll(TimeValue.timeValueSeconds(5L));
        SearchRequest searchRequest = new SearchRequest("mx_demo");
        searchRequest.scroll(scroll);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQuery("area_name", "南"));
        searchSourceBuilder.size(500);
        searchSourceBuilder.sort("order_id");

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        String scrollId = searchResponse.getScrollId();

//        System.out.println(searchResponse);

        SearchHit[] searchHits = searchResponse.getHits().getHits();


        while (searchHits != null && searchHits.length > 0) {

            System.out.println("当前页数：" + scrollId);
            for (SearchHit searchHit : searchHits) {
                System.out.println(searchHit.getSourceAsString());
            }

            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
        }

        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);
        ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
        boolean succeeded = clearScrollResponse.isSucceeded();
    }


}
