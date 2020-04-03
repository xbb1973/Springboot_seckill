package slf.xbb.service.model;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/3 6:46 下午
 * @description：商品领域模型
 * @modifiedBy：
 * @version:
 */
@Data
public class ItemModel {

    private Integer id;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String title;

    /**
     * 商品价格
     */
    @Digits(integer = 10, fraction = 2, message = "商品价格必须合法")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;

    /**
     * 商品库存
     */
    @NotNull(message = "商品库存不能不填")
    private Integer stock;

    /**
     * 商品描述
     */
    @NotBlank(message = "商品描述不能为空")
    private String description;

    /**
     * 商品销量
     */
    private Integer sales;

    /**
     * 商品图片的url
     */
    @NotBlank(message = "图片信息不能为空")
    private String imgUrl;

}
