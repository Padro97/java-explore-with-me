package ru.practicum.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.practicum.EndpointHit;
import ru.practicum.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.practicum.storage.StorageSqlConstants.*;

@Component
@RequiredArgsConstructor
public class StatsStorageImpl implements StatsStorage {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void hit(EndpointHit hit) {
        Map<String, Object> argMap = new HashMap<>();
        argMap.put("app", hit.getApp());
        argMap.put("uri", hit.getUri());
        argMap.put("ip", hit.getIp());
        argMap.put("timestamp", LocalDateTime.parse(hit.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        jdbcTemplate.update(INSERT_STATISTICS_SQL, argMap);
    }

    @Override
    public List<ViewStats> stats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        SqlRowSet rs;
        Map<String, Object> argMap = new HashMap<>();
        if (uris.isEmpty() && !unique) {
            argMap.put("start", start);
            argMap.put("end", end);
            rs = jdbcTemplate.queryForRowSet(SELECT_STATISTICS_ALL_SQL, argMap);
        } else if (uris.isEmpty()) {
            argMap.put("start", start);
            argMap.put("end", end);
            rs = jdbcTemplate.queryForRowSet(SELECT_STATISTICS_ALL_UNIQUE_SQL, argMap);
        } else if (unique) {
            argMap.put("start", start);
            argMap.put("end", end);
            argMap.put("uris", uris);
            rs = jdbcTemplate.queryForRowSet(SELECT_STATISTICS_WITH_URIS_UNIQUE_IP_SQL, argMap);
        } else {
            argMap.put("start", start);
            argMap.put("end", end);
            argMap.put("uris", uris);
            rs = jdbcTemplate.queryForRowSet(SELECT_STATISTICS_WITH_URIS_SQL, argMap);
        }
        List<ViewStats> stats = new ArrayList<>();
        while (rs.next()) {
            stats.add(ViewStats.builder()
                    .app(rs.getString(1))
                    .uri(rs.getString(2))
                    .hits(rs.getLong(3))
                    .build());
        }
        return stats;
    }
}