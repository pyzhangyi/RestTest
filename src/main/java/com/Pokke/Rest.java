package com.Pokke;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eric on 5/31/17.
 */

@Path("/RestTest")
public class Rest {

    @GET
    @Path("/dbtest")
    @Produces("application/json")
    public Response simpleDBTest() throws Exception {
        String testSQLLocation = "/SQLScripts/select1.sql";
        Map<String, String> replacement = new HashMap<String, String>();
        replacement.put("$tablename", "test");

        return Response.status(200).entity(Utility.resultSetToJSON(new DBOperations().selectOperation(Utility.replace(testSQLLocation, replacement)))).build();
    }

    @GET
    @Path("/dbtest/{id}")
    @Produces("application/json")
    public Response DBSelectTest(@PathParam("id") int id) throws Exception {
        String testSQLLocation = "/SQLScripts/select2.sql";
        Map<String, String> replacement = new HashMap<String, String>();
        replacement.put("$tablename", "test");
        List<Integer> parameters = new ArrayList<Integer>();
        parameters.add(id);

        return Response.status(200).entity(Utility.resultSetToJSON(new DBOperations().conditionSelectOperation(Utility.replace(testSQLLocation, replacement),parameters))).build();
    }

}
