package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

    
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<Holiday> findAllHolidays(){
//        String query = "Select * from holidays";
//        RowMapper rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
//        return  jdbcTemplate.query(query, rowMapper);
//    }
}
