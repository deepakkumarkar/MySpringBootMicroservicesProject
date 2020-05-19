package com.deepak.Catalog;

import org.jongo.Jongo;
import org.springframework.stereotype.Service;

import com.deepak.utill.MongoDBUtill;

@Service
public class CatalogService {

	public  CatalogItem getCatalog(int id){
	 return	new Jongo(MongoDBUtill.getDB()).getCollection("movieCatalog").findOne("{movieId:#}",id).as(CatalogItem.class);
		
	}

	public void addCatalog(CatalogItem catalogItem) {
		new Jongo(MongoDBUtill.getDB()).getCollection("movieCatalog").insert(catalogItem);
		
	}
}
