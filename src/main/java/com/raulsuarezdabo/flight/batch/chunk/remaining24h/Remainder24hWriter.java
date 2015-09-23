
package com.raulsuarezdabo.flight.batch.chunk.remaining24h;

import java.io.Serializable;
import java.util.List;
import javax.batch.api.chunk.ItemWriter;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author raulsuarez
 */
@Scope(value="remainderStep")
public class Remainder24hWriter implements ItemWriter {

    @Override
    public void open(Serializable srlzbl) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeItems(List<Object> list) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
