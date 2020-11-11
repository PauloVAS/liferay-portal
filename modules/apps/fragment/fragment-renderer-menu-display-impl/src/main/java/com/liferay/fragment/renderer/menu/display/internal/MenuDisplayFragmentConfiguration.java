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

package com.liferay.fragment.renderer.menu.display.internal;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Víctor Galán
 */
public class MenuDisplayFragmentConfiguration {

	public MenuDisplayFragmentConfiguration(
		DisplayStyle displayStyle, String hoveredItemColor,
		String selectedItemColor, Source source, int sublevels) {

		_displayStyle = displayStyle;
		_hoveredItemColor = hoveredItemColor;
		_selectedItemColor = selectedItemColor;
		_source = source;
		_sublevels = sublevels;
	}

	public DisplayStyle getDisplayStyle() {
		return _displayStyle;
	}

	public Optional<String> getHoveredItemColorOptional() {
		return Optional.ofNullable(_hoveredItemColor);
	}

	public Optional<String> getSelectedItemColor() {
		return Optional.ofNullable(_selectedItemColor);
	}

	public Source getSource() {
		return _source;
	}

	public int sublevels() {
		return _sublevels;
	}

	public static class SiteNavigationMenuSource implements Source {

		public SiteNavigationMenuSource(
			long parentSiteNavigationMenuItemId, long siteNavigationMenuId) {

			_parentSiteNavigationMenuItemId = parentSiteNavigationMenuItemId;
			_siteNavigationMenuId = siteNavigationMenuId;
		}

		public long getParentSiteNavigationMenuItemId() {
			return _parentSiteNavigationMenuItemId;
		}

		public long getSiteNavigationMenuId() {
			return _siteNavigationMenuId;
		}

		private final long _parentSiteNavigationMenuItemId;
		private final long _siteNavigationMenuId;

	}

	public enum ContextualMenu implements Source {

		CHILDREN("children"),
		PARENT_AND_ITS_SIBLINGS("parent-and-its-siblings"),
		SELF_AND_SIBLINGS("self-and-siblings");

		public static ContextualMenu parse(String stringValue) {
			for (ContextualMenu contextualMenu : values()) {
				if (Objects.equals(contextualMenu.getValue(), stringValue)) {
					return contextualMenu;
				}
			}

			return SELF_AND_SIBLINGS;
		}

		public String getValue() {
			return _value;
		}

		private ContextualMenu(String value) {
			_value = value;
		}

		private final String _value;

	}

	public enum DisplayStyle {

		HORIZONTAL("horizontal"), STACKED("stacked");

		public static DisplayStyle parse(String stringValue) {
			for (DisplayStyle displayStyle : values()) {
				if (Objects.equals(displayStyle.getValue(), stringValue)) {
					return displayStyle;
				}
			}

			return HORIZONTAL;
		}

		public String getValue() {
			return _value;
		}

		private DisplayStyle(String value) {
			_value = value;
		}

		private final String _value;

	}

	public interface Source {
	}

	private final DisplayStyle _displayStyle;
	private final String _hoveredItemColor;
	private final String _selectedItemColor;
	private final Source _source;
	private final int _sublevels;

}