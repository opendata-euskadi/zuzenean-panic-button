package pb01c.events;

import java.util.Set;

import com.google.common.collect.Sets;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pb01a.client.api.PB01APanicButtonClientAPI;
import pb01a.model.PB01AAlarmEvent;
import pb01a.model.PB01AAlarmMessage;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForDivision;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForLocation;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForOrganization;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForService;
import pb01a.model.PB01AAlarmMessageEntityAbstracts.PB01AAlarmMessageAbstractForWorkPlace;
import pb01a.model.org.PB01AOrgDivision;
import pb01a.model.org.PB01AOrgDivisionService;
import pb01a.model.org.PB01AOrgDivisionServiceLocation;
import pb01a.model.org.PB01AOrganization;
import pb01a.model.org.PB01AWorkPlace;
import r01f.exceptions.Throwables;
import r01f.patterns.IsBuilder;
import r01f.types.contact.EMail;
import r01f.types.contact.Phone;
import r01f.util.types.collections.CollectionUtils;

/**
 * Builds a {@link PB01AAlarmMessage} from an {@link PB01AAlarmEvent}
 */
@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class PB01CAlarmMessageBuilder
  implements IsBuilder {
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	public static PB01CAlarmMessageBuilderEventStep using(final PB01APanicButtonClientAPI api) {
		return new PB01CAlarmMessageBuilder() {/* nothing */}
						.new PB01CAlarmMessageBuilderEventStep(api,
															  new PB01AAlarmMessage());
	}
/////////////////////////////////////////////////////////////////////////////////////////
//
/////////////////////////////////////////////////////////////////////////////////////////
	@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
	public class PB01CAlarmMessageBuilderEventStep {
		private final PB01APanicButtonClientAPI _api;
		private final PB01AAlarmMessage _message;

		public PB01AAlarmMessage createForEvent(final PB01AAlarmEvent event) {
			_message.setAlarmEventOid(event.getOid());
			_message.setDateTime(event.getDateTime());

			PB01AOrganization org = _api.organizationsAPI()
										.getForCRUD()
											.load(event.getOrganization().getOid());
			PB01AOrgDivision division = _api.orgDivisionsAPI()
												.getForCRUD()
													.load(event.getDivision().getOid());
			PB01AOrgDivisionService service = _api.orgDivisionServicesAPI()
														.getForCRUD()
															.load(event.getService().getOid());
			PB01AOrgDivisionServiceLocation location = _api.orgDivisionServiceLocationsAPI()
															.getForCRUD()
																.load(event.getLocation().getOid());
			PB01AWorkPlace workPlace = _api.workPlacesAPI()
												.getForCRUD()
													.load(event.getWorkPlace().getOid());
			// org & loc are mandatory
			if (org == null || division == null || service == null || location == null || workPlace == null) {
				throw new IllegalStateException(Throwables.message("The {} with oid={} is NOT valid: the organization={}, division={}, service={}, location={} or workPlace={} does NOT exists",
																   PB01AAlarmEvent.class.getSimpleName(),event.getOid(),
																   event.getOrganization().getId(),event.getDivision().getId(),event.getService().getId(),event.getLocation().getId(),event.getWorkPlace().getId()));
			}
			_message.setOrganization(new PB01AAlarmMessageAbstractForOrganization(org));
			_message.setDivision(new PB01AAlarmMessageAbstractForDivision(division));
			_message.setService(new PB01AAlarmMessageAbstractForService(service));
			_message.setLocation(new PB01AAlarmMessageAbstractForLocation(location));
			_message.setWorkPlace(new PB01AAlarmMessageAbstractForWorkPlace(workPlace));

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
