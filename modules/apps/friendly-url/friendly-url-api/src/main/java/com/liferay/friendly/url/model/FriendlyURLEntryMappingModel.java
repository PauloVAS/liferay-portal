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

package com.liferay.friendly.url.model;

import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.MVCCModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the FriendlyURLEntryMapping service. Represents a row in the &quot;FriendlyURLEntryMapping&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryMappingModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.friendly.url.model.impl.FriendlyURLEntryMappingImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryMapping
 * @generated
 */
@ProviderType
public interface FriendlyURLEntryMappingModel
	extends AttachedModel, BaseModel<FriendlyURLEntryMapping>, MVCCModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a friendly url entry mapping model instance should use the {@link FriendlyURLEntryMapping} interface instead.
	 */

	/**
	 * Returns the primary key of this friendly url entry mapping.
	 *
	 * @return the primary key of this friendly url entry mapping
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this friendly url entry mapping.
	 *
	 * @param primaryKey the primary key of this friendly url entry mapping
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this friendly url entry mapping.
	 *
	 * @return the mvcc version of this friendly url entry mapping
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this friendly url entry mapping.
	 *
	 * @param mvccVersion the mvcc version of this friendly url entry mapping
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the friendly url entry mapping ID of this friendly url entry mapping.
	 *
	 * @return the friendly url entry mapping ID of this friendly url entry mapping
	 */
	public long getFriendlyURLEntryMappingId();

	/**
	 * Sets the friendly url entry mapping ID of this friendly url entry mapping.
	 *
	 * @param friendlyURLEntryMappingId the friendly url entry mapping ID of this friendly url entry mapping
	 */
	public void setFriendlyURLEntryMappingId(long friendlyURLEntryMappingId);

	/**
	 * Returns the fully qualified class name of this friendly url entry mapping.
	 *
	 * @return the fully qualified class name of this friendly url entry mapping
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this friendly url entry mapping.
	 *
	 * @return the class name ID of this friendly url entry mapping
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this friendly url entry mapping.
	 *
	 * @param classNameId the class name ID of this friendly url entry mapping
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this friendly url entry mapping.
	 *
	 * @return the class pk of this friendly url entry mapping
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this friendly url entry mapping.
	 *
	 * @param classPK the class pk of this friendly url entry mapping
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the friendly url entry ID of this friendly url entry mapping.
	 *
	 * @return the friendly url entry ID of this friendly url entry mapping
	 */
	public long getFriendlyURLEntryId();

	/**
	 * Sets the friendly url entry ID of this friendly url entry mapping.
	 *
	 * @param friendlyURLEntryId the friendly url entry ID of this friendly url entry mapping
	 */
	public void setFriendlyURLEntryId(long friendlyURLEntryId);

}