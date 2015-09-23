
package com.raulsuarezdabo.flight.batch.chunk.remaining24h;

import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author raulsuarez
 */
@Scope(value="remainderStep")
public class Remainder24hReader implements ItemReader {

    @Override
    public void open(Serializable srlzbl) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object readItem() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
