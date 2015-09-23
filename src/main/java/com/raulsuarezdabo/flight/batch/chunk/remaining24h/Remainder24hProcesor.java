
package com.raulsuarezdabo.flight.batch.chunk.remaining24h;

import javax.batch.api.chunk.ItemProcessor;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author raulsuarez
 */
@Scope(value="remainderStep")
public class Remainder24hProcesor implements ItemProcessor {

    @Override
    public Object processItem(Object o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
