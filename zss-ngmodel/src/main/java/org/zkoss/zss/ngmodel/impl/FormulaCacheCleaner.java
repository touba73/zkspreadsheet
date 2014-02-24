package org.zkoss.zss.ngmodel.impl;

import java.util.Set;

import org.zkoss.zss.ngmodel.NBookSeries;
import org.zkoss.zss.ngmodel.sys.dependency.DependencyTable;
import org.zkoss.zss.ngmodel.sys.dependency.Ref;

/**
 * 
 * @author dennis
 *
 */
public class FormulaCacheCleaner {

	static ThreadLocal<FormulaCacheCleaner>  current = new ThreadLocal<FormulaCacheCleaner>();
	
	final private NBookSeries bookSeries;
	
	public FormulaCacheCleaner(NBookSeries bookSeries){
		this.bookSeries = bookSeries;
	}

	public static FormulaCacheCleaner setCurrent(FormulaCacheCleaner ctx){
		FormulaCacheCleaner old = current.get();
		current.set(ctx);
		return old;
	}
	
	public static FormulaCacheCleaner getCurrent(){
		return current.get();
	}
	
	public void clear(Set<Ref> dependents){
		new FormulaCacheClearHelper(bookSeries).clear(dependents);
	}

	public void clearByPrecedent(Ref precedent) {
		DependencyTable table = ((AbstractBookSeriesAdv)bookSeries).getDependencyTable();
		Set<Ref> dependents = table.getDependents(precedent);
		if(dependents.size()>0){
			clear(dependents);
		}
	}
}
