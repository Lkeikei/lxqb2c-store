package com.lxq.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxq.doc.ProductDoc;
import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.search.service.SearchService;
import com.lxq.utils.R;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  23:01
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 1、判断关键字是否为null null查询全部。不为null add字段查询
     * 2、添加分页睡醒
     * 3、es查询
     * 4、结果处理
     * @param param
     * @return
     */
    @Override
    public R search(ProductSearchParam param) {

        SearchRequest searchRequest = new SearchRequest("product");

        String search = param.getSearch();

        if (StringUtils.isEmpty(search)){
            //null 不添加all关键字，查询全部
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            //不为null
            //添加all 的匹配
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));

        }

        //进行分页数据添加
        searchRequest.source().from((param.getCurrentPage() - 1) * param.getPageSize());
        searchRequest.source().size(param.getPageSize());

        SearchResponse searchResponse = null;

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }

        SearchHits hits = searchResponse.getHits();
        //查询复核的数量
        long total = hits.getTotalHits().value;

        //数据集合
        SearchHit[] hitsHits = hits.getHits();


        //json处理器
        ObjectMapper objectMapper = new ObjectMapper();

        List<Product> productList = new ArrayList<>();
        for (SearchHit hitsHit : hitsHits) {
            //查询的内容数据！ productDoc模型对应的json数据
            String sourceAsString = hitsHit.getSourceAsString();

            Product product = null;
            try {
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            productList.add(product);
        }

        R ok = R.ok(null, productList, total);
        return ok;
    }

    @Override
    public R save(Product product) throws IOException {
        IndexRequest indexRequest =
                new IndexRequest("product").id(product.getProductId().toString());

        ProductDoc productDoc = new ProductDoc(product);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productDoc);

        indexRequest.source(json, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);


        return R.ok("数据同步成功！");
    }

    @Override
    public R remove(Integer productId) throws IOException {
        DeleteRequest re = new DeleteRequest("product").id(productId.toString());

        restHighLevelClient.delete(re, RequestOptions.DEFAULT);
        return R.ok("es库数据删除成功");
    }
}
