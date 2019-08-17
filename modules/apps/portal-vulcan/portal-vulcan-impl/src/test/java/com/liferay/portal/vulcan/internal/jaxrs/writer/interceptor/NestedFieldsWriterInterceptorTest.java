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

package com.liferay.portal.vulcan.internal.jaxrs.writer.interceptor;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldId;
import com.liferay.portal.vulcan.internal.fields.NestedFieldsContext;
import com.liferay.portal.vulcan.internal.fields.NestedFieldsContextThreadLocal;
import com.liferay.portal.vulcan.internal.fields.servlet.NestedFieldsHttpServletRequestWrapperTest;
import com.liferay.portal.vulcan.internal.jaxrs.context.provider.PaginationContextProvider;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.IOException;

import java.lang.reflect.Type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.jaxrs.provider.ProviderFactory;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.osgi.framework.BundleContext;

/**
 * @author Ivica Cardic
 */
public class NestedFieldsWriterInterceptorTest {

	@Before
	public void setUp() throws Exception {
		_nestedFieldsWriterInterceptor = Mockito.spy(
			new NestedFieldsWriterInterceptor(
				Mockito.mock(BundleContext.class)));

		Mockito.doReturn(
			new MockProviderFactory()
		).when(
			_nestedFieldsWriterInterceptor
		).getProviderFactory(
			Mockito.any(Message.class)
		);

		_productResource_v1_0_Impl = new ProductResource_v1_0_Impl();
		_productResource_v2_0_Impl = new ProductResource_v2_0_Impl();

		Mockito.doReturn(
			Arrays.asList(
				_productResource_v1_0_Impl, _productResource_v2_0_Impl)
		).when(
			_nestedFieldsWriterInterceptor
		).getResources();

		_writerInterceptorContext = Mockito.mock(
			WriterInterceptorContext.class);
	}

	@Test
	public void testGetNestedFieldsForMultipleItems() throws Exception {
		Product product1 = _toProduct(1L, null);
		Product product2 = _toProduct(2L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			Arrays.asList(product1, product2)
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest()
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Arrays.asList("productOptions", "skus"), new MessageImpl(),
				new MultivaluedHashMap<>(), "v1.0",
				new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Sku[] skus = product1.getSkus();

		Assert.assertEquals(Arrays.toString(skus), 4, skus.length);

		ProductOption[] productOptionsDTOs = product1.getProductOptions();

		Assert.assertEquals(
			Arrays.toString(productOptionsDTOs), 3, productOptionsDTOs.length);
	}

	@Test
	public void testGetNestedFieldsForSingleItem() throws Exception {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest()
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Arrays.asList("productOptions", "skus"), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Sku[] skus = product.getSkus();

		Assert.assertEquals(Arrays.toString(skus), 4, skus.length);

		ProductOption[] productOptions = product.getProductOptions();

		Assert.assertEquals(
			Arrays.toString(productOptions), 3, productOptions.length);
	}

	@Test
	public void testGetNestedFieldsWithNestedFieldId() throws Exception {
		Product product = _toProduct(1L, "externalCode");

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest()
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("categories"), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Category[] categories = product.getCategories();

		Assert.assertEquals(Arrays.toString(categories), 3, categories.length);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("categories"), new MessageImpl(),
				_getPathParameters(), "v2.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		categories = product.getCategories();

		Assert.assertEquals(Arrays.toString(categories), 2, categories.length);
	}

	@Test
	public void testGetNestedFieldsWithNonexistendFieldName() throws Exception {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.emptyList(), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Sku[] skus = product.getSkus();

		Assert.assertNull(skus);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("nonexistent"), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		skus = product.getSkus();

		Assert.assertNull(skus);
	}

	@Test
	public void testGetNestedFieldsWithPagination() throws Exception {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest(
					"skus", "page", String.valueOf(1), "pageSize",
					String.valueOf(2))
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("skus"), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Sku[] skus = product.getSkus();

		Assert.assertEquals(Arrays.toString(skus), 2, skus.length);
	}

	@Test
	public void testGetNestedFieldsWithQueryParameter() throws IOException {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest("productOptions")
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		MultivaluedHashMap<String, String> queryParameters =
			new MultivaluedHashMap<String, String>() {
				{
					putSingle(
						"productOptions.createDate",
						"2019-02-19T08:03:11.763Z");
					putSingle("productOptions.name", "test2");
				}
			};

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("productOptions"), new MessageImpl(),
				_getPathParameters(), "v1.0", queryParameters));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		ProductOption[] productOptions = product.getProductOptions();

		Assert.assertEquals(
			Arrays.toString(productOptions), 1, productOptions.length);

		ProductOption productOption = productOptions[0];

		Assert.assertEquals("test2", productOption.getName());
	}

	@Test
	public void testInjectResourceContexts() throws Exception {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest("skus")
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Arrays.asList("productOptions", "skus"), new MessageImpl(),
				_getPathParameters(), "v1.0", new MultivaluedHashMap<>()));

		Assert.assertNull(_productResource_v1_0_Impl.themeDisplay);

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Assert.assertNotNull(_productResource_v1_0_Impl.themeDisplay);
	}

	@Test
	public void testNestedFieldsResourceVersioning() throws Exception {
		Product product = _toProduct(1L, null);

		Mockito.when(
			_writerInterceptorContext.getEntity()
		).thenReturn(
			product
		);

		Mockito.doReturn(
			new NestedFieldsHttpServletRequestWrapperTest.
				MockHttpServletRequest()
		).when(
			_nestedFieldsWriterInterceptor
		).getHttpServletRequest(
			Mockito.any(Message.class)
		);

		NestedFieldsContextThreadLocal.setNestedFieldsContext(
			new NestedFieldsContext(
				Collections.singletonList("skus"), new MessageImpl(),
				_getPathParameters(), "v2.0", new MultivaluedHashMap<>()));

		_nestedFieldsWriterInterceptor.aroundWriteTo(_writerInterceptorContext);

		Sku[] skus = product.getSkus();

		Assert.assertEquals(Arrays.toString(skus), 6, skus.length);
	}

	private static Category _toCategory(long id) {
		Category category = new Category();

		category.setId(id);

		return category;
	}

	private static Product _toProduct(long id, String externalCode) {
		Product product = new Product();

		product.setExternalCode(externalCode);
		product.setId(id);

		return product;
	}

	private static Sku _toSku(long id) {
		Sku sku = new Sku();

		sku.setId(id);

		return sku;
	}

	private MultivaluedHashMap<String, String> _getPathParameters() {
		return new MultivaluedHashMap<String, String>() {
			{
				putSingle("id", "1");
			}
		};
	}

	private NestedFieldsWriterInterceptor _nestedFieldsWriterInterceptor;
	private ProductResource_v1_0_Impl _productResource_v1_0_Impl;
	private ProductResource_v2_0_Impl _productResource_v2_0_Impl;
	private WriterInterceptorContext _writerInterceptorContext;

	@Path("/v1.0")
	private static class BaseProductResource_v1_0_Impl
		implements ProductResource_v1_0 {

		@GET
		@Path("/products/{id}/productOptions")
		@Produces("application/*")
		public List<ProductOption> getProductOptions(
			@NotNull @PathParam("id") Long id,
			@QueryParam("name") String name) {

			return Collections.emptyList();
		}

		@GET
		@Path("/products")
		@Produces("application/*")
		public List<Product> getProducts() {
			return Collections.emptyList();
		}

		@GET
		@Path("/products/{id}/skus")
		@Produces("application/*")
		public Page<Sku> getSkus(
			@NotNull @PathParam("id") Long id,
			@Context @NotNull Pagination pagination) {

			return Page.of(Collections.emptyList());
		}

	}

	@Path("/v2.0")
	private static class BaseProductResource_v2_0_Impl
		implements ProductResource_v2_0 {

		@GET
		@Path("/products/{productExternalCode}/categories")
		@Produces("application/*")
		public List<Category> getCategories(
			@NotNull @PathParam("productExternalCode") String externalCode) {

			return Collections.emptyList();
		}

		@GET
		@Path("/products/{id}/productOptions")
		@Produces("application/*")
		public List<ProductOption> getProductOptions(
			@NotNull @PathParam("id") Long id,
			@QueryParam("name") String name) {

			return Collections.emptyList();
		}

		@GET
		@Path("/products")
		@Produces("application/*")
		public List<Product> getProducts() {
			return Collections.emptyList();
		}

		@GET
		@Path("/products/{id}/skus")
		@Produces("application/*")
		public Page<Sku> getSkus(
			@NotNull @PathParam("id") Long id,
			@Context @NotNull Pagination pagination) {

			return Page.of(Collections.emptyList());
		}

	}

	private static class Category {

		public Long getId() {
			return _id;
		}

		public String getName() {
			return _name;
		}

		public void setId(Long id) {
			_id = id;
		}

		public void setName(String name) {
			_name = name;
		}

		private Long _id;
		private String _name;

	}

	@SuppressWarnings("unchecked")
	private static class MockProviderFactory extends ProviderFactory {

		@Override
		public <T> ContextProvider<T> createContextProvider(
			Type contextType, Message message) {

			if (Objects.equals(
					contextType.getTypeName(), Pagination.class.getName())) {

				return (ContextProvider<T>)new PaginationContextProvider();
			}

			return (ContextProvider<T>)new ThemeDisplayContextProvider();
		}

		@Override
		public Configuration getConfiguration(Message message) {
			return null;
		}

		@Override
		protected void setProviders(
			boolean custom, boolean busGlobal, Object... providers) {
		}

		private MockProviderFactory() {
			super(Mockito.mock(Bus.class));
		}

	}

	private static class Product {

		public Category[] getCategories() {
			return categories;
		}

		public String getExternalCode() {
			return externalCode;
		}

		public Long getId() {
			return id;
		}

		public ProductOption[] getProductOptions() {
			return productOptions;
		}

		public Sku[] getSkus() {
			return skus;
		}

		public void setCategories(Category[] categories) {
			this.categories = categories;
		}

		public void setExternalCode(String externalCode) {
			this.externalCode = externalCode;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public void setProductOptions(ProductOption[] productOptions) {
			this.productOptions = productOptions;
		}

		public void setSkus(Sku[] skus) {
			this.skus = skus;
		}

		protected Category[] categories;
		protected String externalCode;
		protected Long id;
		protected ProductOption[] productOptions;
		protected Sku[] skus;

	}

	private static class ProductOption {

		public Long getId() {
			return _id;
		}

		public String getName() {
			return _name;
		}

		public void setId(Long id) {
			_id = id;
		}

		public void setName(String name) {
			_name = name;
		}

		private Long _id;
		private String _name;

	}

	private static class ProductResource_v1_0_Impl
		extends BaseProductResource_v1_0_Impl {

		@NestedField("productOptions")
		@Override
		public List<ProductOption> getProductOptions(Long id, String name) {
			if (id != 1) {
				return Collections.emptyList();
			}

			List<ProductOption> productOptions = Arrays.asList(
				_toProductOption(1L, "test1"), _toProductOption(2L, "test2"),
				_toProductOption(3L, "test3"));

			if (name != null) {
				Stream<ProductOption> productOptionDTOStream =
					productOptions.stream();

				productOptions = productOptionDTOStream.filter(
					productOptionDTO -> Objects.equals(
						productOptionDTO.getName(), name)
				).collect(
					Collectors.toList()
				);
			}

			return productOptions;
		}

		@GET
		@Path("/products")
		@Produces("application/*")
		public List<Product> getProducts() {
			return Arrays.asList(_toProduct(1, null), _toProduct(2, null));
		}

		@NestedField("skus")
		@Override
		public Page<Sku> getSkus(Long id, Pagination pagination) {
			if (!Objects.equals(id, 1L)) {
				return Page.of(Collections.emptyList());
			}

			List<Sku> skus = Arrays.asList(
				_toSku(1L), _toSku(2L), _toSku(3L), _toSku(4L));

			skus = skus.subList(
				pagination.getStartPosition(),
				Math.min(pagination.getEndPosition(), skus.size()));

			return Page.of(skus);
		}

		@Context
		public ThemeDisplay themeDisplay;

		@NestedField("categories")
		protected List<Category> getCategories(
			@NestedFieldId("externalCode") String externalCode) {

			if (!Objects.equals(externalCode, "externalCode")) {
				return Collections.emptyList();
			}

			return Arrays.asList(
				_toCategory(1L), _toCategory(2L), _toCategory(3L));
		}

		private ProductOption _toProductOption(long id, String name) {
			ProductOption productOption = new ProductOption();

			productOption.setId(id);
			productOption.setName(name);

			return productOption;
		}

	}

	private static class ProductResource_v2_0_Impl
		extends BaseProductResource_v2_0_Impl {

		@NestedField("categories")
		public List<Category> getCategories(
			@NestedFieldId("externalCode") String externalCode) {

			if (!Objects.equals(externalCode, "externalCode")) {
				return Collections.emptyList();
			}

			return Arrays.asList(_toCategory(1L), _toCategory(2L));
		}

		@NestedField("productOptions")
		@Override
		public List<ProductOption> getProductOptions(Long id, String name) {
			if (id != 1) {
				return Collections.emptyList();
			}

			List<ProductOption> productOptions = Arrays.asList(
				_toProductOption(1L, "test1"), _toProductOption(2L, "test2"),
				_toProductOption(3L, "test3"));

			if (name != null) {
				Stream<ProductOption> productOptionDTOStream =
					productOptions.stream();

				productOptions = productOptionDTOStream.filter(
					productOptionDTO -> Objects.equals(
						productOptionDTO.getName(), name)
				).collect(
					Collectors.toList()
				);
			}

			return productOptions;
		}

		@GET
		@Path("/products")
		@Produces("application/*")
		public List<Product> getProducts() {
			return Arrays.asList(_toProduct(1, null), _toProduct(2, null));
		}

		@NestedField(parentReturnType = Product.class, value = "skus")
		@Override
		public Page<Sku> getSkus(Long id, Pagination pagination) {
			if (!Objects.equals(id, 1L)) {
				return Page.of(Collections.emptyList());
			}

			List<Sku> skus = Arrays.asList(
				_toSku(1L), _toSku(2L), _toSku(3L), _toSku(4L), _toSku(5L),
				_toSku(6L));

			skus = skus.subList(
				pagination.getStartPosition(),
				Math.min(pagination.getEndPosition(), skus.size()));

			return Page.of(skus);
		}

		@Context
		public ThemeDisplay themeDisplay;

		private ProductOption _toProductOption(long id, String name) {
			ProductOption productOption = new ProductOption();

			productOption.setId(id);
			productOption.setName(name);

			return productOption;
		}

	}

	private static class Sku {

		public Long getId() {
			return _id;
		}

		public void setId(Long id) {
			_id = id;
		}

		private Long _id;

	}

	private static class ThemeDisplayContextProvider
		implements ContextProvider<ThemeDisplay> {

		@Override
		public ThemeDisplay createContext(Message message) {
			return new ThemeDisplay();
		}

	}

	private interface ProductResource_v1_0 {

		public List<ProductOption> getProductOptions(Long id, String name);

		public List<Product> getProducts();

		public Page<Sku> getSkus(Long id, Pagination pagination);

	}

	private interface ProductResource_v2_0 {

		public List<Category> getCategories(String externalCode);

		public List<ProductOption> getProductOptions(Long id, String name);

		public List<Product> getProducts();

		public Page<Sku> getSkus(Long id, Pagination pagination);

	}

}