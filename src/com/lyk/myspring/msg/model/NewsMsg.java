package com.lyk.myspring.msg.model;

import java.util.List;

public class NewsMsg extends BaseMsg{

	
	private int ArticleCount;
	private List<NewsItem> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<NewsItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<NewsItem> articles) {
		Articles = articles;
	}
	
}
