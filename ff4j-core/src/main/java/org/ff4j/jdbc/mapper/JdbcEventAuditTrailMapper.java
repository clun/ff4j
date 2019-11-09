package org.ff4j.jdbc.mapper;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ff4j.event.Event;
import org.ff4j.event.Event.Action;
import org.ff4j.event.Event.Scope;
import org.ff4j.event.Event.Source;
import org.ff4j.event.EventQuery;
import org.ff4j.exception.AuditAccessException;
import org.ff4j.jdbc.JdbcConstants.AuditTrailColumns;
import org.ff4j.jdbc.JdbcQueryBuilder;
import org.ff4j.jdbc.JdbcUtils;
import org.ff4j.mapper.EventMapper;
import org.ff4j.utils.JsonUtils;

/**
 * Map resultset into {@link Event}
 *
 * @author Cedrick Lunven (@clunven)
 */
public class JdbcEventAuditTrailMapper extends AbstractJdbcMapper implements EventMapper < PreparedStatement, ResultSet> {
  
    public JdbcEventAuditTrailMapper(Connection sqlConn, JdbcQueryBuilder qbd) {
        super(sqlConn, qbd);
    }
    
    public PreparedStatement buildPurgeStatement(EventQuery query) {
        PreparedStatement stmt = null;
        try {
            StringBuilder sqlQuery = new StringBuilder(queryBuilder.sqlDeleteAllAuditrail())
                    .append(" WHERE ")
                    .append(AuditTrailColumns.TIMESTAMP + " >? AND ")
                    .append(AuditTrailColumns.TIMESTAMP + " <? ");
            if (!query.getFilteredEntityUids().isEmpty()) {
                sqlQuery.append(" AND ")
                        .append(AuditTrailColumns.NAME + " IN")
                        .append(toStringListWithComma(query.getFilteredEntityUids()));
            }
            if (!query.getFilteredHostNames().isEmpty()) {
                sqlQuery.append(" AND ")
                        .append(AuditTrailColumns.HOSTNAME + " IN")
                        .append(toStringListWithComma(query.getFilteredHostNames()));
            }
            if (!query.getFilteredSources().isEmpty()) {
                sqlQuery.append(" AND ")
                        .append(AuditTrailColumns.SOURCE + " IN")
                        .append(toStringListWithComma(query.getFilteredSources()));
            }
            if (!query.getFilteredValues().isEmpty()) {
                sqlQuery.append(" AND ")
                        .append(AuditTrailColumns.VALUE + " IN")
                        .append(toStringListWithComma(query.getFilteredValues()));
            }
            if (!query.getFilteredValues().isEmpty()) {
                sqlQuery.append(" AND ")
                        .append(AuditTrailColumns.VALUE + " IN")
                        .append(toStringListWithComma(query.getFilteredValues()));
            }
           
            List<Object> params = new ArrayList<>();
            params.add(query.getFrom());
            params.add(query.getTo());
            stmt = JdbcUtils.buildStatement(sqlConn, sqlQuery.toString(), params.toArray());
            
        } catch(SQLException sqlEx) {
            throw new AuditAccessException("Cannot purge audit trail", sqlEx);
        }
        return stmt;
        
    }
    
    public static String toStringListWithComma(Set<String> items) {
        return new StringBuilder("('").append(String.join("','", items)).append("')").toString();
    }
    
    /** {@inheritDoc} */
    @Override
    public PreparedStatement mapToRepository(Event evt) {
        PreparedStatement stmt = null;
        try {
            stmt = sqlConn.prepareStatement(queryBuilder.sqlInsertAuditTrail());
            populateEntity(stmt, evt);
            
            stmt.setTimestamp(6, new java.sql.Timestamp(evt.getTimestamp()));
            stmt.setString( 7, evt.getScope());
            stmt.setString( 8, evt.getRefEntityUid());
            stmt.setString( 9, evt.getAction());
            stmt.setString(10, evt.getHostName());
            stmt.setString(11, evt.getSource());
            stmt.setLong(  12, evt.getDuration().orElse(0L));
            stmt.setString(13, evt.getValue().orElse(null));
            stmt.setString(14, JsonUtils.mapAsJson(evt.getProperties()));
        } catch(SQLException sqlEx) {
            throw new AuditAccessException("Cannot create statement to create event", sqlEx);
        }
        return stmt;
    }

    /**
     * Unmarshall a resultset to Event.
     *
     * @param rs
     *      current line
     * @return
     *      bean populated
     * @throws SQLException
     *      cannot read SQL result
     */
    @Override
    public Event mapFromRepository(ResultSet rs) {
        try {
            Event.Builder eventBuilder = Event.builder();
            eventBuilder.refEntityUid(rs.getString(AuditTrailColumns.UID.colname()));
            eventBuilder.scope(Scope.valueOf(rs.getString(AuditTrailColumns.TYPE.colname())));
            eventBuilder.refEntityUid(rs.getString(AuditTrailColumns.NAME.colname()));
            eventBuilder.action(Action.valueOf(rs.getString(AuditTrailColumns.ACTION.colname())));
            eventBuilder.hostName(rs.getString(AuditTrailColumns.HOSTNAME.colname()));
            eventBuilder.source(Source.valueOf(rs.getString(AuditTrailColumns.SOURCE.colname())));
            eventBuilder.duration(rs.getLong(AuditTrailColumns.DURATION.colname()));
            eventBuilder.value(rs.getString(AuditTrailColumns.VALUE.colname()));
            Event evt = eventBuilder.build();
            mapEntity(rs, evt);
            return evt;
        } catch(SQLException sqlEx) {
            throw new AuditAccessException("Cannot map result to Event", sqlEx);
        }
    }
}