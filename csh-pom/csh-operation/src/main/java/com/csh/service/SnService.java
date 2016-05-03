
package com.csh.service;

import com.csh.entity.Sn.Type;

/**
 * Service - 序列号
 * 
 * @author 
 * @version 
 */
public interface SnService {

    /**
     * 生成序列号
     * 
     * @param type
     *            类型
     * @return 序列号
     */
    String generate(Type type);

}