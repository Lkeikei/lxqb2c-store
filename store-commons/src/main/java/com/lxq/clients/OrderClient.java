package com.lxq.clients;

import com.lxq.param.PageParam;
import com.lxq.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("/order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("/order/admin/check")
    R list(@RequestBody PageParam param);
}
