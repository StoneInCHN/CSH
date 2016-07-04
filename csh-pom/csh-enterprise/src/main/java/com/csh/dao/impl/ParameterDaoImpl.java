package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.ParameterDao;
import com.csh.entity.estore.Parameter;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * 商品参数
 * @author huyong
 *
 */
@Repository("parameterDaoImpl")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter, Long> implements ParameterDao {

}
