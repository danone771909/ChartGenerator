package com.chart.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * Created by Daniel on 2016-04-17.
 */
public interface ChartRepository extends CrudRepository<Chart, Long>
{
    @RestResource(path = "by-chartname")
    Chart findByChartname(@Param("id") String chartname);

    Collection<Chart> findAll();
}
