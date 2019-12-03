package x47b.test;

import java.util.Collection;
import java.util.Date;

import lombok.RequiredArgsConstructor;
import r01f.guids.CommonOIDs.UserCode;
import r01f.guids.PersistableObjectOID;
import r01f.model.PersistableModelObject;
import r01f.services.client.api.delegates.ClientAPIDelegateForModelObjectFindServices;
import r01f.types.Range;
import r01f.util.types.Dates;

@RequiredArgsConstructor
public class X47BPersistableModelObjectFindTest<O extends PersistableObjectOID,M extends PersistableModelObject<O>> {
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	private final ClientAPIDelegateForModelObjectFindServices<O,M> _findAPI;
	
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	public static <O extends PersistableObjectOID,M extends PersistableModelObject<O>> X47BPersistableModelObjectFindTest<O,M> create(final ClientAPIDelegateForModelObjectFindServices<O,M> findAPI) {
		return new X47BPersistableModelObjectFindTest<O,M>(findAPI);
	}
/////////////////////////////////////////////////////////////////////////////////////////
//  
/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Tests the CRUD API (creates an entity, updates it, loads it and finally deletes it)
	 * @param modelObject
	 */
	public void testFind() {
		// [1] - All entities
		System.out.println("FIND ALL ENTITY's OIDS_____________________________");
		Collection<O> allOids = _findAPI.findAll();
		System.out.println(">> " + allOids);

		// [2] - By create / last update date
		Range<Date> dateRange = Range.open(Dates.fromFormatedString("31/12/2014","dd/MM/yyyy"),
										   new Date());
		
		System.out.println("FIND ENTITY's OIDs BY CREATE DATE: " + dateRange.asString() + " __________________");
		Collection<O> oidsByCreateDate = _findAPI.findByCreateDate(dateRange);
		System.out.println(">> " + oidsByCreateDate);
		
		System.out.println("FIND ENTITY's OIDs BY LAST UPDATE DATE: " + dateRange.asString() + " ______________");
		Collection<O> oidsByLastUpdatedDate = _findAPI.findByCreateDate(dateRange);
		System.out.println(">> " + oidsByLastUpdatedDate);
		
		// [3] - By creator / last updator
		UserCode user = UserCode.forId("myNameIsGOD");
		
		System.out.println("FIND ENTITY's OIDs BY CREATOR: " + user + " ________________________");
		Collection<O> oidsByCreator = _findAPI.findByCreator(user);
		System.out.println(">> " + oidsByCreator);

		System.out.println("FIND ENTITY's OIDs BY LAST UPDATOR: " + user + " ___________________");
		Collection<O> oidsByLastUpdator = _findAPI.findByLastUpdator(user);
		System.out.println(">> " + oidsByLastUpdator);
	}
}
