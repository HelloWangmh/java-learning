package wang.mh.tool;

/**
 * Created by 明辉 on 2017/8/8.
 */
public enum CacheKey {

    /**
     * 基础缓存: 商品
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_COMMODITY(CacheType.STRING),
    /**
     * 基础缓存: 商品
     * 渠道: 不区分
     * 参数: code
     */
    TABLE_COMMODITY_CODE(CacheType.STRING),
    /**
     * 基础缓存: 文章
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_ARTICLE(CacheType.STRING),
    /**
     * 基础缓存: 匠人
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_ARTISAN(CacheType.STRING),
    /**
     * 基础缓存: 专题
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_SUBJECT(CacheType.STRING),
    /**
     * 基础缓存: 通知信息
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_NOTICE_MESSAGE(CacheType.STRING),
    /**
     * 基础缓存: 推荐位
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_RECOMMEND_LOCATION(CacheType.STRING),
    /**
     * 基础缓存: 内容频道
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_CONTENT_CHANNEL(CacheType.STRING),
    /**
     * 基础缓存: 组合
     * 渠道: 不区分
     * 参数: id
     */
    TABLE_GROUP(CacheType.STRING),
    /**
     * 单商品图片列表
     * 渠道: 不区分
     * 参数: 商品id
     */
    COMMODITY_IMAGE_LIST(CacheType.STRING),
    /**
     * 匠人推荐商品列表
     * 渠道: 不区分
     * 参数: 匠人id
     */
    COMMODITY_RECOMMEND_LIST(CacheType.ZSET),
    /**
     * 内容分类列表
     * 渠道: 区分
     * 参数: 无
     */
    CONTENT_TYPE_LIST(CacheType.STRING),
    /**
     * 专题分类列表
     * 渠道: 区分
     * 参数: 无
     */
    SUBJECT_TYPE_LIST(CacheType.STRING),
    /**
     * 专题列表
     * 渠道: 不区分
     * 参数: 无
     */
    SUBJECT_LIST(CacheType.ZSET),
    /**
     * 专题分类详情列表
     * 渠道: 不区分
     * 参数: 专题分类id
     */
    SUBJECT_TYPE_DETAILS_LIST(CacheType.ZSET),
    /**
     * 专题内容
     * 渠道: 不区分
     * 参数: 专题id
     */
    SUBJECT_CONTENT(CacheType.STRING),
    /**
     * 短信验证码
     * 渠道: 不区分
     * 参数:
     * 	手机号
     * 	类型
     */
    SMS_VERIFY_CODE(CacheType.STRING),
    /**
     * 登录用户信息
     * 渠道: 不区分
     * 参数: 用户id
     */
    LOGINED_USER_INFO(CacheType.STRING),
    /**
     * 用户匠人关系(关注)
     * 渠道: 不区分
     * 参数: 匠人id
     */
    USER_ARTISAN_FOLLOWING(CacheType.SET),
    /**
     * 用户商品关系(收藏)
     * 渠道: 不区分
     * 参数: 商品id
     */
    USER_COMMODITY_COLLECT(CacheType.SET),
    /**
     * 用户文章关系(收藏)
     * 渠道: 不区分
     * 参数: 商品id
     */
    USER_ARTICLE_COLLECT(CacheType.SET),
    /**
     * 商品编码
     * 渠道: 不区分
     */
    COMMODITY_BIZ_CODE(CacheType.STRING),
    /**
     * 订单编码
     * 渠道: 不区分
     */
    ORDER_BIZ_CODE(CacheType.STRING),
    /**
     * 内容最后更新时间
     * 渠道: 不区分
     * 参数:
     *   name: 内容名称
     *   scope: 0 表示全部 数字表示范围
     */
    LAST_MODIFY_TIME(CacheType.STRING),
    /**
     * 内容最后拉取时间
     * 渠道: 不区分
     * 参数: userId
     * hashKey: 内容名称
     * hashValue: 拉取时间
     */
    LAST_FETCH_TIME(CacheType.HASH),
    /**
     * 直接支付的阈值
     */
    DIRECT_PAY_THRESHOLD(CacheType.STRING),
    /**
     * 通知信息列表
     */
    NOTICE_MESSAGE_LIST(CacheType.ZSET),
    /**
     * 首页缓存
     * 缓存整个首页列表
     */
    HOME_DETAIL_LIST(CacheType.STRING),

    /**
     * 开机图片
     */
    STARTIMAGE_LIST(CacheType.STRING),

    /**
     * 用户匠人昵称
     */
    UNIQUE_NICKNAME_SET(CacheType.SET),
    ;

    /** cache类型 */
    enum CacheType {
        STRING, LIST, SET, ZSET, HASH
    }

    private CacheType cacheType;

    private CacheKey(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    /**
     * 获取缓存key
     * 格式：渠道号#数据类型#缓存名称:参数列表
     * @param channelId -1为不区分渠道
     * @return
     */
    public String getKey(long channelId, Object[] params) {
        String key = String.format("%s#%s#%s", channelId, cacheType, this);
        if (params.length == 0) {
            return key;
        } else {
            StringBuilder keyBuilder = new StringBuilder(key).append(":");
            for (Object param : params) {
                keyBuilder.append(param).append(',');
            }
            keyBuilder.setLength(keyBuilder.length() - 1);
            return keyBuilder.toString();
        }
    }

    /**
     * 获取缓存key不区分渠道
     * @return
     */
    public String getKey() {
        return getKey(-1, new Object[0]);
    }

    /**
     * 获取指定渠道的缓存key
     * @param channelId
     * @return
     */
    public String getKey(long channelId) {
        return getKey(channelId, new Object[0]);
    }

    /**
     * 根据参数获取缓存key
     * @param params
     * @return
     */
    public String getKeyByParams(Object...params) {
        return getKey(-1, params);
    }
}
