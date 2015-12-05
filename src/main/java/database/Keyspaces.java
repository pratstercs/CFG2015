package database;

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
            String createkeyspace = "create keyspace if not exists cfgteam15  WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            String CreateUserTable = "CREATE TABLE if not exists cfgteam15.users ("
                    + " username varchar, \n"
                    + " password varchar, \n"
                    + " isOldPerson boolean, \n"
                    + " isMale boolean, \n"
                    + " age int, \n"
                    + " name varchar, \n"
                    + " contact int, \n"
                    + " email text, \n"
                    + " times list<int>, \n" //2D int array
                    + " anniversaries list<timestamp>, \n"
                    + " condition text, \n"
                    + " preferences list<text>, \n"
                    + " urgent boolean, \n"
                    + " bio text, \n"
                    + " secChecks text, \n" //DBS checks
                    + " PRIMARY KEY (username)"
                    + ")";
            String CreateCallLog = "CREATE TABLE if not exists cfgteam15.callLog (\n"
                    + "callID uuid,\n"
                    + "caller varchar,\n"
                    + "callee varchar,\n"
                    + "time timestamp,\n"
                    + "comments text,\n"
                    + "PRIMARY KEY (callID)\n"
                    + ");";
            String CreateMessages = "CREATE TYPE if not exists cfgteam15.messages (\n"
                    + "      messageID uuid,\n"
                    + "      fromUser varchar,\n"
                    + "      toUser varchar,\n"
                    + "      subject text,\n"
                    + "      body text,\n"
                    + "      isRead boolean\n"
                    + "  );";
            
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created cfgteam15 ");
            } catch (Exception et) {
                System.out.println("Can't create cfgteam15 " + et);
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
