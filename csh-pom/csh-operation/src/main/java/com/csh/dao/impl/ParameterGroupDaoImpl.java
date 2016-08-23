package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.ParameterGroupDao;
import com.csh.entity.estore.ParameterGroup;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * 商品参数
 * @author huyong
 *
 */
@Repository("parameterGroupDaoImpl")
public class ParameterGroupDaoImpl extends BaseDaoImpl<ParameterGroup, Long> implements ParameterGroupDao {

}
