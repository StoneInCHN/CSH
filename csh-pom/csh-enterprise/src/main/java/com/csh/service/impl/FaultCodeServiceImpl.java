package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.FaultCodeDao;
import com.csh.entity.FaultCode;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.FaultCodeService;

@Service("faultCodeServiceImpl")
public class FaultCodeServiceImpl extends BaseServiceImpl<FaultCode,Long> implements FaultCodeService {

    @Resource(name="faultCodeDaoImpl")
    public void setBaseDao(FaultCodeDao faultCodeDao) {
    	super.setBaseDao(faultCodeDao);
    }

	@Override
	public FaultCode findByCode(String faultCode) {
		List<Filter> filters = new ArrayList<Filter>();
		
		Filter faultCodeFilter = new Filter("code", Operator.eq, faultCode);
		filters.add(faultCodeFilter);
		
		List<FaultCode> faultCodes = this.findList(null, filters, null);
		
		if (faultCodes != null && faultCodes.size() == 1) {
			
			return faultCodes.get(0);
		}
		return null;
	}
}