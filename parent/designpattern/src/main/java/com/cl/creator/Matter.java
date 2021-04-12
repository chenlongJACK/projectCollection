package com.cl.creator;

import java.math.BigDecimal;

/**
 * @Description 物料接口
 * @auther chenlong
 * @date 2021/3/3115:12
 */
public interface Matter {
    String scene(); // 场景；地板、地砖、涂料、吊顶
    String brand(); // 品牌
    String model(); // 型号
    BigDecimal price(); // 价格
    String desc(); // 描述
}
