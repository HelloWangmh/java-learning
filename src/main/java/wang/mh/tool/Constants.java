package wang.mh.tool;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 应用常量
 * @author cuichuping
 *
 */
public class Constants {

	/**
	 * 商品详情类型
	 * @author admin
	 *
	 */
	interface CommodityDetailType {
		int IMAGE = 1;   // 图片
		int HTML = 2;    // 富文本
	}
	
	/**
	 * 专题状态
	 * @author admin
	 *
	 */
	public static final Map<Integer,String> subjectMap = Maps.newLinkedHashMap();
	static {
		subjectMap.put(1,"上架");
	}
	enum SubjectStatus {
		// 上架
		ONLINE("上架", 1),
		// 下架
		OFFLINE("下架", 2),
		// 发布
		PUBLISH("发布", 3),
		// 预发布
		TIME_PUBLISH("预发布", 4),
		// 隐藏
		HIDDEN("隐藏", 5),
		// 删除
		DELETED("删除", -1);
		
		private String name;
		private int value;
		
		private SubjectStatus(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}
		
		public int getValue() {
			return value;
		}
		
	}
	
	/**
	 * 专题列表
	 * @author cuichuping
	 *
	 */
	interface SubjectListType {
		int NORMAL = 0;
		int OFFLINE = 1;
		int DELETED = 2;
	}
	
	/**
	 * 资源类型
	 * @author cuichuping
	 *
	 */
	interface ResourceType {
		int BANNER    = 1; // 推荐位
		int ARTISAN   = 2; // 匠人
		int COMMODITY = 3; // 商品
		int SUBJECT   = 4; // 专题
		int ARTICLE   = 5; // 文章
		int CHANNEL   = 6; // 频道
		int GROUP     = 7; // 组合
	}
	
	/**
	 * 通用状态信息
	 * @author cuichuping
	 *
	 */
	interface NormalStatus {
		int DISABLE = -2;   // 禁用
		int DELETED = -1;   // 删除
		int NORMAL = 0;     // 正常
		int ENABLED = 1;    // 启用
	}
	
	/**
	 * 通用上下线状态信息
	 * @author cuichuping
	 *
	 */
	interface OnlineStatus {
		int OFFLINE = 0;    // 下架
		int ONLINE = 1;     // 上架
	}
	
	/**
	 * 商品状态信息
	 * @author cuichuping
	 *
	 */
	interface CommodityStatus {
		int DELETED = -1;       // 删除
		int NORMAL = 0;         // 正常
		int SUBMIT_AUDIT = 1;   // 提交审核
		int APPROVED = 2;       // 审核通过
		int REHECT = 3;         // 审核驳回
	}
	
	/**
	 * 验证码类型
	 * @author cuichuping
	 *
	 */
	interface SmsVerifyCodeType {
		int REGISTER = 1;        // 注册
		int PASSWORD_RESET = 2;  // 重置密码
		int ORDERINFO = 3; 
	}
	
	/**
	 * 推送设备类型
	 * @author cuichuping
	 *
	 */
	interface DevicePushType {
		int IOS = 1;			// ios
		int ANDROID_OTHER = 2;	// 安卓
		int ANDROID_HUAWEI = 3;	// 华为
		int ANDROID_XIAOMI = 4;	// 小米
	}
	
	/**
	 * 订单状态
	 * @author cuichuping
	 *
	 */
	interface OrderStatus {
		int TO_BE_CONFIRMED = 0;    // 待确认
		int PENDING_PAYMENT = 1;    // 待付款
		int PENDING_SHIPMENT = 2;   // 待发货
		int PENDING_RECEIVED = 3;   // 待收货
		int COMPLETED = 4;          // 已完成
		int APPLY_REFUND = 5;       // 申请退款
		int REFUNDED = 6;           // 已退款
		int CANCELED = 7;           // 已取消
	}
	
	/**
	 * 支付方式
	 * @author cuichuping
	 *
	 */
	interface PayType {
		int WEIXIN = 1; // 微信支付
		int ALIPAY = 2; // 支付宝
	}
	
	/**
	 * 通用编码
	 */
	String APP_CHARSET = "UTF-8";
	
	/**
	 * 推送类型
	 * @author cuichuping
	 *
	 */
	interface PushType {
		int NOTICE_MESSAGE = 1;           // 通知信息
		int ORDER_MESSAGE = 2;            // 订单信息
		int ARTISAN_ARTICLE_MESSAGE = 3;  // 文章信息
	}
	
	/**
	 * 
	 * @author cuichuping
	 *
	 */
	interface RongCloud {
		
		/**
		 * 用户角色
		 * @author cuichuping
		 *
		 */
		interface UserRole {
			int NORMAL = 1;
			int ARTISAN = 2;
			int SERVICE = 3;
		}
		
		/**
		 * 消息类型
		 * @author cuichuping
		 *
		 */
		enum MessageType {
			TxtMsg("文本消息", 1),
			ImgMsg("图片消息", 2),
			VcMsg("语音消息", 3),
			ImgTextMsg("图文消息", 4),
			LBSMsg("位置消息", 5),
			ContactNtf("添加联系人消息", 6),
			InfoNtf("提示条通知消息", 7),
			ProfileNtf("资料通知消息", 8),
			CmdNtf("通用命令通知消息", 9),
			HsMsg("客服握手消息", 10),
			SpMsg("客服挂断消息", 11);
			
			private int value;
			
			private String name;
			
			private MessageType(String name, int value) {
				this.name = name;
				this.value = value;
			}
			
			public String getName() {
				return name;
			}
			
			public int getValue() {
				return value;
			}
			
		}
		
	}
	
}
