package team15.cfg2015;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    /**
     * Configures the keyspaces for the program
     * @param c The Cassandra cluster to use
     */
    public static void SetUpKeySpaces(Cluster c) {
        try {
            String createkeyspace = "create keyspace if not exists cfg-team15  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            String CreateUserTable = "CREATE TABLE if not exists cfg-team15.user ("
                    + " username varchar, \n"
                    + " name varchar, \n"
                    + " contact int, \n"
                    + " times list<timestamp>, \n" //2D int array
                    + " anniversaries list<timestamp>, \n"
                    + " condition text, \n"
                    + " preferences list<text>, \n"
                    + " urgent bool, \n"
                    + " PRIMARY KEY (username)"
                    + ")";
            String CreateCallLog = "CREATE TABLE if not exists cfg-team15.callLog (\n"
                    + "callID uuid,\n"
                    + "caller varchar,\n"
                    + "callee varchar,\n"
                    + "time timestamp,\n"
                    + "text comments"
                    + "PRIMARY KEY (callID)\n"
                    + ") WITH CLUSTERING ORDER BY (time desc);";
            String CreateMessages = "CREATE TYPE if not exists cfg-team15.Messages (\n"
                    + "      messageID uuid,\n"
                    + "      from varchar,\n"
                    + "      to varchar,\n"
                    + "      subject text,\n"
                    + "      body text\n"
                    + "  );";
            
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created cfg-team15 ");
            } catch (Exception et) {
                System.out.println("Can't create cfg-team15 " + et);
            }

            //now add some column families 
            System.out.println("" + CreateUserTable);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUserTable);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user table " + et);
            }
            System.out.println("" + CreateCallLog);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateCallLog);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create call log table " + et);
            }
            System.out.println("" + CreateMessages);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateMessages);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create message table " + et);
            }
            
            
            session.close();

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}