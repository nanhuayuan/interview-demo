package com.guanshi.season2.git;

/**
 * @author poi 2021/2/16 18:02
 * @version 1.0
 * 2021/2/16 18:02
 *
 * 常用词含义
 *      watch：会持续收到该项目的动态
 *      fork，复制某个项目到自己的Github仓库中
 *      star，可以理解为点赞
 *      clone，将项目下载至本地
 *      follow，关注你感兴趣的作者，会收到他们的动态
 *
 *  常用操作:
 *          in 关键词限制搜索范围
 *                  公式xxx关键词in:name或description或readme
 *                  xxx in:name项目名包含xxx的
 *                  xxx in:description项目描述包含xxx的
 *                  xxx in:readme项目的readme文件中包含xxx的
 *                  组合使用:
 *                      搜索项目名或者readme中包含秒杀的项目
 *                      seckill in:name,readme
 *
 *          stars或fork数量关键词去查找
 *                  公式:
 *                      1.xxx关键词stars通配符 :>或者:>=
 *                      2.区间范围数字:数字1..数字2
 *                  实操:
 *                  1.查找stars数大于等于5000的springboot项目
 *                      springboot stars:>=5000
 *                  2.查找forks数大于500的springcloud项目
 *                      springcloud forks:>500
 *                  3.查找fork在100到200之间并且stars数在80到100之间的springboot项目
 *                      springboot forks:100..200 stars:80..100
 *          awesome加强搜索
 *                 一般是用来收集学习、工具、书籍类相关的项目
 *                 公式: awesome 关键字
 *                 实操:
 *                      1.搜索优秀的redis相关的项目，包括框架、教程等
 *                          awesome redis
 *
 *
 *          高亮显示某一行代码
 *              公式:1. 1行-地址后面紧跟#数字
 *                  2. 多行-地址后面紧跟#数字-L数字2
 *               实操:1.单行
 *                  https://github.com/codingXiaxw/seckill/blob/master/src/main/java/cn/codingxiaxw/dao/SeckillDao.java#L13
 *               2.多行
 *                  https://github.com/codingXiaxw/seckill/blob/master/src/main/java/cn/codingxiaxw/dao/SeckillDao.java#L13-L23
 *          项目内搜索
 *              英文t
 *          搜索某个地区内的大佬
 *              公式:1.location：地区
 *                  2.language：语言
 *              实操:
 *                  1..地区北京的Java方向的用户
 *                  location:beijing language:java
 *
 * e
 *
 *
 *
 *
 */
public class git {
}
