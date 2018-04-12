package com.ism.table.impl;

import com.ism.table.TableService;
import com.ism.table.model.Table;
import com.ism.table.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TableImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public Collection<Table> getAll(){
        return StreamSupport.stream(tableRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Optional<Table> getById(Integer id){
        return tableRepository.findById(id);
    }

    @Override
    public Table addTable(Table table) throws IllegalArgumentException {
        if(table.getId() == null || !tableRepository.existsById(table.getId())) {
            try {
                return tableRepository.save(table);
            }
            catch (DataAccessException e){
                throw  new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Table does already exist");
    }

    @Override
    public Table modifyTable(Table table) throws IllegalArgumentException {
        Optional<Table> originalTable = tableRepository.findById(table.getId());
        if(originalTable.isPresent()) {
            try {
                Table originalTableContent = originalTable.get();
                if (table.getCapacity()==null)
                    table.setCapacity(originalTableContent.getCapacity());
                if (table.getName()==null)
                    table.setName(originalTableContent.getName());
                return tableRepository.save(table);
            } catch (DataAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Table does not exist");
    }
}
