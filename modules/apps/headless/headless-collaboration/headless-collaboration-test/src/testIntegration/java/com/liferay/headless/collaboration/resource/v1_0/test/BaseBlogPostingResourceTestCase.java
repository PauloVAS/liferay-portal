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

package com.liferay.headless.collaboration.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.headless.collaboration.dto.v1_0.AggregateRating;
import com.liferay.headless.collaboration.dto.v1_0.BlogPosting;
import com.liferay.headless.collaboration.dto.v1_0.Categories;
import com.liferay.headless.collaboration.dto.v1_0.Creator;
import com.liferay.headless.collaboration.dto.v1_0.Image;
import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public abstract class BaseBlogPostingResourceTestCase {

	@Before
	public void setUp() throws Exception {
		testGroup = GroupTestUtil.addGroup();

		_resourceURL = new URL(
			"http://localhost:8080/o/headless-collaboration/v1.0");
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testDeleteBlogPosting() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetBlogPosting() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetContentSpaceBlogPostingsPage() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPatchBlogPosting() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPostContentSpaceBlogPosting() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testPutBlogPosting() throws Exception {
		Assert.assertTrue(true);
	}

	protected void assertEquals(
		BlogPosting blogPosting1, BlogPosting blogPosting2) {

		Assert.assertTrue(
			blogPosting1 + " does not equal " + blogPosting2,
			equals(blogPosting1, blogPosting2));
	}

	protected void assertEquals(
		List<BlogPosting> blogPostings1, List<BlogPosting> blogPostings2) {

		Assert.assertEquals(blogPostings1.size(), blogPostings2.size());

		for (int i = 0; i < blogPostings1.size(); i++) {
			BlogPosting blogPosting1 = blogPostings1.get(i);
			BlogPosting blogPosting2 = blogPostings2.get(i);

			assertEquals(blogPosting1, blogPosting2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<BlogPosting> blogPostings1, List<BlogPosting> blogPostings2) {

		Assert.assertEquals(blogPostings1.size(), blogPostings2.size());

		for (BlogPosting blogPosting1 : blogPostings1) {
			boolean contains = false;

			for (BlogPosting blogPosting2 : blogPostings2) {
				if (equals(blogPosting1, blogPosting2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				blogPostings2 + " does not contain " + blogPosting1, contains);
		}
	}

	protected void assertResponseCode(
		int expectedResponseCode, Http.Response actualResponse) {

		Assert.assertEquals(
			expectedResponseCode, actualResponse.getResponseCode());
	}

	protected boolean equals(
		BlogPosting blogPosting1, BlogPosting blogPosting2) {

		if (blogPosting1 == blogPosting2) {
			return true;
		}

		return false;
	}

	protected boolean invokeDeleteBlogPosting(Long blogPostingId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), Boolean.class);
	}

	protected Http.Response invokeDeleteBlogPostingResponse(Long blogPostingId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setDelete(true);

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected BlogPosting invokeGetBlogPosting(Long blogPostingId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), BlogPostingImpl.class);
	}

	protected Http.Response invokeGetBlogPostingResponse(Long blogPostingId)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected Page<BlogPosting> invokeGetContentSpaceBlogPostingsPage(
			Long contentSpaceId, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/blog-postings",
					contentSpaceId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options),
			new TypeReference<Page<BlogPostingImpl>>() {
			});
	}

	protected Http.Response invokeGetContentSpaceBlogPostingsPageResponse(
			Long contentSpaceId, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/blog-postings",
					contentSpaceId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected BlogPosting invokePatchBlogPosting(
			Long blogPostingId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), BlogPostingImpl.class);
	}

	protected Http.Response invokePatchBlogPostingResponse(
			Long blogPostingId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected BlogPosting invokePostContentSpaceBlogPosting(
			Long contentSpaceId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(blogPosting),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/blog-postings",
					contentSpaceId));

		options.setPost(true);

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), BlogPostingImpl.class);
	}

	protected Http.Response invokePostContentSpaceBlogPostingResponse(
			Long contentSpaceId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(blogPosting),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath(
					"/content-spaces/{content-space-id}/blog-postings",
					contentSpaceId));

		options.setPost(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected BlogPosting invokePutBlogPosting(
			Long blogPostingId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(blogPosting),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		options.setPut(true);

		return _outputObjectMapper.readValue(
			HttpUtil.URLtoString(options), BlogPostingImpl.class);
	}

	protected Http.Response invokePutBlogPostingResponse(
			Long blogPostingId, BlogPosting blogPosting)
		throws Exception {

		Http.Options options = _createHttpOptions();

		options.setBody(
			_inputObjectMapper.writeValueAsString(blogPosting),
			ContentTypes.APPLICATION_JSON, StringPool.UTF8);

		options.setLocation(
			_resourceURL +
				_toPath("/blog-postings/{blog-posting-id}", blogPostingId));

		options.setPut(true);

		HttpUtil.URLtoString(options);

		return options.getResponse();
	}

	protected BlogPosting randomBlogPosting() {
		return new BlogPostingImpl() {
			{
				alternativeHeadline = RandomTestUtil.randomString();
				articleBody = RandomTestUtil.randomString();
				caption = RandomTestUtil.randomString();
				contentSpace = RandomTestUtil.randomLong();
				dateCreated = RandomTestUtil.nextDate();
				dateModified = RandomTestUtil.nextDate();
				datePublished = RandomTestUtil.nextDate();
				description = RandomTestUtil.randomString();
				encodingFormat = RandomTestUtil.randomString();
				friendlyUrlPath = RandomTestUtil.randomString();
				hasComments = RandomTestUtil.randomBoolean();
				headline = RandomTestUtil.randomString();
				id = RandomTestUtil.randomLong();
				imageId = RandomTestUtil.randomLong();
			}
		};
	}

	protected Group testGroup;

	protected static class BlogPostingImpl implements BlogPosting {

		public AggregateRating getAggregateRating() {
			return aggregateRating;
		}

		public String getAlternativeHeadline() {
			return alternativeHeadline;
		}

		public String getArticleBody() {
			return articleBody;
		}

		public String getCaption() {
			return caption;
		}

		public Categories[] getCategories() {
			return categories;
		}

		public Long[] getCategoryIds() {
			return categoryIds;
		}

		public Long getContentSpace() {
			return contentSpace;
		}

		public Creator getCreator() {
			return creator;
		}

		public Date getDateCreated() {
			return dateCreated;
		}

		public Date getDateModified() {
			return dateModified;
		}

		public Date getDatePublished() {
			return datePublished;
		}

		public String getDescription() {
			return description;
		}

		public String getEncodingFormat() {
			return encodingFormat;
		}

		public String getFriendlyUrlPath() {
			return friendlyUrlPath;
		}

		public Boolean getHasComments() {
			return hasComments;
		}

		public String getHeadline() {
			return headline;
		}

		public Long getId() {
			return id;
		}

		public Image getImage() {
			return image;
		}

		public Long getImageId() {
			return imageId;
		}

		public String[] getKeywords() {
			return keywords;
		}

		public void setAggregateRating(AggregateRating aggregateRating) {
			this.aggregateRating = aggregateRating;
		}

		@JsonIgnore
		public void setAggregateRating(
			UnsafeSupplier<AggregateRating, Throwable>
				aggregateRatingUnsafeSupplier) {

			try {
				aggregateRating = aggregateRatingUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setAlternativeHeadline(String alternativeHeadline) {
			this.alternativeHeadline = alternativeHeadline;
		}

		@JsonIgnore
		public void setAlternativeHeadline(
			UnsafeSupplier<String, Throwable>
				alternativeHeadlineUnsafeSupplier) {

			try {
				alternativeHeadline = alternativeHeadlineUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setArticleBody(String articleBody) {
			this.articleBody = articleBody;
		}

		@JsonIgnore
		public void setArticleBody(
			UnsafeSupplier<String, Throwable> articleBodyUnsafeSupplier) {

			try {
				articleBody = articleBodyUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setCaption(String caption) {
			this.caption = caption;
		}

		@JsonIgnore
		public void setCaption(
			UnsafeSupplier<String, Throwable> captionUnsafeSupplier) {

			try {
				caption = captionUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setCategories(Categories[] categories) {
			this.categories = categories;
		}

		@JsonIgnore
		public void setCategories(
			UnsafeSupplier<Categories[], Throwable> categoriesUnsafeSupplier) {

			try {
				categories = categoriesUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setCategoryIds(Long[] categoryIds) {
			this.categoryIds = categoryIds;
		}

		@JsonIgnore
		public void setCategoryIds(
			UnsafeSupplier<Long[], Throwable> categoryIdsUnsafeSupplier) {

			try {
				categoryIds = categoryIdsUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setContentSpace(Long contentSpace) {
			this.contentSpace = contentSpace;
		}

		@JsonIgnore
		public void setContentSpace(
			UnsafeSupplier<Long, Throwable> contentSpaceUnsafeSupplier) {

			try {
				contentSpace = contentSpaceUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setCreator(Creator creator) {
			this.creator = creator;
		}

		@JsonIgnore
		public void setCreator(
			UnsafeSupplier<Creator, Throwable> creatorUnsafeSupplier) {

			try {
				creator = creatorUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}

		@JsonIgnore
		public void setDateCreated(
			UnsafeSupplier<Date, Throwable> dateCreatedUnsafeSupplier) {

			try {
				dateCreated = dateCreatedUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setDateModified(Date dateModified) {
			this.dateModified = dateModified;
		}

		@JsonIgnore
		public void setDateModified(
			UnsafeSupplier<Date, Throwable> dateModifiedUnsafeSupplier) {

			try {
				dateModified = dateModifiedUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setDatePublished(Date datePublished) {
			this.datePublished = datePublished;
		}

		@JsonIgnore
		public void setDatePublished(
			UnsafeSupplier<Date, Throwable> datePublishedUnsafeSupplier) {

			try {
				datePublished = datePublishedUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@JsonIgnore
		public void setDescription(
			UnsafeSupplier<String, Throwable> descriptionUnsafeSupplier) {

			try {
				description = descriptionUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setEncodingFormat(String encodingFormat) {
			this.encodingFormat = encodingFormat;
		}

		@JsonIgnore
		public void setEncodingFormat(
			UnsafeSupplier<String, Throwable> encodingFormatUnsafeSupplier) {

			try {
				encodingFormat = encodingFormatUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setFriendlyUrlPath(String friendlyUrlPath) {
			this.friendlyUrlPath = friendlyUrlPath;
		}

		@JsonIgnore
		public void setFriendlyUrlPath(
			UnsafeSupplier<String, Throwable> friendlyUrlPathUnsafeSupplier) {

			try {
				friendlyUrlPath = friendlyUrlPathUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setHasComments(Boolean hasComments) {
			this.hasComments = hasComments;
		}

		@JsonIgnore
		public void setHasComments(
			UnsafeSupplier<Boolean, Throwable> hasCommentsUnsafeSupplier) {

			try {
				hasComments = hasCommentsUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setHeadline(String headline) {
			this.headline = headline;
		}

		@JsonIgnore
		public void setHeadline(
			UnsafeSupplier<String, Throwable> headlineUnsafeSupplier) {

			try {
				headline = headlineUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setId(Long id) {
			this.id = id;
		}

		@JsonIgnore
		public void setId(UnsafeSupplier<Long, Throwable> idUnsafeSupplier) {
			try {
				id = idUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setImage(Image image) {
			this.image = image;
		}

		@JsonIgnore
		public void setImage(
			UnsafeSupplier<Image, Throwable> imageUnsafeSupplier) {

			try {
				image = imageUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setImageId(Long imageId) {
			this.imageId = imageId;
		}

		@JsonIgnore
		public void setImageId(
			UnsafeSupplier<Long, Throwable> imageIdUnsafeSupplier) {

			try {
				imageId = imageIdUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public void setKeywords(String[] keywords) {
			this.keywords = keywords;
		}

		@JsonIgnore
		public void setKeywords(
			UnsafeSupplier<String[], Throwable> keywordsUnsafeSupplier) {

			try {
				keywords = keywordsUnsafeSupplier.get();
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}

		public String toString() {
			StringBundler sb = new StringBundler(42);

			sb.append("{");

			sb.append("aggregateRating=");

			sb.append(aggregateRating);
			sb.append(", alternativeHeadline=");

			sb.append(alternativeHeadline);
			sb.append(", articleBody=");

			sb.append(articleBody);
			sb.append(", caption=");

			sb.append(caption);
			sb.append(", categories=");

			sb.append(categories);
			sb.append(", categoryIds=");

			sb.append(categoryIds);
			sb.append(", contentSpace=");

			sb.append(contentSpace);
			sb.append(", creator=");

			sb.append(creator);
			sb.append(", dateCreated=");

			sb.append(dateCreated);
			sb.append(", dateModified=");

			sb.append(dateModified);
			sb.append(", datePublished=");

			sb.append(datePublished);
			sb.append(", description=");

			sb.append(description);
			sb.append(", encodingFormat=");

			sb.append(encodingFormat);
			sb.append(", friendlyUrlPath=");

			sb.append(friendlyUrlPath);
			sb.append(", hasComments=");

			sb.append(hasComments);
			sb.append(", headline=");

			sb.append(headline);
			sb.append(", id=");

			sb.append(id);
			sb.append(", image=");

			sb.append(image);
			sb.append(", imageId=");

			sb.append(imageId);
			sb.append(", keywords=");

			sb.append(keywords);

			sb.append("}");

			return sb.toString();
		}

		@JsonProperty
		protected AggregateRating aggregateRating;

		@JsonProperty
		protected String alternativeHeadline;

		@JsonProperty
		protected String articleBody;

		@JsonProperty
		protected String caption;

		@JsonProperty
		protected Categories[] categories;

		@JsonProperty
		protected Long[] categoryIds;

		@JsonProperty
		protected Long contentSpace;

		@JsonProperty
		protected Creator creator;

		@JsonProperty
		protected Date dateCreated;

		@JsonProperty
		protected Date dateModified;

		@JsonProperty
		protected Date datePublished;

		@JsonProperty
		protected String description;

		@JsonProperty
		protected String encodingFormat;

		@JsonProperty
		protected String friendlyUrlPath;

		@JsonProperty
		protected Boolean hasComments;

		@JsonProperty
		protected String headline;

		@JsonProperty
		protected Long id;

		@JsonProperty
		protected Image image;

		@JsonProperty
		protected Long imageId;

		@JsonProperty
		protected String[] keywords;

	}

	protected static class Page<T> {

		public Collection<T> getItems() {
			return new ArrayList<>(items);
		}

		public int getItemsPerPage() {
			return itemsPerPage;
		}

		public int getLastPageNumber() {
			return lastPageNumber;
		}

		public int getPageNumber() {
			return pageNumber;
		}

		public int getTotalCount() {
			return totalCount;
		}

		@JsonProperty
		protected Collection<T> items;

		@JsonProperty("pageSize")
		protected int itemsPerPage;

		@JsonProperty
		protected int lastPageNumber;

		@JsonProperty("page")
		protected int pageNumber;

		@JsonProperty
		protected int totalCount;

	}

	private Http.Options _createHttpOptions() {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");

		String userNameAndPassword = "test@liferay.com:test";

		String encodedUserNameAndPassword = Base64.encode(
			userNameAndPassword.getBytes());

		options.addHeader(
			"Authorization", "Basic " + encodedUserNameAndPassword);

		options.addHeader("Content-Type", "application/json");

		return options;
	}

	private String _toPath(String template, Object value) {
		return template.replaceFirst("\\{.*\\}", String.valueOf(value));
	}

	private static final ObjectMapper _inputObjectMapper = new ObjectMapper() {
		{
			setSerializationInclusion(JsonInclude.Include.NON_NULL);
		}
	};
	private static final ObjectMapper _outputObjectMapper = new ObjectMapper();

	private URL _resourceURL;

}