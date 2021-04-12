package com.cl.creator;

/**
 * @Description 装修包接口
 * @auther chenlong
 * @date 2021/3/3115:11
 */
public interface IMenu {
    IMenu appendCeiling(Matter matter); // 吊顶
    IMenu appendCoat(Matter matter); // 涂料
    IMenu appendFloor(Matter matter); // 地板
    IMenu appendTile(Matter matter); // 地砖
    String getDetail(); // 明细
}
