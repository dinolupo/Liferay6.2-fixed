/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.search.facet;

import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.collector.FacetCollector;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.util.FacetValueValidator;

/**
 * @author Raymond Augé
 */
public interface Facet {

	public BooleanClause getFacetClause();

	public FacetCollector getFacetCollector();

	public FacetConfiguration getFacetConfiguration();

	public FacetValueValidator getFacetValueValidator();

	public String getFieldId();

	public String getFieldName();

	public SearchContext getSearchContext();

	public boolean isStatic();

	public void setFacetCollector(FacetCollector facetCollector);

	public void setFacetConfiguration(FacetConfiguration facetConfiguration);

	public void setFacetValueValidator(FacetValueValidator facetValueValidator);

	public void setFieldName(String fieldName);

	public void setStatic(boolean isStatic);

}