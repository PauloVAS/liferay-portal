/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.bean;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <a href="RendererUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Aug�
 */
public class RendererUtil {

	public static Renderer getRenderer() {
		return _renderer;
	}

	public static String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			Object bean)
		throws RendererException {

		return getRenderer().renderBean(request, response, bean);
	}

	public static String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			Object bean, String varientSuffix)
		throws RendererException {

		return getRenderer().renderBean(request, response, bean, varientSuffix);
	}

	public static String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, Object bean)
		throws RendererException {

		return getRenderer().renderBean(
			request, response, servletContextName, bean);
	}

	public static String renderBean(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, Object bean, String varientSuffix)
		throws RendererException {

		return getRenderer().renderBean(
			request, response, servletContextName, bean, varientSuffix);
	}

	public static String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Object bean)
		throws RendererException {

		return getRenderer().renderBean(portletRequest, portletResponse, bean);
	}

	public static String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Object bean, String varientSuffix)
		throws RendererException {

		return getRenderer().renderBean(
			portletRequest, portletResponse, bean, varientSuffix);
	}

	public static String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, Object bean)
		throws RendererException {

		return getRenderer().renderBean(
			portletRequest, portletResponse, servletContextName, bean);
	}

	public static String renderBean(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, Object bean, String varientSuffix)
		throws RendererException {

		return getRenderer().renderBean(
			portletRequest, portletResponse, servletContextName, bean,
			varientSuffix);
	}

	public static String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String className, Object classPK)
		throws RendererException {

		return getRenderer().renderEntity(
			request, response, className, classPK);
	}

	public static String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String className, Object classPK, String varientSuffix)
		throws RendererException {

		return getRenderer().renderEntity(
			request, response, className, classPK, varientSuffix);
	}

	public static String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, String className, Object classPK)
		throws RendererException {

		return getRenderer().renderEntity(
			request, response, servletContextName, className, classPK);
	}

	public static String renderEntity(
			HttpServletRequest request, HttpServletResponse response,
			String servletContextName, String className, Object classPK,
			String varientSuffix)
		throws RendererException {

		return getRenderer().renderEntity(
			request, response, servletContextName, className, classPK,
			varientSuffix);
	}

	public static String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String className, Object classPK)
		throws RendererException {

		return getRenderer().renderEntity(
			portletRequest, portletResponse, className, classPK);
	}

	public static String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String className, Object classPK, String varientSuffix)
		throws RendererException {

		return getRenderer().renderEntity(
			portletRequest, portletResponse, className, classPK, varientSuffix);
	}

	public static String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, String className, Object classPK)
		throws RendererException {

		return getRenderer().renderEntity(
			portletRequest, portletResponse, servletContextName, className,
			classPK);
	}

	public static String renderEntity(
			PortletRequest portletRequest, PortletResponse portletResponse,
			String servletContextName, String className, Object classPK,
			String varientSuffix)
		throws RendererException {

		return getRenderer().renderEntity(
			portletRequest, portletResponse, servletContextName, className,
			classPK, varientSuffix);
	}

	public void setRenderer(Renderer renderer) {
		_renderer = renderer;
	}

	private static Renderer _renderer;

}