/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.ratings.kernel.model;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the RatingsStats service. Represents a row in the &quot;RatingsStats&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.ratings.model.impl.RatingsStatsModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.ratings.model.impl.RatingsStatsImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStats
 * @generated
 */
@ProviderType
public interface RatingsStatsModel
	extends AttachedModel, BaseModel<RatingsStats>, ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a ratings stats model instance should use the {@link RatingsStats} interface instead.
	 */

	/**
	 * Returns the primary key of this ratings stats.
	 *
	 * @return the primary key of this ratings stats
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this ratings stats.
	 *
	 * @param primaryKey the primary key of this ratings stats
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the stats ID of this ratings stats.
	 *
	 * @return the stats ID of this ratings stats
	 */
	public long getStatsId();

	/**
	 * Sets the stats ID of this ratings stats.
	 *
	 * @param statsId the stats ID of this ratings stats
	 */
	public void setStatsId(long statsId);

	/**
	 * Returns the company ID of this ratings stats.
	 *
	 * @return the company ID of this ratings stats
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this ratings stats.
	 *
	 * @param companyId the company ID of this ratings stats
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this ratings stats.
	 *
	 * @return the fully qualified class name of this ratings stats
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this ratings stats.
	 *
	 * @return the class name ID of this ratings stats
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this ratings stats.
	 *
	 * @param classNameId the class name ID of this ratings stats
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this ratings stats.
	 *
	 * @return the class pk of this ratings stats
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this ratings stats.
	 *
	 * @param classPK the class pk of this ratings stats
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the total entries of this ratings stats.
	 *
	 * @return the total entries of this ratings stats
	 */
	public int getTotalEntries();

	/**
	 * Sets the total entries of this ratings stats.
	 *
	 * @param totalEntries the total entries of this ratings stats
	 */
	public void setTotalEntries(int totalEntries);

	/**
	 * Returns the total score of this ratings stats.
	 *
	 * @return the total score of this ratings stats
	 */
	public double getTotalScore();

	/**
	 * Sets the total score of this ratings stats.
	 *
	 * @param totalScore the total score of this ratings stats
	 */
	public void setTotalScore(double totalScore);

	/**
	 * Returns the average score of this ratings stats.
	 *
	 * @return the average score of this ratings stats
	 */
	public double getAverageScore();

	/**
	 * Sets the average score of this ratings stats.
	 *
	 * @param averageScore the average score of this ratings stats
	 */
	public void setAverageScore(double averageScore);

}