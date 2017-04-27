package com.jinmao.web.test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

/**
 * @描述 : mongo练习
 * @创建者 : HeZeMin
 * @创建时间 : 2016-9-12 下午5:15:49
 */
public class MongoDome {

	public static void main(String[] args) {
		try {
			//建立一个mongo的数据库连接对象
			Mongo mongo = new Mongo("192.168.1.197:27017");
//			Mongo mongo = new Mongo("192.168.1.197:27018");
			
			//查询所有的Databasename
//			List<String> databaseNames = mongo.getDatabaseNames();
//			for (String string : databaseNames) {
//				System.out.println(string);
//			}
			
			//创建相关数据库连接
			
			DB db = mongo.getDB("hezemin");
			
			//查询数据库所有的集合名字
			
//			System.out.println(db.getCollectionNames());
			
			
			//查询所有数据
			
			DBCollection collection = db.getCollection("persons");
			
//			DBCursor dbCursor = collection.find();
//			while(dbCursor.hasNext()){
//				DBObject dbObject = dbCursor.next();
//				System.out.println(dbObject.get("name"));
//			}
			//其他操作
//			System.out.println(dbCursor.size());
			
			
			//添加
//			DBObject dbObject = new BasicDBObject();
//			dbObject.put("name", "小王");
//			collection.insert(dbObject);
			
			//批量添加
//			List<DBObject> dbObjects = new ArrayList<DBObject>();
//			DBObject dbObject = new BasicDBObject();
//			dbObject.put("name", "小李");
//			dbObjects.add(dbObject);
//			collection.insert(dbObjects);
			
			
			//根据属性删除数据
			DBObject dbObject = new BasicDBObject();
			dbObject.put("name", "jim");
			int count = collection.remove(dbObject).getN();
			
			
			//查询
//			DBObject dbObject = new BasicDBObject();
//			dbObject.put("name", true);
//			DBCursor dbCursor = collection.find(null, dbObject);
//			while(dbCursor.hasNext()){
//				DBObject object = dbCursor.next();
//				System.out.println(object.get("_id"));
//				System.out.println(object.get("name"));
//			}
			
			
			
			
			
			mongo.close();
			System.out.println("影响行数:" + count);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
