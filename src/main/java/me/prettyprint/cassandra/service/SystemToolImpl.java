package me.prettyprint.cassandra.service;

import me.prettyprint.cassandra.model.HectorException;
import me.prettyprint.cassandra.model.HectorTransportException;
import org.apache.cassandra.thrift.*;
import org.apache.thrift.TException;

/**
 * Created by IntelliJ IDEA.
 * User: peter
 * Date: 2/09/2010
 * Time: 10:57:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemToolImpl implements SystemTool {

    private Cassandra.Client client;

    public SystemToolImpl( Cassandra.Client client ) {
        this.client = client;
    }

    @Override
    public void addKeyspace(KsDef keyspaceDefinition) {

        try {
            client.set_keyspace( "system");
            client.system_add_keyspace( keyspaceDefinition );
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }
    }

    @Override
    public void renameKeyspace(String from, String to) {
        try {
            client.set_keyspace( "system");
            client.system_rename_keyspace( from, to);
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }
    }

    @Override
    public void dropKeyspace(String keyspace) {
        try {
            client.set_keyspace( "system");
            client.system_drop_keyspace( keyspace );
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }
    }

    @Override
    public void addColumnFamily(CfDef columnFamilyDefinition) {
        try {
            client.set_keyspace( "system");
            client.system_add_column_family( columnFamilyDefinition );
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }
    }

    @Override
    public void renameColumnFamily(String from, String to) {
        try {
            client.set_keyspace( "system");
            client.system_rename_column_family( from, to );
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }

    }

    @Override
    public void dropColumnFamily(String columnFamily) {
        try {
            client.system_drop_column_family( columnFamily );
        } catch (InvalidRequestException e) {
            throw new HectorException(e);
        } catch (TException e) {
            throw new HectorTransportException(e);
        }

    }
}
