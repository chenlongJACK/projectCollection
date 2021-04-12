package com.cl.creator;

import java.math.BigDecimal;

/**
 * @Description 多乐⼠
 * @auther chenlong
 * @date 2021/3/3115:21
 */
public class DuluxCoat implements Matter {
    public String scene() {
        return "涂料";
    }
    public String brand() {
        return "多乐⼠(Dulux)";
    }
    public String model() {
        return "第⼆代";
    }
    public BigDecimal price() {
        return new BigDecimal(719);
    }
    public String desc() {
        return "多乐⼠是阿克苏诺⻉尔旗下的著名建筑装饰油漆品牌，产品畅销于全球100个国家，每年全球有5000万户家庭使⽤多乐⼠油漆。";
    }
}
