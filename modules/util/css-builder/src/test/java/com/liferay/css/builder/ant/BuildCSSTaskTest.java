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

package com.liferay.css.builder.ant;

import com.liferay.css.builder.CSSBuilderTest;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.net.URL;

import java.nio.file.Path;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;

/**
 * @author Andrea Di Giorgi
 */
public class BuildCSSTaskTest extends CSSBuilderTest {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		URL url = BuildCSSTaskTest.class.getResource("dependencies/build.xml");

		File buildXmlFile = new File(url.toURI());

		Assert.assertTrue(buildXmlFile.isFile());

		buildFileRule.configureProject(buildXmlFile.getAbsolutePath());
	}

	@Rule
	public final BuildFileRule buildFileRule = new BuildFileRule();

	@Override
	protected void executeCSSBuilder(
			String dirName, Path docrootDirPath, boolean generateSourceMap,
			String outputDirName, Path portalCommonPath, int precision,
			String[] rtlExcludedPathRegexps, String sassCompilerClassName)
		throws Exception {

		Project project = buildFileRule.getProject();

		project.setProperty("build.css.dir.names", dirName);
		project.setProperty(
			"build.docroot.dir",
			String.valueOf(docrootDirPath.toAbsolutePath()));
		project.setProperty(
			"build.css.generate.source.maps",
			String.valueOf(generateSourceMap));
		project.setProperty(
			"build.css.output.dir.name", String.valueOf(outputDirName));
		project.setProperty(
			"build.css.portal.common.path",
			String.valueOf(portalCommonPath.toAbsolutePath()));
		project.setProperty("build.css.precision", String.valueOf(precision));
		project.setProperty(
			"build.css.rtl.excluded.path.regexps",
			StringUtil.merge(rtlExcludedPathRegexps));
		project.setProperty(
			"build.css.sass.compiler.class.name", sassCompilerClassName);

		project.executeTarget("build-css");
	}

}