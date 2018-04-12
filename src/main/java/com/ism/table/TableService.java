package com.ism.table;

import com.ism.table.model.Table;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface TableService {

    Collection<Table> getAll();

    Optional<Table> getById(Integer id);

    Table addTable(Table table) throws IllegalArgumentException;

    Table modifyTable(Table table) throws IllegalArgumentException;

}
