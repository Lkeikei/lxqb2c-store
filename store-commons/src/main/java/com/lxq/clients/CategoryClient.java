package com.lxq.clients;

import com.lxq.param.PageParam;
import com.lxq.param.ProductHotParam;
import com.lxq.pojo.Category;
import com.lxq.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * projectName: b2c-store
 * <p>
 * description: 类别的调用接口
 */
@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);


    @PostMapping("/category/hots")
    R hotsCategory(@RequestBody ProductHotParam param);

    @GetMapping("/category/list")
    R list();

    @PostMapping("/category/admin/list")
    R adminPageList(@RequestBody PageParam param);

    @PostMapping("/category/admin/save")
    R adminSave(@RequestBody Category category);

    @PostMapping("/category/admin/remove")
    R adminRemove(@RequestBody Integer categoryId);

    @PostMapping("/category/admin/update")
    R adminUpdate(@RequestBody Category category);
}
