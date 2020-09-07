package com.dandandog.framework.faces.model;

import com.apj.framework.domain.BaseEntity;
import com.apj.framework.web.faces.utils.FacesContextUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dandandog.framework.faces.utils.FacesContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author create by Johnny
 * @description com.johnny.web.restful
 * @date 七月 06,2019
 */
public class PageDataModel<T extends BaseEntity> extends LazyDataModel<T> {
	private static final long serialVersionUID = 2957926997919683676L;


	public Map<String, Object> filters;

	public PageDataModel setFilters(Map filters) {
		this.filters = filters;
		return this;
	}


	public BaseMapper<T> getBaseMapper() {
		String clazz = getMClass();
		return (BaseMapper<T>) FacesContextUtils.getBean(clazz);
	}

	@Override
	public T getRowData(String rowKey) {
		return getBaseMapper().selectById(rowKey);
	}

	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		IPage<T> page = getBaseMapper().selectPage(new Page<>(getPage(first, pageSize), pageSize),
				new QueryWrapper<T>().allEq(Optional.ofNullable(this.filters).orElse(filters)).orderBy(StringUtils.isNotBlank(sortField),
						SortOrder.ASCENDING.equals(sortOrder), sortField));
		this.setRowCount((int) page.getTotal());
		return page.getRecords();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		super.setRowIndex((getPageSize() == 0) ? -1 : rowIndex);
	}

	private int getPage(int first, int pageSize) {
		int pageNum = (pageSize > 0) ? (first / pageSize) : 0;
		return pageNum + 1;
	}

	private String getMClass() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		String typeName = type.getActualTypeArguments()[0].getTypeName();
		return typeName.substring(typeName.lastIndexOf(".") + 1).toLowerCase()
				+ "Mapper";
	}

}
