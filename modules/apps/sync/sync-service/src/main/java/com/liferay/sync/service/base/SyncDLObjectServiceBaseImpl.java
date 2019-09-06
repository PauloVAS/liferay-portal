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

package com.liferay.sync.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectService;
import com.liferay.sync.service.persistence.SyncDLFileVersionDiffPersistence;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.sync.service.persistence.SyncDLObjectPersistence;
import com.liferay.sync.service.persistence.SyncDevicePersistence;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the sync dl object remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.sync.service.impl.SyncDLObjectServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.impl.SyncDLObjectServiceImpl
 * @generated
 */
public abstract class SyncDLObjectServiceBaseImpl
	extends BaseServiceImpl
	implements SyncDLObjectService, AopService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SyncDLObjectService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.sync.service.SyncDLObjectServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			SyncDLObjectService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		syncDLObjectService = (SyncDLObjectService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncDLObjectService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SyncDLObject.class;
	}

	protected String getModelClassName() {
		return SyncDLObject.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = syncDLObjectPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected SyncDevicePersistence syncDevicePersistence;

	@Reference
	protected SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence;

	@Reference
	protected com.liferay.sync.service.SyncDLObjectLocalService
		syncDLObjectLocalService;

	protected SyncDLObjectService syncDLObjectService;

	@Reference
	protected SyncDLObjectPersistence syncDLObjectPersistence;

	@Reference
	protected SyncDLObjectFinder syncDLObjectFinder;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.GroupService groupService;

	@Reference
	protected com.liferay.portal.kernel.service.OrganizationLocalService
		organizationLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.OrganizationService
		organizationService;

	@Reference
	protected com.liferay.portal.kernel.service.RepositoryLocalService
		repositoryLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.RepositoryService
		repositoryService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourcePermissionLocalService
		resourcePermissionLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourcePermissionService
		resourcePermissionService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLAppService
		dlAppService;

	@Reference
	protected
		com.liferay.document.library.kernel.service.DLFileEntryLocalService
			dlFileEntryLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLFileEntryService
		dlFileEntryService;

	@Reference
	protected
		com.liferay.document.library.kernel.service.DLFileVersionLocalService
			dlFileVersionLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLFileVersionService
		dlFileVersionService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLFolderLocalService
		dlFolderLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLFolderService
		dlFolderService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLTrashLocalService
		dlTrashLocalService;

	@Reference
	protected com.liferay.document.library.kernel.service.DLTrashService
		dlTrashService;

}