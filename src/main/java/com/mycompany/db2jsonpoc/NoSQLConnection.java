package com.mycompany.db2jsonpoc;

import com.ibm.nosql.json.api.BasicDBObject;
import com.ibm.nosql.json.api.DB;
import com.ibm.nosql.json.api.DBCollection;
import com.ibm.nosql.json.api.DBCursor;
import com.ibm.nosql.json.api.DBObject;
import com.ibm.nosql.json.api.NoSQLClient;

public class NoSQLConnection {
	public static void main (String[] args)
	  {
	    // for convenience, the arguments are supplied hardcoded in this sample
	    String databaseHost = "localhost";
	    int port = 50000;
	    String databaseName = "sample";
	    String user = "db2admin";                          // <- DB2 user name
	    String password = "payments";                      // <- user password
	    String ns = "TEST";                        // <- JSON namespace aka DB2 Schema
	    
	    // build the required url 
	    //     e.g. jdbc:db2://localhost:50000/sample
	    String databaseUrl = "jdbc:db2://" + databaseHost + ":" + port + "/" + databaseName;
	    System.out.println ("databaseUrl: " + databaseUrl);
	    
	    // get a DB object with this connection information
	    //     represents the JSON namespace aka SQL schemaname inside DB2;
	    //     will use the username as default schema if not set explicitly
	    //     
	    DB db = NoSQLClient.getDB (databaseUrl, user, password, ns);
	    
	    // get a DBCollection object
	    //     the collection will not be created at this point
	    //     to create it explicitly use db.createCollection(name, indexSpec)
	    //db.createCollection("books", null);
	    DBCollection col = db.getCollection ("books");
	    
	    // insert document
	    //     the collection will be automatically created, if it does not exist 
	    //     when the first document is inserted;
	    //     the document does not contain an _id field, therefore the collection
	    //     is created with a primary key that takes generated binary identifiers
		/*
		 * BasicDBObject json = new BasicDBObject (); json.append ("author", "Smith");
		 * json.append ("title", "My first book"); System.out.println ("Inserting: " +
		 * json); // insert, ignore WriteResult here, we'll search for it anyway
		 * col.insert (json);
		 */
	    
	    // submit a query
	    //     build a query object with the desired search criteria;
	    //     results are available through a cursor;
	    //     the query is executed with the first call to hasNext();
	    //     the output shows the generated identifier as well as the two attributes
	    BasicDBObject query = new BasicDBObject ();
	    query.append ("author", "Smith");
	    
	    DBCursor cursor = col.find (query);
	    try
	    {
	      while (cursor.hasNext ()) {
	        DBObject obj = cursor.next ();
	        System.out.println ("Retrieved: " + obj);
	      }
	    }
	    finally
	    {
	      cursor.close ();
	    }
	  }
}
