package com.ism.table;

import com.ism.table.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    public TableService tableService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Table> getTracks(){
        return tableService.getAll();
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Table> getTracks(@PathVariable Integer id){
        return tableService.getById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Table> addTrack(@RequestBody Table table) {
        try {
            return new ResponseEntity<>(tableService.addTable(table), HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Table> modifyTrack(@RequestBody Table table) {
        Optional<Table> originalTrack = tableService.getById(table.getId());
        if(!originalTrack.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        try {
            return new ResponseEntity<>(tableService.modifyTable(table),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
