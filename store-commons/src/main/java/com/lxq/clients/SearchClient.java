package com.lxq.clients;

import com.lxq.param.ProductSearchParam;
import com.lxq.pojo.Product;
import com.lxq.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam param);

    @PostMapping("/search/save")
    R saveOrUpdate(@RequestBody Product product);

    @PostMapping("/search/remove")
    R remove(@RequestBody Integer productId);
}
