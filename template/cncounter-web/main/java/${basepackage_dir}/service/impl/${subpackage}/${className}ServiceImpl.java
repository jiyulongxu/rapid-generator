/*
 * 文 件 名:  ${table.className}ServiceImpl.java
 * 创 建 人:  
 * 创建时间:  <#if now??>${now?string('yyyy-MM-dd')}</#if>
 */
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.service.impl.${subpackage};

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.service.api.${subpackage}.${className}Service;
import ${basepackage}.model.${className};
import ${basepackage}.dao.mysql.${className}Mapper;
import com.cncounter.util.string.StringNumberUtil;

/**
 * <一句话功能简述>
 */
@Service
public class ${className}ServiceImpl implements ${className}Service {
	
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;
    
    @Transactional
	public int add(${className} ${classNameLower}) {
		if(null == ${classNameLower}){
			return 0;
		}
        int rows = ${classNameLower}Mapper.insert(${classNameLower});
        return rows;
	}

    @Transactional
    public int update(${className} ${classNameLower}) {
		if(null == ${classNameLower}){
			return 0;
		}
        int rows = ${classNameLower}Mapper.update(${classNameLower});
        return rows;
    }
    
    @Transactional
    public int delete(Integer id) {
		if(null == id){
			return 0;
		}
        int rows = ${classNameLower}Mapper.deleteById(id);
        return rows;
    }
    
    
    public ${className} getById(Integer id) {
		if(null == id){
			return null;
		}
		${className} ${classNameLower} = ${classNameLower}Mapper.getById(id);
		//
        return ${classNameLower};
    }
	
	public Integer countBy(Map<String, Object> params){
		processPageParams(params);
		Integer rows = ${classNameLower}Mapper.countBy(params);
		return rows;
	}
	
	public List<${className}> listPage(Map<String, Object> params){
		processPageParams(params);
		List<${className}> lists = ${classNameLower}Mapper.listPage(params);
		
		return lists;
	}

	private static void processPageParams(Map<String, Object> params){
		// 此段代码可以迁移到工具类之中
		if(null == params){
			return;
		}
		Integer pageSize = 20;
		Integer page = 0;
		Object _pageSize = params.get("pageSize");
		Object _page = params.get("page");
        if(_pageSize instanceof Integer){
            pageSize = (Integer)_pageSize;
        } else if(_pageSize instanceof String){
            pageSize = StringNumberUtil.parseInt(_pageSize.toString(), pageSize);
        }
        if(_page instanceof Integer){
            page = (Integer)_page;
        } else if(_page instanceof String){
            page = StringNumberUtil.parseInt(_page.toString(), page);
		}
		//
		Integer start = page * pageSize;
		//
		params.put("_start", start);
		params.put("_pageSize", pageSize);
	}
}
