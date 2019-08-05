package x47b.events;

import java.util.Set;

import com.google.common.collect.Sets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import r01f.exceptions.Throwables;
import r01f.patterns.IsBuilder;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;
import x47b.client.api.X47BPanicButtonClientAPI;
import x47b.model.X47BAlarmEvent;
import x47b.model.X47BAlarmMessage;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForDivision;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForLocation;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForOrganization;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForService;
import x47b.model.X47BAlarmMessageEntityAbstracts.X47BAlarmMessageAbstractForWorkPlace;
import x47b.model.org.X47BOrgDivision;
import x47b.model.org.X47BOrgDivisionService;
import x47b.model.org.X47BOrgDivisionServiceLocation;
import x47b.model.org.X47BOrganization;
import x47b.model.org.X47BWorkPlace;

/**
 * Builds a {@link X47BAlarmMessage} from an {@link X47BAlarmEvent}
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class X47BAlarmMessageBuilder
  implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static X47BAlarmMessageBuilderEventStep using(final X47BPanicButtonClientAPI api) {
		return new X47BAlarmMessageBuilder() {/* nothing */}
						.new X47BAlarmMessageBuilderEventStep(api,
															  new X47BAlarmMessage());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class X47BAlarmMessageBuilderEventStep {
		private final X47BPanicButtonClientAPI _api;
		private final X47BAlarmMessage _message;

		public X47BAlarmMessage createForEvent(final X47BAlarmEvent event) {
			_message.setAlarmEventOid(event.getOid());

			X47BOrganization org = _api.organizationsAPI()
										.getForCRUD()
											.load(event.getOrganization().getOid());
			X47BOrgDivision division = _api.orgDivisionsAPI()
												.getForCRUD()
													.load(event.getDivision().getOid());
			X47BOrgDivisionService service = _api.orgDivisionServicesAPI()
														.getForCRUD()
															.load(event.getService().getOid());
			X47BOrgDivisionServiceLocation location = _api.orgDivisionServiceLocationsAPI()
															.getForCRUD()
																.load(event.getLocation().getOid());
			X47BWorkPlace workPlace = _api.workPlacesAPI()
												.getForCRUD()
													.load(event.getWorkPlace().getOid());
			// org & loc are mandatory
			if (org == null || division == null || service == null || location == null || workPlace == null) {
				throw new IllegalStateException(Throwables.message("The {} with oid={} is NOT valid: the organization={}, division={}, service={}, location={} or workPlace={} does NOT exists",
																   X47BAlarmEvent.class.getSimpleName(),event.getOid(),
																   event.getOrganization().getId(),event.getDivision().getId(),event.getService().getId(),event.getLocation().getId(),event.getWorkPlace().getId()));
			}
			_message.setOrganization(new X47BAlarmMessageAbstractForOrganization(org));
			_message.setDivision(new X47BAlarmMessageAbstractForDivision(division));
			_message.setService(new X47BAlarmMessageAbstractForService(service));
			_message.setLocation(new X47BAlarmMessageAbstractForLocation(location));
			_message.setWorkPlace(new X47BAlarmMessageAbstractForWorkPlace(workPlace));

			// Get the list of phones & mails to notify
			Set<Phone> phones = Sets.newHashSet();
			if (CollectionUtils.hasData(org.getPhones())) phones.addAll(org.getPhones());
			if (CollectionUtils.hasData(division.getPhones())) phones.addAll(division.getPhones());
			if (CollectionUtils.hasData(service.getPhones())) phones.addAll(service.getPhones());
			if (CollectionUtils.hasData(location.getPhones())) phones.addAll(location.getPhones());
			if (CollectionUtils.hasData(workPlace.getPhones())) phones.addAll(workPlace.getPhones());
			_message.setPhones(phones);

			Set<EMail> mails = Sets.newHashSet();
			if (CollectionUtils.hasData(org.getEmails())) mails.addAll(org.getEmails());
			if (CollectionUtils.hasData(division.getEmails())) mails.addAll(division.getEmails());
			if (CollectionUtils.hasData(service.getEmails())) mails.addAll(service.getEmails());
			if (CollectionUtils.hasData(location.getEmails())) mails.addAll(location.getEmails());
			if (CollectionUtils.hasData(workPlace.getEmails())) mails.addAll(workPlace.getEmails());
			_message.setMails(mails);

			// Return
			return _message;
		}
	}
}
