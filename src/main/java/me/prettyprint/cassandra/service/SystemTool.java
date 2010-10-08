package me.prettyprint.cassandra.service;

import org.apache.cassandra.thrift.CfDef;
import org.apache.cassandra.thrift.KsDef;

/**
 * Created by IntelliJ IDEA.
 * User: peter
 * Date: 1/09/2010
 * Time: 4:50:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SystemTool {

    public void addKeyspace( KsDef keyspaceDefinition );

    public void renameKeyspace( String from, String to);

    public void dropKeyspace( String keyspace );

    public void addColumnFamily( String keyspace, CfDef columnFamilyDefinition );

    public void renameColumnFamily( String keyspace, String from, String to );

    public void dropColumnFamily( String keyspace, String columnFamily );

}
