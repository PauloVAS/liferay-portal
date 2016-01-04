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

package com.liferay.portal.security.sso.opensso.internal.verify.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsException;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.security.sso.opensso.constants.LegacyOpenSSOPropsKeys;
import com.liferay.portal.security.sso.opensso.constants.OpenSSOConstants;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.VerifyException;
import com.liferay.portal.verify.VerifyProcess;
import com.liferay.portal.verify.test.BaseVerifyProcessTestCase;

import java.io.IOException;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * @author Brian Greenwald
 */
@RunWith(Arquillian.class)
public class OpenSSOCompanySettingsVerifyProcessTest
	extends BaseVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws PortalException {
		Bundle bundle = FrameworkUtil.getBundle(
			OpenSSOCompanySettingsVerifyProcessTest.class);

		_bundleContext = bundle.getBundleContext();

		ServiceReference<CompanyLocalService> companyLocalServiceReference =
			_bundleContext.getServiceReference(CompanyLocalService.class);

		_companyLocalService = _bundleContext.getService(
			companyLocalServiceReference);

		ServiceReference<PrefsProps> prefsPropsServiceReference =
			_bundleContext.getServiceReference(PrefsProps.class);

		_prefsProps = _bundleContext.getService(prefsPropsServiceReference);

		ServiceReference<SettingsFactory> configurationAdminServiceReference =
			_bundleContext.getServiceReference(SettingsFactory.class);

		_settingsFactory = _bundleContext.getService(
			configurationAdminServiceReference);

		UnicodeProperties properties = new UnicodeProperties();

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_EMAIL_ADDRESS_ATTR,
			"testEmailAddressAttr");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_AUTH_ENABLED, StringPool.TRUE);

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_FIRST_NAME_ATTR,
			"testFirstNameAttr");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_IMPORT_FROM_LDAP, StringPool.TRUE);

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_LAST_NAME_ATTR, "testLastNameAttr");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_LOGIN_URL,
			"http://test.com/login/url");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_LOGOUT_ON_SESSION_EXPIRATION,
			StringPool.TRUE);

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_LOGOUT_URL,
			"http://test.com/logout/url");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_SCREEN_NAME_ATTR,
			"testScreenNameAttr");

		properties.put(
			LegacyOpenSSOPropsKeys.OPENSSO_SERVICE_URL,
			"http://test.com/service/url");

		List<Company> companies = _companyLocalService.getCompanies(false);

		for (Company company : companies) {
			_companyLocalService.updatePreferences(
				company.getCompanyId(), properties);
		}
	}

	@AfterClass
	public static void tearDownClass()
		throws InvalidSyntaxException, IOException {

		List<Company> companies = _companyLocalService.getCompanies(false);

		for (Company company : companies) {
			Settings settings = getSettings(company.getCompanyId());

			ModifiableSettings modifiableSettings =
				settings.getModifiableSettings();

			modifiableSettings.reset();
		}

		_bundleContext = null;
	}

	protected static Settings getSettings(long companyId) {
		try {
			Settings settings = _settingsFactory.getSettings(
				new CompanyServiceSettingsLocator(
					companyId, OpenSSOConstants.SERVICE_NAME));

			return settings;
		}
		catch (SettingsException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void doVerify() throws VerifyException {
		super.doVerify();

		List<Company> companies = _companyLocalService.getCompanies(false);

		for (Company company : companies) {
			PortletPreferences portletPreferences = _prefsProps.getPreferences(
				company.getCompanyId(), true);

			for (String key : LegacyOpenSSOPropsKeys.TOTAL_OPENSSO_KEYS) {
				Assert.assertTrue(
					Validator.isNull(
						portletPreferences.getValue(key, StringPool.BLANK)));
			}

			Settings settings = getSettings(company.getCompanyId());

			Assert.assertEquals(
				"testEmailAddressAttr",
				settings.getValue(
					OpenSSOConstants.EMAIL_ADDRESS_ATTR, StringPool.BLANK));

			Assert.assertTrue(
				GetterUtil.getBoolean(
					settings.getValue(
						OpenSSOConstants.AUTH_ENABLED, StringPool.FALSE)));

			Assert.assertEquals(
				"testFirstNameAttr",
				settings.getValue(
					OpenSSOConstants.FIRST_NAME_ATTR, StringPool.BLANK));

			Assert.assertTrue(
				GetterUtil.getBoolean(
					settings.getValue(
						OpenSSOConstants.IMPORT_FROM_LDAP, StringPool.FALSE)));

			Assert.assertEquals(
				"testLastNameAttr",
				settings.getValue(
					OpenSSOConstants.LAST_NAME_ATTR, StringPool.BLANK));

			Assert.assertEquals(
				"http://test.com/login/url",
				settings.getValue(
					OpenSSOConstants.LOGIN_URL, StringPool.BLANK));

			Assert.assertTrue(
				GetterUtil.getBoolean(
					settings.getValue(
						OpenSSOConstants.LOGOUT_ON_SESSION_EXPIRATION,
						StringPool.FALSE)));

			Assert.assertEquals(
				"http://test.com/logout/url",
				settings.getValue(
					OpenSSOConstants.LOGOUT_URL, StringPool.BLANK));

			Assert.assertEquals(
				"testScreenNameAttr",
				settings.getValue(
					OpenSSOConstants.SCREEN_NAME_ATTR, StringPool.BLANK));

			Assert.assertEquals(
				"http://test.com/service/url",
				settings.getValue(
					OpenSSOConstants.SERVICE_URL, StringPool.BLANK));
		}
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		try {
			ServiceReference<?>[] serviceReferences =
				_bundleContext.getAllServiceReferences(
					VerifyProcess.class.getName(),
					"(&(objectClass=" + VerifyProcess.class.getName() +
						")(verify.process.name=" +
							"com.liferay.portal.security.sso.opensso))");

			if (ArrayUtil.isEmpty(serviceReferences)) {
				throw new IllegalStateException("Unable to get verify process");
			}

			return (VerifyProcess)_bundleContext.getService(
				serviceReferences[0]);
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalStateException("Unable to get verify process");
		}
	}

	private static BundleContext _bundleContext;
	private static volatile CompanyLocalService _companyLocalService;
	private static volatile PrefsProps _prefsProps;
	private static volatile SettingsFactory _settingsFactory;

}