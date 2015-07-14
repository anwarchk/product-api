package com.anwar.web.data.converter;

import com.anwar.domain.BaseEntity;
import com.anwar.web.data.PageableResultData;
import org.springframework.data.domain.Page;

import javax.inject.Named;

/**
 * @author Anwar
 */

@Named
public class PageableDataConverter extends AbstractDataConverter<Page<? extends BaseEntity>, PageableResultData> {

    @Override
    public PageableResultData convert(Page<? extends BaseEntity> page) {
        PageableResultData pageData = null;
        if (page != null) {
            pageData = new PageableResultData();
            pageData.setFirst(page.isFirst());
            pageData.setLast(page.isLast());
            pageData.setTotalElements(page.getTotalElements());
            pageData.setNumberOfElements(page.getNumberOfElements());
            pageData.setPageNumber(page.getNumber());
            pageData.setTotalPages(page.getTotalPages());
            pageData.setPageSize(page.getSize());
            pageData.setSort(page.getSort() == null ? null : page.getSort().toString());
        }
        return pageData;
    }
}
