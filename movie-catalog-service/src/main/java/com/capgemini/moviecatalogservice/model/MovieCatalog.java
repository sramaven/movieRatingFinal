package com.capgemini.moviecatalogservice.model;

import java.util.List;

public class MovieCatalog {
private List<CatalogClass> catalogItems;

public List<CatalogClass> getCatalogItems() {
	return catalogItems;
}

public MovieCatalog() {
	super();
}

public MovieCatalog(List<CatalogClass> catalogItems) {
	super();
	this.catalogItems = catalogItems;
}

public void setCatalogItems(List<CatalogClass> catalogItems) {
	this.catalogItems = catalogItems;
}
}
