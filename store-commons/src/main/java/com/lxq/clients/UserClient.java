package com.lxq.clients;

import com.lxq.param.CartListParam;
import com.lxq.param.PageParam;
import com.lxq.pojo.User;
import com.lxq.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/user/admin/list")
    R adminListPage(@RequestBody PageParam param);

    @PostMapping("user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("user/admin/update")
    R update(@RequestBody User user);

    @PostMapping("user/admin/save")
    R save(@RequestBody User user);


}
