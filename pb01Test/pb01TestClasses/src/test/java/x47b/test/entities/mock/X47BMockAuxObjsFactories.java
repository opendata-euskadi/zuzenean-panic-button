package x47b.test.entities.mock;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Sets;

import r01f.types.contact.EMail;
import r01f.types.contact.Phone;

class X47BMockAuxObjsFactories {
	static Collection<Phone> createPhoneList() {
		Set<Phone> outPhones = Sets.newHashSetWithExpectedSize(5);
		outPhones.add(Phone.create("+34616178858"));	// Iker
		outPhones.add(Phone.create("+34688671967"));	// Alex
		outPhones.add(Phone.create("+34609479344"));	// Isra
		outPhones.add(Phone.create("+34669077629"));	// Alberto
//		outPhones.add(Phone.create("+34646229762"));	// unai
		return outPhones;
	}
	static Collection<EMail> createEMailList() {
		Set<EMail> outMails = Sets.newHashSetWithExpectedSize(5);
		outMails.add(EMail.create("a-lara@ejie.eus"));
//		outMails.add(EMail.create("futuretelematics@gmail.com"));
//		outMails.add(EMail.create("isgaral@gmail.com"));
//		outMails.add(EMail.create("i-olabarria@ejie.eus"));
//		outMails.add(EMail.create("percebe@gmail.com"));
		return outMails;
	}
}
