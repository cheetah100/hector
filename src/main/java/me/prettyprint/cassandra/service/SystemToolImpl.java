package me.prettyprint.cassandra.service;

import me.prettyprint.hector.api.ddl.HCfDef;
import me.prettyprint.hector.api.exceptions.HectorException;
//import org.apache.cassandra.thrift.*;
import org.apache.cassandra.thrift.Cassandra;
import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.InvalidRequestException;
import org.apache.cassandra.thrift.KsDef;
import org.apache.thrift.TException;

/**
 * Created by IntelliJ IDEA.
 * User: peter
 * Date: 2/09/2010
 * Time: 10:57:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemToolImpl implements SystemTool {


    private CassandraClient client;
    private final CassandraClient.FailoverPolicy failoverPolicy;
    private final CassandraClientPool clientPools;
    private final CassandraClientMonitor monitor;
    private final ExceptionsTranslator xtrans;


    public SystemToolImpl( CassandraClient client, CassandraClient.FailoverPolicy failoverPolicy, CassandraClientPool clientPools, CassandraClientMonitor monitor ) {
        this.client = client;
        this.failoverPolicy = failoverPolicy;
        this.clientPools = clientPools;
        this.monitor = monitor;
        this.xtrans = new ExceptionsTranslatorImpl();
    }

    private void operateWithFailover(Operation<?> op) throws HectorException {
        FailoverOperator operator = new FailoverOperator(  this.failoverPolicy, this.monitor, this.client, clientPools, null);
        client = operator.operate(op);
    }

    @Override
    public void addKeyspace(final KsDef keyspaceDefinition) {

        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace("system");
                    client.getCassandra().system_add_keyspace( keyspaceDefinition );
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }

    @Override
    public void renameKeyspace(final String from, final String to) {

        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace( "system");
                    client.getCassandra().system_rename_keyspace( from, to);
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }

    @Override
    public void dropKeyspace(final String keyspace) {

        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace( "system");
                    client.getCassandra().system_drop_keyspace( keyspace );
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }

    @Override
    public void addColumnFamily(final String keyspace, final CfDef columnFamilyDefinition) {

        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace( keyspace );
                    client.getCassandra().system_add_column_family( columnFamilyDefinition );
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }

    @Override
    public void renameColumnFamily(final String keyspace, final String from, final String to) {
        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace( keyspace );
                    client.getCassandra().system_rename_column_family( from, to );
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }

    @Override
    public void dropColumnFamily(final String keyspace, final String columnFamily) {
        Operation<Void> op = new Operation<Void>(OperationType.WRITE) {

            public Void execute(Cassandra.Client cassandra) throws HectorException {
                try {
                    client.getCassandra().set_keyspace( keyspace );
                    client.getCassandra().system_drop_column_family( columnFamily );
                } catch (InvalidRequestException e) {
                    throw xtrans.translate(e);
                } catch (TException e) {
                    throw xtrans.translate(e);
                }
                return null;
            }
        };

        operateWithFailover(op);
    }
}
