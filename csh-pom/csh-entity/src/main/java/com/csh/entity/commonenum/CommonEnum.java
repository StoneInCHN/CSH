package com.csh.entity.commonenum;

/**
 * 公共枚举数据
 * 
 * @author shijun
 *
 */
public class CommonEnum {

  /**
   * 配置元属性基本数据类型
   * 
   * @author shijun
   *
   */
  public enum MetaDataType {
    SHORT, INT, LONG, DOUBLE, DECIMAL, STRING
  }

  /**
   * 配置元之间关系
   */
  public enum MetaRelation {
    /**
     * 依赖
     */
    DEPEND,
    /**
     * 包含
     */
    INCLUDE,
    /**
     * 关联
     */
    ASSOCIATION
  }

  

  


  

 

  

 


 
  
  

 

  /**
   * 帐号状态
   */
  public enum AccountStatus {

    /** 帐号正常 */
    ACTIVED,

    /** 帐号锁定 */
    LOCKED
  }

  /**
   * 性别
   */
  public enum Gender {

    /** 男 */
    MALE,

    /** 女 */
    FEMALE
  }

  /**
   * 员工状态
   */
  public enum StaffStatus {

    /** 在职 */
    INSERVICE,

    /** 离职 */
    OUTSERVICE
  }

  

  /**
   * 通用状态
   * 
   * @author shijun
   *
   */
  public enum Status {
    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }
  /**
   * 设备状态
   * @author huyong
   *
   */
  public enum DeviceStatus{
    /**
     * 初始状态
     */
    INITED,
    /**
     * 下发
     */
    SENDOUT,
    /**
     * 已出库
     */
    STORAGEOUT,
    /**
     * 已绑定
     */
    BINDED,
    
    /**
     * 退还
     */
    REFUNDED
    
  }
 
  /**
   * 民族枚举数据
   * 
   * @author shijun
   *
   */
  public enum Nation {
    /**
     * 汉族
     */
    HANZU,
    /**
     * 藏族
     */
    ZANGZU,
    /**
     * 回族
     */
    HUIZU,
    /**
     * 白族
     */
    BAIZU,
    /**
     * 傣族
     */
    DAIZU,
    /**
     * 满族
     */
    MANZU,
    /**
     * 苗族
     */
    MIAOZU,
    /**
     * 怒族
     */
    NUZU,
    /**
     * 黎族
     */
    LIZU,
    /**
     * 京族
     */
    JINGZU,
    /**
     * 羌族
     */
    QIANGZU,
    /**
     * 水族
     */
    SHUIZU,
    /**
     * 侗族
     */
    DONGZU,
    /**
     * 土族
     */
    TUZU,
    /**
     * 佤族
     */
    WAZU,
    /**
     * 瑶族
     */
    YAOZU,
    /**
     * 畲族
     */
    SHEZU,
    /**
     * 彝族
     */
    YIZU,
    /**
     * 壮族
     */
    ZHUANGZU,
    /**
     * 阿昌族
     */
    ACHANGZU,
    /**
     * 保安族
     */
    BAOANZU,
    /**
     * 朝鲜族
     */
    CHAOXIANZU,
    /**
     * 布朗族
     */
    BULANGZU,
    /**
     * 布依族
     */
    BUYIZU,
    /**
     * 德昂族
     */
    DEANGZU,
    /**
     * 东乡族
     */
    DONGXIANGZU,
    /**
     * 独龙族
     */
    DULONGZU,
    /**
     * 高山族
     */
    GAOSHANZU,
    /**
     * 哈尼族
     */
    HANIZU,
    /**
     * 珞巴族
     */
    LUOBAZU,
    /**
     * 拉祜族
     */
    LAHUZU,
    /**
     * 僳僳族
     */
    SUSUZU,
    /**
     * 仡佬族
     */
    GELAOZU,
    /**
     * 赫哲族
     */
    HEZHEZU,
    /**
     * 景颇族
     */
    JINGPOZU,
    /**
     * 基诺族
     */
    JINUOZU,
    /**
     * 蒙古族
     */
    MENGGUZU,
    /**
     * 纳西族
     */
    NAXIZU,
    /**
     * 撒拉族
     */
    SALAZU,
    /**
     * 毛南族
     */
    MAONANZU,
    /**
     * 锡伯族
     */
    XIBOZU,
    /**
     * 土家族
     */
    TUJIAZU,
    /**
     * 仫佬族
     */
    MULAOZU,
    /**
     * 普米族
     */
    PUMIZU,
    /**
     * 门巴族
     */
    MENBAZU,
    /**
     * 裕固族
     */
    YUGUZU,
    /**
     * 达斡尔族
     */
    DAWOERZU,
    /**
     * 塔吉克族
     */
    TAJIKEZU,
    /**
     * 俄罗斯族
     */
    ELUOSIZU,
    /**
     * 鄂伦春族
     */
    ELUNCHUNZU,
    /**
     * 鄂温克族
     */
    EWENKEZU,
    /**
     * 哈萨克族
     */
    HASAKEZU,
    /**
     * 塔塔尔族
     */
    TATAERZU,
    /**
     * 维吾尔族
     */
    WEIWUERZU,
    /**
     * 柯尔克孜族
     */
    KEERKEZIZU,
    /**
     * 乌孜别克族
     */
    WUZIBIEKEZU
  }

  /**
   * 教育程度
   * 
   * @author shijun
   *
   */
  public enum EducationLevel {
    /**
     * 本科
     */
    BACHELOR,
    /**
     * 硕士
     */
    MASTER,
    /**
     * 博士
     */
    DOCTOR,
    /**
     * 高中
     */
    HIGHSCHOOL,
    /**
     * 中专
     */
    ZHONGZHUAN,
    /**
     * 初中
     */
    JUNIORHIGHSCHOOL,
    /**
     * 小学
     */
    PRIMARYSCHOOL,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 政治面貌
   * 
   * @author shijun
   *
   */
  public enum PoliticalOutlook {
    /**
     * 中共党员
     */
    ZHONGDANGDANGYUAN,
    /**
     * 共青团员
     */
    GONGQINGTUANYUAN,
    /**
     * 民主党派成员
     */
    MINZUDANGPAICHENGYUAN,
    /**
     * 群众
     */
    QUNZHONG,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 信仰
   * 
   * @author shijun
   *
   */
  public enum Religion {
    /**
     * 佛教
     */
    BUDDHISM,
    /**
     * 道教
     */
    TAOISM,
    /**
     * 基督教
     */
    CHRISTIANITY,
    /**
     * 伊斯兰教
     */
    ISLAM,
    /**
     * 天主教
     */
    CATHOLICISM,
    /**
     * 其它
     */
    OTHER
  }

  /**
   * 婚姻状况
   * 
   * @author shijun
   *
   */
  public enum MarriageState {
    /**
     * 已婚
     */
    MARRIED,
    /**
     * 未婚
     */
    UNMARRIED,
    /**
     * 丧偶
     */
    WIDOWED,
    /**
     * 其它
     */
    OTHER
  }

  

  

  /**
   * 逻辑删除标记
   * 
   * @author shijun
   *
   */
  public enum DeleteStatus {
    /**
     * 没有删除
     */
    NOT_DELETED,
    /**
     * 删除
     */
    DELETED
  }

  
  /**
   * 版本状态
   * @author huyong
   *
   */
  public enum VersionStatus{
     /**
      * 可用
      */
      ENABLE,
      /**
       * 禁用
       */
      DISABLE
  }
  /**
   * 数据字典
   * 
   * @author sujinxuan
   *
   */
  public enum ConfigKey {
    /**
     * 房间类型,0
     */
    ROOMTYPE("房间类型"),
    /**
     * 护理级别,1
     */
    NURSELEVEL("护理级别"),
    /**
     * 伙食类别,2
     */
    MEALTYPE("伙食类别"),
    /**
     * 表单类型,3
     */
    FORMTYPE("表单类型"),
    /**
     * 计量单位,4
     */
    UNITS("计量单位"),
    /**
     * 药品分类,5
     */
    DRUGSCATEGORY("药品分类"),
    /**
     * 药品用法,6
     */
    DRUGSMETHOD("药品用法"),
    /**
     * 人员类别,7
     */
    PERSONNELCATEGORY("人员类别"),
    /**
     * 评估结果,8
     */
    EVALUATINGRESULT("评估结果"),
    /**
     * 结算日期,9
     */
    BILLDAY("结算日期"),
    /**
     * 调账原因（调钱）,10
     */
    ADJUSTMENTCAUSE("调账原因"),
    /**
     * 体检配置项,11
     */
    PHYSICALEXAMITEM("体检配置项"),
    /**
     * 评估等级,12
     */
    EVALUATINGLEVEL("评估等级"),
    /**
     * 个性化服务,13
     */
    PERSONALIZED("个性化服务");
    
    private String keyName; 
    
    private ConfigKey(String keyName) { 
        this.keyName = keyName; 
    } 
        
    public String getKeyName() { 
        return this.keyName; 
    } 
  }
  /**
   * 人员编号
   */
  public enum IdentifierType {
    /**
     * 老人编号
     */
    ELDERLYINFO_IDENTIFIER
  }
  /**
   * 树节点展开状态
   * 
   * @author tanbiao
   *
   */
  public enum TreeNodeState {
    /**
     * 展开
     */
    open,
    /**
     * 关闭
     */
    closed
  }
  
  public enum SystemType{
    /**
     * 运营管理系统
     */
    OPERATION,
    /**
     * 多租户系统
     */
    ENTERPRISE
  }
  
}
