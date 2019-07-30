
package pb01.ui.i18n;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Singleton;

import lombok.Getter;
import lombok.experimental.Accessors;
import pb01.PB01UI;
import r01f.ui.i18n.UII18NService;
import r01f.ui.vaadin.i18n.VaadinUII18NManager;
import r01f.util.types.collections.Lists;


@Singleton
@Accessors(prefix="_")
public class PB01UII18NManager
     extends VaadinUII18NManager
  implements UII18NService {

    private static final long serialVersionUID = -4523459172009081309L;
/////////////////////////////////////////////////////////////////////////////////////////
//  FIELDS
/////////////////////////////////////////////////////////////////////////////////////////
    @Getter List<String> _i18nBundleNames = new LinkedList<String>();
/////////////////////////////////////////////////////////////////////////////////////////
//  CONSTRUCTORS
/////////////////////////////////////////////////////////////////////////////////////////
    public PB01UII18NManager() {
        this(Thread.currentThread().getContextClassLoader());
    }
    public PB01UII18NManager(final ClassLoader classLoader) {
        super(Lists.newArrayList(PB01UI.PACKAGE));
    }
}
