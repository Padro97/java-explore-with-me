package ru.practicum.storage;

import ru.practicum.EndpointHit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class StorageSqlConstants {
    public static final String INSERT_STATISTICS_SQL = "INSERT INTO statistics (application, uri, ip, timestamp)" +
            " VALUES (:app, :uri, :ip, :timestamp)";
    public static final String SELECT_STATISTICS_ALL_SQL = "SELECT application, uri, COUNT(uri) " +
            "FROM statistics AS s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY uri, application " +
            "ORDER BY COUNT(uri) DESC ";
    public static final String SELECT_STATISTICS_ALL_UNIQUE_SQL = "SELECT DISTINCT application, uri, COUNT(DISTINCT ip) " +
            "FROM statistics AS s " +
            "WHERE s.timestamp BETWEEN :start AND :end " +
            "GROUP BY uri, application " +
            "ORDER BY COUNT(ip) DESC ";
    public static final String SELECT_STATISTICS_WITH_URIS_UNIQUE_IP_SQL = "SELECT application, uri, COUNT(DISTINCT ip) " +
            "FROM statistics AS s " +
            "WHERE s.timestamp BETWEEN :start AND :end AND s.uri IN (:uris) " +
            "GROUP BY uri, application " +
            "ORDER BY COUNT(ip) DESC ";
    public static final String SELECT_STATISTICS_WITH_URIS_SQL = "SELECT application, uri, COUNT(uri) " +
            "FROM statistics AS s " +
            "WHERE s.timestamp BETWEEN :start AND :end AND s.uri IN (:uris) " +
            "GROUP BY uri, application " +
            "ORDER BY COUNT(uri) DESC ";
}
